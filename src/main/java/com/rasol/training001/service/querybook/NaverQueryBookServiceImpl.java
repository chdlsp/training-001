package com.rasol.training001.service.querybook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasol.training001.constant.Constants;
import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.exception.RestException;
import com.rasol.training001.model.dto.querybook.NaverBooks;
import com.rasol.training001.model.dto.Book;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NaverQueryBookServiceImpl implements QueryBookService {

    private RestTemplate restTemplate;

    public NaverQueryBookServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size) {
        String fullUrl = this.createFullUrl(keyword, page, size);

        return Optional.ofNullable(this.getNaverBook(fullUrl)).orElseGet(NaverBooks::new).getBooks()
                .stream()
                .map(Book::new)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        String isbnUrl = this.createIsbnUrl(isbn);

        NaverBooks naverBooks = Optional.ofNullable(this.getNaverBook(isbnUrl)).orElseGet(NaverBooks::new);
        if(naverBooks.getBooks().size()==0){
            throw NotFoundException.getBookNotFoundException();
        }

        return new Book(naverBooks.getBooks().get(0));
    }

    private String createIsbnUrl(String isbn){
        String url = Arrays.stream(isbn.split(" "))
                .map(splitIsbn -> "&querybook=" + splitIsbn)
                .reduce("", String::concat);
        return Constants.NAVER_BASE_URL + Constants.NAVER_BOOK_API_URL + "?target=isbn" + url;
    }

    private String createFullUrl(String keyword, Integer start, Integer display){
        return Constants.NAVER_BASE_URL + Constants.NAVER_BOOK_API_URL + "?querybook=" + keyword + "&start=" + start + "&display=" + display;
    }

    private NaverBooks getNaverBook(String url){
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.NAVER_CLIENT_ID_KEY, Constants.NAVER_CLIENT_ID);
        headers.set(Constants.NAVER_CLIENT_SECRET_KEY, Constants.NAVER_CLIENT_SECRET);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<NaverBooks> responseEntity = null;
        Map<String, Object> data;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<NaverBooks>() {
            });
        }catch(HttpClientErrorException e){
            try {
                data = objectMapper.readValue(e.getResponseBodyAsString(), new TypeReference<Map<String, Object>>() {
                });
            }catch(IOException i){
                throw new RestException(e.getResponseBodyAsString(), e.getStatusCode());
            }
            throw new RestException((String)data.get("message"), HttpStatus.BAD_REQUEST);
        }
//        Optional<KakaoBooks> maybeKakaoBook = Optional.ofNullable(responseEntity.getBody());
        return responseEntity.getBody();

    }

}

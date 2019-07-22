package com.rasol.training001.service.querybook;

import com.rasol.training001.constant.Constants;
import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.model.dto.querybook.KakaoBooks;
import com.rasol.training001.model.dto.Book;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class KakaoQueryBookServiceImpl implements QueryBookService {

    private RestTemplate restTemplate;
    private static final String AUTHORIZATION = "KakaoAK " + Constants.KAKAO_APP_KEY;

    public KakaoQueryBookServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size) {
        String fullUrl = this.createFullUrl(keyword, page, size);

        return Optional.ofNullable(this.getKakaoBook(fullUrl)).map(KakaoBooks::getBooks).orElse(null);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        String isbnUrl = this.createIsbnUrl(isbn);

        KakaoBooks kakaoBooks = Optional.ofNullable(this.getKakaoBook(isbnUrl)).orElse(null);

        return Optional.ofNullable(kakaoBooks).isPresent() ? kakaoBooks.getBooks().get(0) : null;
    }

    private String createIsbnUrl(String isbn){
        String url = Arrays.stream(isbn.split(" "))
                .map(splitIsbn -> "&query=" + splitIsbn)
                .reduce("", String::concat);
        return Constants.KAKAO_BASE_URL + Constants.KAKAO_BOOK_API_URL + "?target=isbn" + url;
    }

    private String createFullUrl(String keyword, Integer page, Integer size){
        return Constants.KAKAO_BASE_URL + Constants.KAKAO_BOOK_API_URL + "?query=" + keyword + "&page=" + page + "&size=" + size;
    }

    private KakaoBooks getKakaoBook(String url){
//        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.KAKAO_HEADER_KEY, AUTHORIZATION);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<KakaoBooks> responseEntity = null;
//        Map<String, Object> data;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<KakaoBooks>() {
            });
        }catch(HttpClientErrorException e){
//            try {
//                data = objectMapper.readValue(e.getResponseBodyAsString(), new TypeReference<Map<String, Object>>() {
//                });
//            }catch(IOException i){
//                throw new RestException(e.getResponseBodyAsString(), e.getStatusCode());
//            }
//            throw new RestException((String)data.get("message"), HttpStatus.BAD_REQUEST);
            return null;
        }
        return responseEntity.getBody();

    }

}

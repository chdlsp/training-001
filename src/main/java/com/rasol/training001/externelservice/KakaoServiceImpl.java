package com.rasol.training001.externelservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasol.training001.constant.Constants;
import com.rasol.training001.exception.NotFoundException;
import com.rasol.training001.exception.RestException;
import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.dto.KakaoBook;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class KakaoServiceImpl implements KakaoService{

    private RestTemplate restTemplate;
    private static final String authorization = "KakaoAK " + Constants.kakaoAppKey;

    public KakaoServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size, String target) {
        String fullUrl = this.createFullUrl(keyword, page, size, target);

        return Optional.ofNullable(this.getKakaoBook(fullUrl)).orElseGet(KakaoBook::new).getBooks();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        String isbnUrl = this.createIsbnUrl(isbn);

        KakaoBook kakaoBook = Optional.ofNullable(this.getKakaoBook(isbnUrl)).orElseGet(KakaoBook::new);
        if(kakaoBook.getBooks().size()==0){
            throw NotFoundException.getBookNotFoundException();
        }

        return kakaoBook.getBooks().get(0);
    }

    private String createIsbnUrl(String isbn){
        String url = Arrays.stream(isbn.split(" "))
                .map(splitIsbn -> "&query=" + splitIsbn)
                .reduce("", String::concat);
        return Constants.kakaoBaseUrl + Constants.kakaoApiUrl + "?target=isbn" + url;
    }

    private String createFullUrl(String keyword, Integer page, Integer size, String target){
        return Constants.kakaoBaseUrl + Constants.kakaoApiUrl + "?target=" + target + "&query=" + keyword + "&page=" + page + "&size=" + size;
    }

    private KakaoBook getKakaoBook(String url){
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.kakaoHeaderKey, authorization);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<KakaoBook> responseEntity = null;
        Map<String, Object> data;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<KakaoBook>() {
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
//        Optional<KakaoBook> maybeKakaoBook = Optional.ofNullable(responseEntity.getBody());
        return responseEntity.getBody();

    }

}

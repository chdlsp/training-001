package com.rasol.training001.externelservice;

import com.rasol.training001.constant.Constants;
import com.rasol.training001.model.dto.Book;
import com.rasol.training001.model.dto.KakaoBook;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class KakaoServiceImpl implements KakaoService{

    private RestTemplate restTemplate;

    public KakaoServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Book> getBookListByKeywordAndPageAndSizeAndTarget(String keyword, Integer page, Integer size, String target) {
        String fullUrl = this.createFullUrl(keyword, page, size, target);
        String authorization = this.getAuthorization();

        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.kakaoHeaderKey, authorization);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<KakaoBook> responseEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, entity, new ParameterizedTypeReference<KakaoBook>() {
        });
        Optional<KakaoBook> maybeKakaoBook = Optional.ofNullable(responseEntity.getBody());

        return maybeKakaoBook.orElseGet(KakaoBook::new).getBooks();
    }

    private String createFullUrl(String keyword, Integer page, Integer size, String target){
        return Constants.kakaoBaseUrl + Constants.kakaoApiUrl + "?target=" + target + "&query=" + keyword + "&page=" + page + "&size=" + size;
    }

    private String getAuthorization(){
        return "KakaoAK " + Constants.kakaoAppKey;
    }
}

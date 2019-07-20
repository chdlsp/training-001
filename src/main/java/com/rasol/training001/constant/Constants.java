package com.rasol.training001.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final String kakaoBaseUrl = "https://dapi.kakao.com";
    public static final String kakaoApiUrl = "/v3/search/book";
    public static final String kakaoAppKey = "8a933422d0e43d89b011e9027e2756e8";
    public static final String kakaoHeaderKey = "Authorization";

}

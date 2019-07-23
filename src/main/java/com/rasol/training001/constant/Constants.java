package com.rasol.training001.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.management.timer.Timer;

@Component
public class Constants {
    public static final String KAKAO_BASE_URL = "https://dapi.kakao.com";
    // error test
//    public static final String KAKAO_BOOK_API_URL = "/v3/search/error";
    public static final String KAKAO_BOOK_API_URL = "/v3/search/book";
    public static final String KAKAO_APP_KEY = "8a933422d0e43d89b011e9027e2756e8";
    public static final String KAKAO_HEADER_KEY = "Authorization";

    public static final String NAVER_BASE_URL = "https://openapi.naver.com";
    public static final String NAVER_BOOK_API_URL = "/v1/search/book.xml";
    public static final String NAVER_BOOK_DETAIL_URL = "/v1/search/book_adv.xml";
    public static final String NAVER_CLIENT_ID = "i_XB2KjtvLVA8R_Qqkvl";
    public static final String NAVER_CLIENT_SECRET = "l_hAGzfLeA";
    public static final String NAVER_CLIENT_ID_KEY = "X-Naver-Client-Id";
    public static final String NAVER_CLIENT_SECRET_KEY = "X-Naver-Client-Secret";

    public static final Long BOOK_CACHING_TIME = 3600000L;
    // 1 hour
//    public static final Long BOOK_CACHING_TIME = Timer.ONE_HOUR;

}
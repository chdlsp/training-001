# training-001

# SuccessResponse

timestamp : 요청이 처리되어 나가는 시간
path : 요청자가 요청한 url
body : 해당 API의 결과값

example
{
    "timestamp": "2019-07-24T00:03:37.172+09:00",
    "path": "/users",
    "body": {
        "userId": "11"
    }
}

# ErrorResponse

timestamp : 요청이 처리되어 나가는 시간
path : 요청자가 요청한 url
message : 해당 API의 에러 상세
error : 해당 API의 HttpStatus error
status : 해당 API의 HttpStatus errorCode 

example
{
    "timestamp": "2019-07-24T00:03:41.796+09:00",
    "path": "/users/login",
    "message": "user not found",
    "error": "Not Found",
    "status": 404
}




# 1. 회원가입 / 로그인

# 회원가입 
POST /users

- 유저 정보 생성 ( 중복시 에러 )
- 패스워드를 암호화하여 DB에 저장

RequestBody : 
String userId // 최대 255자, 공백을 제외한 문자 1자 이상
String password

ResponseBody.body :
String userId

ErrorResponse :
409 Conflict  // id 중복
400 Bad Request // request 형식 오류




# 로그인
POST /users/login

- 유저 정보 대조 ( 없거나 패스워드가 틀릴시 에러)
- springSecurity 를 이용한 session 생성

RequestBody : 
String userId // 최대 255자, 공백을 제외한 문자 1자 이상
String password

ResponseBody.body :
String userId

ResponseHeader :
JSESSIONID

ErrorResponse :
403 Fobbiden  // 패스워드 불일치
404 Not Found  // 가입되지 않은 유저
400 Bad Request // request 형식 오류


# 2. 책 검색

# 책 리스트 조회
GET /books?keyword={keyword}&page={page}&size={size}

- 로그인 세션 필요
- kakao server 에서 조회 후, 에러 시 -> naver server 에서 조회
- 1시간동안 DB에 저장하여 캐싱

RequestParam : 
String keyword // 공백을 제외한 문자 1자 이상
Integer page  // 1 이상 100 이하 (kakao rule과 동일), optional, default : 1
Integer size  // 1 이상 50 이하 (kakao rule과 동일), optional, default : 10

ResponseBody.body[] :
String title
String isbn
String thumbnail
String contents
String authors
String datetime

ErrorResponse :
403 Fobbiden // 인증되지 않은 유저
400 Bad Request // request 형식 오류


# 3. 검색된 책의 상세 조회

# 책 상세 조회
GET /books/isbsns/{isbn}

- 로그인 세션 필요
- cache DB 에서 조회 후 없을시 -> kakao server 에서 조회 후, 에러 시 -> naver server 에서 조회
- 1시간동안 DB에 저장하여 캐싱
- keyword counting ( NativeQuery 를 이용하여 concurrency issue 해결 )

PathVariable : 
String isbn // 공백을 제외한 문자 1자 이상

ResponseBody.body :
String title
String isbn
String thumbnail
String contents
String authors
String datetime
String publisher
Integer price
Integer sale_price

ErrorResponse :
403 Fobbiden // 인증되지 않은 유저
404 Not Found // 책을 찾지 못했을 때
400 Bad Request // request 형식 오류


# 4. 내 검색 히스토리

# 검색 히스토리 조회
GET /histories/users/{userId}

- 로그인 세션 필요
- userId 와 로그인 세션의 userId 동일 검사

PathVariable :
String userId

ResponseBody.body[] : // 최신순 정렬
userId
keyword
date

ErrorResponse :
403 Fobbiden // 인증되지 않은 유저


# 5. 인기 키워드 목록

# 인기 키워드 조회
GET /popularKeywords

- 로그인 세션 필요

ResponseBody.body[] : // count 내림차순 정렬
keyword
count

ErrorResponse :
403 Fobbiden // 인증되지 않은 유저


# A. 트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 구현

# Caching

- 책 리스트 조회, 책 상세 조회 시 books 테이블에 저장하여 캐싱.
- 책 상세 조회 시 1차적으로 cache DB 에서 조회하고, 없을 시 API 호출
- Scheduler 가 10분마다 modifiedDate 가 1시간보다 과거인 데이터를 검색해서 삭제.
- 책 상세 조회 시 modifiedDate 를 수정하여 자주 조회되는 데이터는 계속 유지.


# B. 동시성 이슈가 발생할 수 있는 부분을 염두에 둔 구현 (예시. 키워드 별로 검색된 횟수의 정확도)

# PopularKeywords.count

- JPA named query 가 아닌 native query 를 이용하여 update query를 생성 
- (UPDATE PopularKeywordEntity p SET p.count = p.count + 1L WHERE p.keyword = :keyword)
- update 문 실행시 row lock 이 걸린다는 점을 이용, 해당 row 의 데이터를 이용하게 해서 동시성 문제가 없을것으로 예상.


# C. 카카오 책 검색 API에 장애가 발생한 경우, 네이버 책 검색 API를 통해 데이터 제공 (Front-end는 이 사실을 몰라야 함)

# Naver books API

- size, page 의 validation 을 해당 App 에서 검사하여 Kakao의 기준에 통일.
- Kakao, Naver 에서 발생 가능한 에러를 미리 체크하여 에러코드 통일.
- Kakao, Naver 의 리턴값을 받아 custom DTO 에 넣음으로써 Response 통일.
- Naver API 의 경우 isbn 검색이 XML API 밖에 없기 때문에 목록 조회, 상세 조회 모두 XML 타입으로 받게 변경.










# **Main**

## Users

- 로그인
    
    -로그인, 회원가입 버튼 클릭
    
    - 계정 생성
        
        **`POST`**  / users
        
    - 계정 조회
        
        **`GET`**  /users/{users_id}
        

## Restaurants

- 검색 //이름(restaurants-name), 분위기(restaurants-mood), 날씨별(weather) 검색

//레스토랑 등록할 때 분위기, 날씨 삽입

-검색 버튼 클릭

- 검색결과 식당 조회
    - 이름 조회
        
        **`GET`**  /restaurants?sort.fieldName=restaurants-name&sort.order=DESC
        
    - 분위기 조회
        
        **`GET`**  /restaurants?sort.fieldName=restaurants-mood&sort.order=DESC
        
    - 날씨별 조회
        
        **`GET`**  /restaurants?sort.fieldName=weather&sort.order=DESC
        
    
- 지도
    
    **`GET`**  /restaurants/map/{restaurants_id}
    
    - 마커 클릭 시 **Detail**로 이동

## Detail

- 리뷰(review)
    - 리뷰 등록
        
        **`POST`** /review
        
    - 리뷰 조회(남이 쓴 것)
        
        **`GET`**  /review/{restaurants_id}
        
    

## My Page

- 회원 정보
    - 회원 정보 조회
        
        **`GET`**  /member/{users_id}
        
    - 회원 정보 수정
        
        **`PATCH`**  /member/{users_id}
        

- 찜 목록
    - 찜 조회
        
        **`GET`** /likes/{users_id} 
        
    - 찜 등록
        
        **`POST`**  /likes
        
    - 찜 삭제
        
        **`DELETE`** /likes/{users_id}
        
    
- 리뷰
    - 리뷰 조회(내가 쓴 것)
        
        **`GET`**  /review/{users_id}
        
    - 리뷰 수정
        
        **`PATCH`**  /review/{users_id}
        
    - 리뷰 삭제
        
        **`DELETE`**  /review/{users_id}
        
        
        
        
        
        
        
        
        
        
        
     

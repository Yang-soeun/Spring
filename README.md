
# Spring_Study
<details>

<summary> ✔ Intellij 단축키 </summary>
<div markdown="1">

#### Preferences->  `Crtl + Akt + S `
#### refactor -> ` Crtl + Alt + M `
#### getter setter -> `Alt + insert `
#### create new Test -> ` Crtl + Shift + T `
#### static import -> `Alt + Enter `
#### 변수 이름 한번에 바꾸기 -> ` Shift + F6 `
#### 실행 -> ` Crtl + Shift + F10 `
#### 주석처리 ` Crtl + / `
#### method명 출력  `soutm`
#### 변수명 출력  `sout`
#### 코드복사  'Crtl + d'
#### 바로 다음줄로  'Crtl + Shift + Enter'
#### 인라인 ``

</div>
</details>

<details>

<summary> ❗ ERROR </summary>
<div markdown="1">

<details>
  <summary> 인코딩 설정 </summary>
  <div markdonw = "2">
  
  - 한글 깨짐 현상 방지
  
  ### 1️⃣ Intelij VM 설정
  - Intelij 설피 파일 경로로 이동
  - bin 디렉터리 하위 .vmoptions 파일을 메모장으로 실행
  
  ![image](https://user-images.githubusercontent.com/87464750/190101528-2f60ee16-b9a1-4e5e-9608-1b3e2774bc92.png)

  - 파일의 맨 아랫줄에 -Dfile.encoding=UTF-8를 추가한 후 실행.
  
  ### 2️⃣  Edit Custom VM Options
  - Itellij를 실행
  - help -> Edit Custom VM Options
  - -Dfile.encoding=UTF-8를 추가한 후 다시 실행.
  
  ### 3️⃣ Intellij Editor File Encoding 설정
  - File -> Setting
  
  ![image](https://user-images.githubusercontent.com/87464750/190102321-21f479d0-6451-49a0-b24d-1d570be8415b.png)


  </div>
</details>


</div>
</details>

## 📒 [스프링 핵심 원리 -기본](https://github.com/Yangsoeun/Spring/blob/f5191e187c0ee2e4a70395d6a327805b4989f378/%EC%8A%A4%ED%94%84%EB%A7%81%20%ED%95%B5%EC%8B%AC%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8.md)

## 📘 HTTP
<details>

<summary> 인터넷 네트워크 </summary>
<div markdown="1">

## 📑 IP - 인터넷 프로트콜 역할
- 지정한 IP 주소(IP Address)에 데이터 전달
- 패킷(Packet)이라는 통신 단위로 데이터 전달

#### ❗ IP 프로토콜의 한계
##### 비연결성
- 패킷을 받을 대상이 어뵤거나 서비스 불능 상태여도 패킷 전송
##### 비신뢰성
- 중간에 패킷이 사라진다면?
- 패킷이 순서대로 오지 않는다면?
##### 프로그램 구분
- 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상이면

## 📑 TCP, UDP
#### 인터넷 프로토콜 스택의 4계층
![image](https://user-images.githubusercontent.com/87464750/181905401-94c8f005-8da1-49fd-8b94-878e0cda0b66.png)

#### 프로토콜 계층
![image](https://user-images.githubusercontent.com/87464750/181905419-e1eaa3c2-fd00-4392-aa3d-79fdd9f2573c.png)

#### TCP 특징 - 전송 제어 프로토콜(Transmission Control Protocol)
- 연결지향: TCP 3 way handshake(가상 연결)
- 데이터 전달 보증
- 순서 보장
- 신뢰할 수 있는 프로토콜

#### UDP 특징 - 사용자 데이터그램 프로토콜(User Datagram Protocol)
- 연결지향 = TCP 3 way handshake X
- 데이터 전달 보증  X
- 순서 보장 X
- 데이터 전달 및 순서가 보장되지 않지만, 단순하고 빠름
- ✔ 정리
    - IP 와 거의 같다. + PORT + 체크섬 정도만 추가
    - 애플리케이션에서 추가 작업 필요
    
## 📑 PORT - 같은 IP 내에서 프로세스 구분

#### PORT
- 0~ 65535 할당 가능
- 0~1023: 잘 알려진 포트, 사용하지 않는 것이 좋음
- FRP -20,21
- TELNET -23
- HTTP - 80
- HTTPS - 443

## 📑 DNS
- IP는 기억하기 어렵다.
- IP는 변경될 수 있다.

#### DNS - 도메인 네임 시스템
- 도메인 명을 IP 주소로 변환

![image](https://user-images.githubusercontent.com/87464750/181905685-da34dea0-72c3-4e6b-9397-e0c8f5e77413.png)


  </div>
</details>

<details>
<summary> URI와 웹 브라우저 </summary>
<div markdown="1">

![image](https://user-images.githubusercontent.com/87464750/181905720-b08e46f7-0788-4611-b74f-a4b092432026.png)

## 📑 URI
#### 단어 뜻
- Uniform: 리소스 식별하는 통일된 방식
- Resource: 자원, URI로 식별할 수 있는 모든 것(제한없음)
- Identifier: 다른 항목과 구분하는데 필요한 정보

## 📑 URL, URN
####  단어 뜻
- URL - Locator: 리소스가 있는 위치를 지정
- URN - Name: 리소스에 이름을 부여
    - 위치는 변할 수 있지만, 이름은 변하지 않는다.
    - URN 이름만으로 실제 리소스를 찾을 수 있는 방법이 보편화 되지 않음
    
#### URL 전체 문법
• scheme://[userinfo@]host[:port][/path][?query][#fragment]

• https://www.google.com:443/search?q=hello&hl=ko

- 프로토콜(https)
- 호스트명(www.google.com)
- 포트 번호(443)
- 패스(/search)
- 쿼리 파라미터(q=hello&hl=ko)

### URL - scheme

• `scheme`://[userinfo@]host[:port][/path][?query][#fragment]

• `https`://www.google.com:443/search?q=hello&hl=ko

- 주로 프로토콜 사용
- 프로토콜: 어떤 방식으로 자원에 접근할 것인가 하는 약속 규칙
    - ex) http, https, ftp 등등
- http는 80포트, https는 443 포트를 주로 사용, 포트는 생략 가능
- https는 http에 보안이 추가된것(HTTP Secure)

### URL - userinfo
• scheme://`[userinfo@]`host[:port][/path][?query][#fragment]

• https://www.google.com:443/search?q=hello&hl=ko

- URL에 사용자 정보를 포함해서 인증
- 거의 사용하지 않음

### URL - host
• scheme://[userinfo@]`host`[:port][/path][?query][#fragment]

• https://`www.google.com`:443/search?q=hello&hl=ko

- 호스트명
- 도메인명 또는 IP 주소를 직접 사용가능

### URL - PORT
• scheme://[userinfo@]host`[:port]`[/path][?query][#fragment]

• https://www.google.com`:443`/search?q=hello&hl=ko

- 포트
- 접속 포트
- 일반적으로 생략, 생략시 http는 80, https는 443

### URL - path
• scheme://[userinfo@]host[:port]`[/path]`[?query][#fragment]

• https://www.google.com:443/`search`?q=hello&hl=ko

- 리소스 경로(path), 계층적 구조
- ex)
    -  /home/file1.jpg
    -  /members
    -  /members/100, /items/iphone12
    
### URL - query
• scheme://[userinfo@]host[:port][/path]`[?query]`[#fragment]

• https://www.google.com:443/search`?q=hello&hl=ko`

- key = value 형태
- ?로 시작, &로 추가 가능 ?keyA=valueA&keyB=valueB
- query parameter, query string 등으로 불림, 웹서버에 제공하는 파라미터, 문자 형태

### URL - fragment
• scheme://[userinfo@]host[:port][/path][?query]`[#fragment]`

• https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html`#getting-started-introducing-spring-boot`

- fragment
- html 내부 북마크 등에 사용
- 서버에 전송하는 정보 아님

  </div>
</details>

<details>
<summary> HTTP </summary>
<div markdown="1">

<details>
  <summary> 1️⃣ 기본 </summary>
  <div markdonw = "2">

### HTTP 특징
- 클라이언트 서버 구조
- 무상태 프로토콜(stateless), 비연결성
- HTTP 메시지
- 단순함, 확장가능

### 클라이언트 서버 구조
- Request Response 구조
- 클라이언트는 서버에 요청을 보내고, 응답을 대기
- 서버가 요청에 대한 결과를 만들어서 응답.

![image](https://user-images.githubusercontent.com/87464750/182030364-ee31ee5b-ef95-4c65-aba1-35b0cce023c1.png)

### StateLess - 무상태 프로토콜
- 서버가 클라이언트의 상태를 보존 X
- 장점: 서버 확장성 높은(스케일 아웃)
- 단점: 클라이언트가 추가 데이터 전송

#### ❗ 실무 한계
- 모든것을 무상태로 설계할 수 있는 경우도 있고 없는 경우도 있다.
- 무상태
    - ex) 로그인이 필요없는 단순한 서비스 소개 화면
- 상태유지
    - ex) 로그인
- 로그인한 사용자의 경우 로그인 했다는 상태를 서버에 유지
- 일반적으로 브라우저 쿠키와 서버 세션등을 사용해서 상태 유지
- 상태 유지는 최소한만 사용

### 비연결성
- HTTP는 기본이 연결을 유지하지 않는 모델
- 일반적으로 초 단위의 이하의 빠른 속도로 응답
- 1시간 동안 수천명이 서비스를 이용해도 실제 서버에서 동시에 처리하는 요청은 수십개 이하로 매우 작음
- 서버 자원을 매우 효율적으러 사용할 수 있음


  </div>
</details>

<details>
  <summary> 2️⃣ 메서드 </summary>
  <div markdonw = "2">

## 📒 HTTP 메서드
### 주요 메서드
- GET: 리소스 조회
- POST: 요청 데이터 처리, 주로 등록에 사용
- PUT: 리소스를 대체, 해당 리소스가 없으면 생성
- PATCH: 리소스 부분 변경
- DELETE: 리소스 삭제

### 기타 메서드
- HEAD: GET과 동일하지만 메시지 부분을 제외하고, 상태 줄과 헤더만 반환
- OPTIONS: 대상 리소스에 대한 통신 가능 옵션(메서드)을 설명(주로 CORS에서 사용)
- CONNECT: 대상 자원으로 식별되는 서버에 대한 터널을 설정
- TRACE: 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행

### 📑 GET
- 리소스 조회
- 서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스트링)를 통해서 전달
- 메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아서 권장하지 않음

### 📑 POST
- 요청 데이터 처리
- 메시지 바디를 통해 서버로 요청 데이터 전달
- 서버는 요청 데이터를 처리
    - 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행
- 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용

#### 요청 데이터를 처리하는다는 것?
- HTML 양식에서 입력된 필드와 같은 데이터 블록을 데이터 처리 프로세스에 제공
>ex)HTML FORM에 입력한 정보로 회원 가입, 주문 등에서 사용
- 게시판, 뉴스그룹, 메일링 리스트, 블로그 또는 유사한 기사 그룹에 메시지 게시
> ex) 게시판 글쓰기 댓글 달기
- 서버가 아직 식별하지 않은 새 리소스 생성
>  ex) 신규 주문 생성
- 기존 자원에 데이터 추가
> ex) 한 문서 끝에 내용 추가하기
❗ 이 리소스 URI에 POST 요청이 오면 데이터를 어떻게 처리할지 리소스마다 따로 정해야함 -> 정해진것이 없음

#### ✔ 정리
- 1. 새 리소스 생성(등록)
    - 서버가 아직 식별하지 않은 새 리소스 생성
- 2. 요청 데이터 처리
    - 단순히 데이터를 생성하거나, 변경하는 것을 넘어서 프로세스를 처리해야 하는 경우
        - ex) 주문에서 결제 완료 -> 배달 시작 -> 배달완료 처럼 단순히 값 변경을 넘어 프로세스의 상태가 변경되는 경우
    - POST의 겨로가로 새로운 리소스가 생성되지 않을수도 있음
        - ex)  POST /orders/{orderId}/start-delivery (컨트롤 URI)
- 3. 다른 메서드로 처리하기 애매한 경우
    - ex) JSON으로 조회 데이터를 넘겨야 하는데, GET 메서드를 사용하기 어려운 경우

### 📑 PUT, PATHCH, DELETE
#### PUT
- 리소스가 있으면 대체
- 리소스가 없으면 생성

- ❗ 클라이언트가 리소스를 식별
    - 클라이언트가 리소스 위치를 알고 URI 지정
    - POST와 차이점
    
#### DELETE
- 리소스 제거

## 📒 HTTP 메서드의 속성

![image](https://user-images.githubusercontent.com/87464750/183295046-2bf92b2e-7bda-483a-8919-42d6b193f921.png)


- 안전(Safe Methods)
- 멱등(Idempotent Methods)
- 캐시가능(Cacheable Methods)

### 안전
- 호출해도 리소스를 변경하지 않는다.

### 멱등
- f(f(x)) = f(x)
- 여러번 호출해도 결과가 같다.
- 멱등 메서드
    - GET
    - POST
    - DELETE
- POST는 멱등이 아니다
    - 두 번 호출하면 같은 결제가 중북해서 발생할 수 있다.
    
### 캐시가능
- 응답 결과 리소스를 캐시해서 사용해도 되는가?
- GET, HEAD, POST, PATCH 캐시가능
- 실제로는 GET, HEAD 정도만 캐시로 사용
    - POST, PATCH는 본문 내용까지 캐시 키로 고려해야 하는데, 구현이 쉽지 않음
    
  </div>
</details>

<details>
  <summary> 3️⃣ 메서드 활용 </summary>
  <div markdonw = "2">

### 클라이언트에서 서버로 데이터 전송 -2가지
- 쿼리 파라미터를 통한 데이터 전송
    - GET
    - 주로 정렬 필터(검색어)
- 메시지 바디를 통한 데이터 전송
    - POST, PUT, PATCH
    - 회원 가입, 상품 주문, 리소스 등록, 리소스 변경
    
### 클라이언트에서 서버로 데이터 전송 - 4가지 상황
- 정적 데이터 조회
    - 이미지, 정적 텍스트 문서
 - 동적 데이터 조회
    - 주로 검색, 게시판 목록에서 정렬 필터(검색어)
 - HTML Form을 통한 데이터 전송
    - 회원 가입, 상품 주문, 데이터 변경
 - HTTP API를 통한 데이터 전송
    - 회원 가입, 상품 주문, 데이터 변경
    - 서버 to 서버, 앱 클라이언트, 웹 클라이언트
    
 #### ✏ 정적 데이터 조회
 - 이미지, 정적 텍스트 문서
 - 조회는 GET 사용
 - 정적 데이터는 일반적으로 쿼리 파라미터 없이 리소스 경로로 단순하게 조회 가능
 
 #### ✏ 동적 데이터 조회
 - 주로 검색, 게시판 목록에서 정렬 필터(검색어)
 - 조회 조건을 줄여주는 필터, 조회 결과를 정렬하는 정렬 조건에 주로 사용
 - 조회는 GET 사용
 - GET은 쿼리 파라미터 사용해서 데이터 전달
 
 #### ✏ HTML Form 데이터 전송
 - HTML Form submit시 POST 전송
    - > ex) 회원 가입, 상품 주문, 데이터 변경
 - Content-Type: application/x-www-form-urlencoded 사용
    - form의 내용을 메시지 바디를 통해서 전송(key = value, 쿼리 파라미터 형식)
    - 전송 데이터를 url encoding 처리
        - > ex) abc김 ->abc%EA%B9%80
 - HTML Form은 GET 전송도 가능
 - Content-Type: multipart/form-data
    - 파일 업로드 같은 바이너리 데이터 전송시 사용
    - 다른 종류의 여러 파일과 폼의 내용 함께 전송 가능
 - HTML Form 전소은 GET, POST만 지원
 
 
 #### ✏ HTTP API 데이터 전송
 - 서버 to 서버
    - 백엔드 시스템 통신
 - 앱 클라이언트
    - 아이폰, 안드로이드
 - 웹 클라이언트
    - HTML에서 Form 전송 대신 자바 스크립트를 통한 통신에 사용
    - > ex) React, VueJs 같은 웹 클라이언트와 API 통신
 - POST, PUT, PATCH:메시지 바디를 통해 데이터 전송
 - GET: 조회, 쿼리 파라미터로 데이터 전달
 - Content-Type: application/json을 주로 사용
    -  TEXT, XML, JSON 등등

#### 📑 정리

##### HTTP API - 컬렉션
- POST 기반 등록
- 서버가 리소스 URI 결정
##### HTTP API - 스토어
- PUT 기반 등록
- 클라이언트가 리소스 URI 결정
##### HTML FORM 사용
- 순수 HTML + HTML form 사용
- GET, POST만 지원

  </div>
</details>

<details>
  <summary> 4️⃣ 상태코드 </summary>
  <div markdonw = "2">

#### 상태 코드 - 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능
- 1xx(Informational): 요청이 수신되어 처리중
- 2xx(Successful): 요청 정상 처리
- 3xx(Redirection): 요청을 완료하려면 추가 행동이 필요
- 4xx(Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- 5xx(Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함

#### ✏ 2XX(Successful)- 클라이언트 요청을 성공적으로 처리
- 200(OK): 요청 성공
- 201(Created): 요청 성공해서 새로운 리소스가 생성됨
- 202(Accepted): 요청이 접수되었으나 처리가 완료되지 않음
- 204(No Content): 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음

#### ✏ 3XX - 리다이렉션: 요청을 완료하기 위해 유저 에이전트의 추가 조치 필요

#### 영구 리다이렉션 - 301, 308
- 리소스의 URI가 영구적으로 이동
- 원래의 URL를 사용 X, 검색 엔진 등에서도 변경 인지
- 301 Moved Permantly
    - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음
- 308 Permanent Redirect
    - 301과 기능은 같음
    - 리다이렉트시 요청 메서드와 본문 유지(처음 POST를 보내면 리다이렉트도 POST 유지)
    
#### 일시적인 리다이렉션 - 302, 307, 303
- 리소스의 URI가 일시적으로 변경
- 따라서 검색 엔진 등에서 URL을 변경하면 안됨
- 302 Found
    - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음
- 307 Temporary Redirect
    - 302와 기능은 같음
    - 리다이렉트시 요청 메서드와 본문 유지(요청 메서드를 변경하면 안된다. MUST NOT)
- 303 See Other
    - 302와 기능은 같음
    - 리다이렉트시 요청 메서드가 GET으로 변경
    

  </div>
</details>

<details>
  <summary> 5️⃣ 쿠키 </summary>
  <div markdonw = "2">
  
### 📑 쿠키
- Set-cookie: 서버에서 클라이언트로 쿠키 전달(응답)
- Cookie: 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달

- > ex) set-cookie: sessionId=abcde1234; expires=Sat, 26-Dec-2020 00:00:00 GMT; path=/; domain=.google.com; Secure

#### 사용처
- 사용자 로그인 세션 관리
- 광고 정보 트래킹

#### 쿠키 정보는 항상 서버에 전송됨
- 네트워크 트래픽 추가 유발
- 최소한의 정보만 사용(세션 id, 인증 토큰)
- 서버에 전송하지 않고, 웹 브라우저 내부에서 데이터를 저장하고 싶으면 웹 스토리지(localStrorage, sessionStorage) 참고

#### 주의
- 보안이 민감한 데이터는 저장하면 안됨(주민번호, 신용카드 번호 등등)

### 📑 생명주기 -  Expires, max-age
- Set-Cookie: expires=Sat, 26-Dec-2020 04:39:21 GMT
    - 만료일이 되면 쿠키 삭제
-  Set-Cookie: max-age=3600 (3600초)
    - 0이나 음수를 지정하면 쿠키 삭제
    
- 세션 쿠키: 만료 날짜를 생략하면 브라우저 종료시까지만 유지
- 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지

### 📑 도메인 - domain
- ex) domain=example.org
- 명시: 명시한 문서 기준 도메인 + 서브 도메인 포함
- domain=example.org를 지정해서 쿠키 생성
    - example.org는 물론이고
    - dev.example.org도 쿠키 접근
- 생략: 현재 문서 기준 도메인만 적용
    - example.org 에서 쿠키를 생성하고 domain 지정을 생략
    -  example.org 에서만 쿠키 접근
    - dev.example.org는 쿠키 미접근
    
### 📑 경로 - Path
- ex) path=/home
- 이 경로를 포함한 하위 경로 페이지만 쿠키 접근
- 일반적으로 path=/ 루트로 지정
- ex)
    - path=/home 지정
    - /home -> 가능
    - /home/level1 -> 가능
    - /home/level1 -> 가능
    - /hello -> 불가능
    
### 📑 보안 - Seure, HttpOnly. SameSite
- Secure
    - 쿠키는 http, https를 구분하지 않고 전송
    - Secure를 적용하면 https인 경우에만 전송
    
- HttpOnly
    - XSS 공격 방지
    - 자바스크립트에서 접근 불가(document.cookie)
    - HTTP 전송에만 사용
- SameSite
    - XSRF 공격 방지
    - 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송
   
  </div>
</details>

  </div>
</details>

## 📙 MVC-1
<details>

<summary> 스프링 MVC 구조이해</summary>
<div markdown="1">

### 📑 SpringMVC 구조
![image](https://user-images.githubusercontent.com/87464750/185922255-108958ec-7a19-468a-838c-191aaea9c4a7.png)

#### 동작순서
- 1️⃣ 핸들러 조회: 핸들러 매핑을 통해 요청 URL에 매핑된 핸드러(컨트롤러)를 조회한다.
- 2️⃣ 핸들러 어댑터 조회: 핸들러를 실행할 수 있는 핸들러 어댑터를 조회한다.
- 3️⃣ 핸들러 어댑터 실행: 핸들러 어댑터를 실행한다.
- 4️⃣ 핸들러 실행: 핸들러 어댑터가 실제 핸들러를 실행한다.
- 5️⃣ ModelAndView 반환: 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환해서 반환한다.
- 6️⃣ viewResolver 호출: 뷰 리졸버를 찾고 실행한다.
    - JSP의 경우: `InternalResourceViewResolver`가 자동 등록되고 사용된다.
- 7️⃣ View 반환: 뷰 리졸버는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환한다.
    - JSP의 경우 `InternalResourceView(Jstlview)`를 반환하는데, 내부에 forward()로직이 있다.
- 8️⃣ 뷰 렌더링: 뷰를 통해서 뷰를 렌더링 한다.

#### 주요 인터페이스 목록
- 핸들러 매핑: `org.springframework.web.servlet.HandlerMapping`
- 핸들러 어댑터: `org.springframework.web.servlet.HandlerAdapter`
- 뷰 리졸버: `org.springframework.web.servlet.ViewResolver`
- 뷰: `org.springframework.web.servlet.View`

#### `@RequestMapping` 
- `RequestMappingHandlerMapping`
- `RequestMappingHandlerAdapter`

> ex) 회원 등록 폼
``` JAVA
package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {
  @RequestMapping("/springmvc/v1/members/new-form")
  public ModelAndView process() {
    return new ModelAndView("new-form");
  }
}
```

- `@Controller`
    - 스프링이 자동으로 스프링 빈으로 등록한다.(내부에 `@Component` 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
    - 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.
- `@RequestMapping`
    - 요청 정보를 매핑한다.
    - 해당 URL이 호출되면 이 메서드가 호출된다.
    - 애노테이션을 기반으로 동작하기 때문에, 메섣의 이름은 임의로 지으면 된다ㅓ.
- `ModelAndView`
    - 모델과 뷰 정보를 담아서 반환하면 된다.

#### 조합
- `@RequestMapping("/springmvc/v2/members/new-form")`
- `@RequestMapping("/springmvc/v2/members")`
- `@RequestMapping("/springmvc/v2/members/save")`

- 다음 코드는 ` /springmvc/v2/members`라는 부분에 중복이 있다.

#### 조합결과
- 클래스 레벨 `@RequestMapping("/springmvc/v2/members")`
    - 메서드 레벨 `@RequestMapping("/new-form")` ->  `/springmvc/v2/members/new-form`
    - 메서드 레벨 `@RequestMapping("/save")` -> `/springmvc/v2/members/save`
    - 메서드 레벨 `@RequestMapping` -> `/springmvc/v2/members`


#### Spring MVC - 실용적인 방식
``` JAVA
package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * @RequestParam 사용
 * @RequestMapping -> @GetMapping, @PostMapping
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
 
    @GetMapping("/new-form")
    public String newForm() {
      return "new-form";
    }
    
    @PostMapping("/save")
    public String save(
        @RequestParam("username") String username,
        @RequestParam("age") int age,
        Model model) {
      
      Member member = new Member(username, age);
      memberRepository.save(member);
      model.addAttribute("member", member);
      return "save-result";
    }
    
    @GetMapping
    public String members(Model model) {
      List<Member> members = memberRepository.findAll();
      model.addAttribute("members", members);
      return "members";
    }
}

```

#### Model 파라미터
- `save()`, `members()`를 보면 Model을 파라미터로 받는 것을 확인가능
#### ViewName 반환
- 뷰의 논리 이름을 반환할 수 있다.
#### @RequestParam 사용
- 스프링은 HTTP 요청 파라미터를 `RequestParam`으로 받을 수 있다.
- `@RequestParam("username")` 은 ` request.getParameter("username")`와 거의 같은 코드라 생각하면 된다.
- GET 쿼리 파라미터, POST Form 방식을 모두 지원

#### `@RequestMapping` ->  `@GetMapping`, `@PostMapping`
- HTTP Method 구분가능

  </div>
</details>

<details>

<summary> 스프링 MVC 기본기능</summary>
<div markdown="1">

<details>
  <summary> 1️⃣ 로깅 </summary>
  <div markdonw = "2">
 
#### 로깅 라이브러리
- 스프링 부트 라이브러리를 사용하면 스프링 부트 로깅 라이브러리 `spring-boot-starter-logging`가 함께 포함된다.
- 스프링 부트 로깅 라이브러리는 기본으로 다음 로깅 라이브러리를 사용한다.
    - SLF4J - http://www.slf4j.org
    - Logback - http://logback.qos.ch
    
#### 로그 선언
- private Logger log = LoggerFactory.getLogger(getClass());
- private static final Logger log = LoggerFactory.getLogger(Xxx.class)
- @Slf4j : 롬복 사용 가능

#### 매핑정보
- `@RestController`
    - `@Controller`는 반환값이 String이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
    - `@RestController`는 반환값으로 뷰를 찾는것이 아니라, HTTP 메시지 바디에 바로 입력한다.
    
#### 로그레벨
- LEVEL: `TRACE > DEBUG > INFO > WARN > ERROR`
- 개발 서버는 debug 출력
- 운영 서버는 info 출력

#### 올바른 로그 사용법
- ❗ `log.debug("data="+data)`
    - 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어버린다. 결과적으로 문자 더하기 연산이 발생.
- ✔ `log.debug("data={}", data)`
    - 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다. 따라서 앞과 같은 의미없는 연산이 발생하지 않는다.
    
#### 💡 로그 사용시 장점
- 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정할 수 있다.
- 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력하지 않는 등 로그를 상황에 맞게 조절할 수 있다.
- 시스템 아웃 콘솔에만 출력하는 것이 아니라, 파일이나 네트워크 등, 로그를 별도의 위치에 남길 수 있다. 특히 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할하는 것도 가능하다.
- 성능도 일반 System.out 보다 좋다(내부 버퍼링, 멀티 쓰레드 등등) 그래서 실무에서는 꼭 로그를 사용해야 한다.


  </div>
</details>

<details>
  <summary> 2️⃣ 요청 매핑 </summary>
  <div markdonw = "2">
  
### 매핑 정보
- `@RestController`
    - `@Controller`는 반환값이 `String`이면 뷰 이름으로 인식. 그래서 뷰를 찾고 뷰가 랜더링 된다.
    - `@RestController`는 반환값으로 뷰를 찾는것이 아니라, HTTP 메시지 바디에 바로 입력한다.
- `@RequestMapping("/hello-basic)`
    - `/hello-basic` URL 호출이 오면 이 메서드가 실행되도록 매핑.
    - 대부분의 속성을 배열로 제공하므로 다중 설정이 가능하다.
        - `{"/hello-basic", "/hello-go"}`
 
- 매핑: `/hello-basic`
- URL 요청: `/hello-basic', `/hello-basic/`
    - 요청 URL이 다르지만 스프링은 같은 요청으로 매핑한다.
       
### HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form
#### ❗ 클라이언트에서 서버로 요청 데이터 전달하는 3가지 방법
- 1️⃣ GET - 쿼리 파라미터
    - /url?username=hello&age=20
    - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
    - ex) 검색, 필터, 페이징등에서 많이 사용하는 방식
- 2️⃣ POST -HTML Form
    - content-type: application/x-www-form-urlencoded
    - 메시지 바디에 쿼리 파라미터 형식으로 전달  username=hello&age=20
    - ex) 회원 가입, 상품 주문, HTML Form 사용
- 3️⃣ HTTP message body에 데이터를 직접 담아서 요청
    - HTTP API에서 주로 사용, JSON< XML, TEXT
    - 데이터 형식은 주로 JSON 사용
    - POST, PUT, PATHCH
    </div>
</details>

  </div>
</details>
  
  ## 📕 [MVC_2]()
  

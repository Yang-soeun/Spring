## ❗❕ MVC-2

<details>

<summary> ✔ 검증 </summary>
<div markdown="1">

### `BindingResult`
- 스프링이 제공하는 검증 오류를 보고나하는 객체
- 검증 오류가 발생하면 여기에 보관
- `BindinResult`가 있으면 `@ModelAttribute`에 데이터 바인딩 시 오류가 발생해도 컨트롤러가 호출된다
  
### ex) `@ModelAttribute`에 바인딩 시 타입 오류가 발생하면?
- `BindingResult`가 없으면 -> 400오류가 발생하면서 컨트롤러가 호출되고, 오류 페이지로 이동
- `BindingResult`가 있으면 -> 오류정보(FieldError)를 (BindingResult)에 담아서 컨트롤러를 정상호출
  
### 적용하는 방법
- 1️⃣ `@ModelAttribute`의 객체 타입 오류 등으로 바인딩이 실패하는 경우 스프링이 `FieldError`를 생성해서 `BindingResult`에 넣어준다.
- 2️⃣ 개발자가 직접 넣어준다.
- 3️⃣ `Validator` 사용
  
### ❗ 주의
- `BindingResult`는 검증할 대상 바로 다음에 와야함
    - ex) `@ModelAttribute Item item, `바로 다음에 `BindingResult`가 와야한다.
- `BindingResult`는 `Model`에 자동으로 포함된다.
- BindingResult는 인터페이스고, Errors 인터페이스를 상속받고 있다
- BindingResult 대신에 Errors를 사용해도 된다.
- Errors 인터페이스는 단순한 오류 저장과 조회 기능을 제공한다.
- BindingResult는 여기에 더해서 추가적인 기능들을 제공한다. - 주로 많이 사용
  
## 📑 Bean Validation
### Bean Validatoin이란?
- 검증 애노테이션과 여러 인터페이스의 모음
- 이를 구현한 기술중에 일반적으로 사용하는 구현체는 하버네이트 Validator이다.

#### 하버네이트 Validator
- 공식 사이트: http://hibernate.org/validator/
- 공식 메뉴얼: https://docs.jboss.org/jibernate/validator/6.2/reference/en-US/html_single/
- 검증 애노테이션 모음: https://docs/jboss.org/hibernate/validator/6.2/reference/en-US/html_single/#validator-defineconstraints-spec
  
  
### 검증 애노테이션
- `@NotBlank`: 빈값+공백을 허용하지 않는다.
- `@NotNull`:null을 허용하지 않는다.
- `@Range(min= , max= )`: 범위안의 값이어야 한다.
- `@Max()`: 최대()까지만 허용.
  
#### ❕ 참고
-  javax.validation.constraints.NotNull
-  org.hibernate.validator.constraints.Range
-  javax.validation으로 시작하면 특정 구현에 관계없이 제공되는 표준 인터페이스이고, org.hibernate.validator로 시작하면 하버네이트 validator 구현체를 사용할 때만 제공되는 검증 기능이다.

  
### 스프링 MVC Bean Validator 사용
- 스프링 부트가  spring-boot-starter-validation 라이브러리를 넣으면 자동으로 Bean Validator를 인지하고 스프링에 통합
- 스프링 부트는 자동으로 글로벌 Validator로 등록한다.
    - LocalValidatorFactoryBean을 글로벌 Validator로 등록.
    - 이 Validator는 @NotNull 과 같은 애노테이션을 보고 검증을 수행한다.
    - 이렇게 글로벌 Validator가 적용되어 있기 때문에, @Valid, @Validated만 적용하면 된다.
    - 검증 오류가 발생하면, FieldError, ObjectError를 생성해서 BindingResult에 담아준다.
  
### ❗ 주의
- 검증시 @Validated, @Valid 둘 다 사용가능하다.
-  javax.validation.@Valid를 사용하려면 build.gradle 의존관계 추가가 필요하다.
- @Validated는 스프링 전용 검증 애노테이션이고, @Valid는 자바 표준 애노테이션이다.
    - 둘중 아무거나 사용해도 동일하게 작동하지만 @Validated는 내부에 groups라는 기능을 포함하고 있다.

#### 검증순서
- 1. elAttribute 각각의 필드의 타입 변환시도
    - 1. 성공하면 다음으로
    - 2. 실패하면 typeMismatch로 FieldError 추가
- 2. Validator 적용
  
### 동일한 객체를 action에 따라 다르게 검증하는 방법
#### 1️⃣ BeanValidatoin의 groups 기능을 사용
#### 2️⃣ 별도의 모델 객체를 만들어서 사용
  
  
  
</div>
</details>

<details>

<summary> ✔ 로그인 </summary>
<div markdown="1">
  
  <details>
  <summary> 쿠키, 세션 </summary>
  <div markdonw = "2">

    ### 쿠키 생성
    ![image](https://user-images.githubusercontent.com/87464750/191488776-442ec51f-3ad9-4526-9b64-dfc948869e42.png)

    ### 클라이언트 쿠키 전달1
    ![image](https://user-images.githubusercontent.com/87464750/191488809-ee2e59ca-bead-49be-a397-3aa8158e9804.png)

    ### 클라이언트 쿠키 전달2
    ![image](https://user-images.githubusercontent.com/87464750/191488841-e90dbea0-411d-4c07-a354-a9c7f56d3495.png)

    - 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지
    - 세션 쿠키: 만료 날짜를 생략하면 브라우저 종류시 까지만 유지
    
    #### 쿠키 생성 로직
    ``` JAVA
    Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
    response.addCookie(idCookie);
    ```
    
    - 로그인에 성공하면 쿠키를 생성하고 HttpServletResponse에 담는다.
    - 쿠키 이름은 memberId이고, 값은 회원의 id를 담아둔다.
    - 웹 브라우저 종료전까지 회원의 id를 서버에 계속 보내줄것이다.

    #### 로그아웃
    
   </div>
</details>
  
  <details>
  <summary> 필터, 인터셉터 </summary>
  <div markdonw = "2">

   </div>
</details>
  
</div>
</details>

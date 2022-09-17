
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

## 📘 [HTTP](HTTP.md)

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
  

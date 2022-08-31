<details>

<summary> ✏ HTML </summary>
<div markdown="1">


</div>
</details>

<details>

<summary> ✏ Intellij 단축키 </summary>
<div markdown="1">

#### Preferences->  `Crtl + Akt + S `
#### refactor -> ` Crtl + Alt + M `
#### getter setter -> `Alt + insert `
#### create new Test -> ` Crtl + Shift + T `
#### static import -> `Alt + Enter `
#### 변수 이름 한번에 바꾸기 -> ` Shift + F6 `
#### 실행 -> ` Crtl + Shift + F10 `
#### 주석처리 ` Crtl + / `

</div>
</details>

<details>

<summary> ✏ 회원관리예제 </summary>
<div markdown="1">

## 📕 회원관리 예제 - 백엔드 개발
- 비즈니스 요구사항 정리
- 회원 도메인과 리포지토리 만들기
- 회원 리포지토리 테스트 케이스 작성
- 회원 서비스 개발
- 회원 서비스 테스트

#### 💡 비즈니스 요구사항 정리
- 데이터: 회원ID, 이름
- 기능: 회원 등록, 조회
- 아직 데이터 저장소가 선정되지 않음

- 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계

#### 1️⃣ 회원 도메인과 리포지토리 만들기
##### 회원 객체
[SourceCode](src/main/java/hello/hellospring/domain/Member.java)

##### 회원 리포지토리 인터페이스
[SourceCode](src/main/java/hello/hellospring/repository/MemberRepository.java)

##### 회원 리포지토리 메모리 구현체
[SourceCode](src/main/java/hello/hellospring/repository/MemoryMemberRepository.java)

#### 2️⃣ 회원 리포지토리 테이스 케이스 작성
- JUnit이라는 프레임워크로 테스트를 실행

##### 회원 리포지토리 메모리 구현체 테스트

` src/test/java ` 하위 폴더를 생성.

[SourceCode](src/test/java/hello/hellospring/repository/MemoryMembeRepositoryTest.java)

##### ` @AfterEach `
- 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남아 다음 테스트가 실패할 가능성이 있다.
- 각 테스트가 종료될때 마다 메모리 DB에 저장된 데이터를 삭제한다.
- 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.

#### 3️⃣ 회원 서비스 개발
[SourceCode](src/main/java/hello/hellospring/service/MemberService.java)

#### 4️⃣ 회원서비스 테스트
- 기존에는 회원 서비스가 메모리 회원 리포지토리를 직접 생성하게함.

``` JAVA
public class MemberService {
  private final MemberRepository memberRepository = new MemoryMemberRepository();
}
```

- 회원 서비스 코드를 DI 가능하게 변경.

``` JAVA
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
 ...
}
```

##### 회원 서비스 테스트
[SourceCode](src/test/java/hello/hellospring/service/MemberServiceTest.java)

##### ` @AfterEach `
- 각 테스트 실행전에 호출.
- 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.

## 📒 스프링 빈과 의존관계

- 컴포넌트 스캔과 자동 의존관계 설정
- 자바 코드로 직접 스프링 빈 등록하기

- 생성자에 `@Autowired`가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
  - DI: 객체 의존관계를 외부에서 넣어주는 거, 의존성 주입
  
 > HelloController는 스프링이 제공하는 컨트롤러여서 스프링 빈으로 자동 등록된다.
 > `@Controller`가 있으면 자동 등록됨
 
 #### 스프링 빈을 등록하는 2가지 방법
 - 컴포넌트 스캔과 자동 의존관계 설정
 - 자바 코드로 직접 스프링 빈 등록하기
 
 #### 컴포넌트 스캔 원리
 - `@Componet` 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
 - `@Controller` 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔때문이다.
 
- `@Component`를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
    - `@Controller`
    - `@Service`
    - `@Repository`
    
   
## 📗 회원 관리 예제- 웹 MVC 개발

- 회원 웹 기능 - 홈 화면 추가
- 회원 웹 기능 - 등록
- 회원 웹 기능 - 조회

#### 1️⃣ 회원 웹 기능 - 홈 화면 추가
- 홈 컨트롤러 추가

[SourceCode](src/main/java/hello/hellospring/controller/HomeController.java)

- 회원 관리용 홈

[SourceCodde](src/main/resources/templates/home.html)

> 컨트롤러가 정적 파일보다 우선순위가 높다.

#### 2️⃣ 회원 웹 기능 - 등록, 조회

- 회원 등록, 조회 폼 컨트롤러

[SourceCode](src/main/java/hello/hellospring/controller/MemberController.java)

- 회원 등록 폼 HTML

[SourceCode](src/main/resources/templates/members/createMemberForm.html)

- 회원 리스트 HTML
[SourceCode](src/main/resources/templates/members/memberList.html)

## 📘 스프링 DB 접근기술








</div>
</details>

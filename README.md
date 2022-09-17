
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

## 📒 스프링 핵심 원리 -기본

<details>

<summary> 회원관리예제 </summary>
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

## 📘 AOP
- AOP가 필요한 상황
> ex) 모든 메소드의 호출 시간을 측정하고 싶은경우

![image](https://user-images.githubusercontent.com/87464750/178098701-7b8dddbd-538d-4010-91b2-733e2d0b8ead.png)

💡 문제
- 회원가입 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
- 시간을 측정하는 로직은 공통 관심 사항이다.
- 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
- 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.

### AOP 적용
- AOP: Aspect Oriented Programming
- 공통 관심 사함 VS 핵심 관심 사항 분리

![image](https://user-images.githubusercontent.com/87464750/178098776-00c888de-8b5b-49c1-a897-8e27f8b9a2b7.png)

💡 해결
- 회원가입, 회원 조회 등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
- 핵심 관심 사항을 깔끔하게 유지할 수 있다.
- 변경이 필요하면 이 로직만 변경하면 된다.
- 원하는 적용 대상을 선택할 수 있다.

### 스프링의 AOP 동작 방식 설명
#### AOP 적용 전 의존관계
![image](https://user-images.githubusercontent.com/87464750/178098893-774f41cf-b738-49d4-ba2b-fc4d2213b239.png)

#### AOP 적용 후 의존관계
![image](https://user-images.githubusercontent.com/87464750/178098909-766e8001-919b-4890-a04f-e507bf1fa265.png)

#### AOP 적용 전 전체 그림
![image](https://user-images.githubusercontent.com/87464750/178098921-90c6c214-4cc0-47f9-97f9-1403f4b1f625.png)

#### AOP 적용 후 전체 그림
![image](https://user-images.githubusercontent.com/87464750/178098934-a992a071-6cac-482c-8a37-d324b7f51dbb.png)

> 실제 Proxy가 주입되는지 콘솔에 출력해서 확인하기


</div>
</details>

<details>

<summary>  스프링 핵심 원리 </summary>
<div markdown="1">

<details>
  <summary> 💡 스프링이란? </summary>
  <div markdonw = "2">
  
### 스프링 프레임워크
- `핵심 기술` : 스프링 DI 컨테이너, AOP, 이벤트, 기타
- `웹 기술` : 스프링 MVC, 스프링 WebFlux
- `데이터 접근 기술` : 트랜잭션, JDBC, ORM 지원, XML 지원
- `기술 통합` : 캐시, 이메일, 원격접근, 스케줄링
- `테스트` : 스프링 기반 테스트 지원
- `언어` : 코틀린, 그루비
- 최근에는 스프링 부트를 통해서 스프링 프레임워크의 기술들을 편라하게 사용

### 스프링 부트
- 스프링을 편리하게 사용할 수 있도록 지원, 최근에는 기본으로 사용
- 단독으로 실행할 수 있는 스프링 애플리케이션을 쉽게 생성
- Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨
- 손쉬운 빌드 구성을 위한 starter 종속성 제공
- 스프링과 3rd parth(외부) 라이브러리 자동 구성
- 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공
- 관례에 의한 간결한 설정

### 스프링의 핵심
- 스프링은 자바 언어 기반의 프레임워크
- 자바 언어의 가장 큰 특징 
      - 객체 지향 언어
- 스프링은 객체 지향 언어가 가진 강력한 특성을 살려내는 프레임워크
- 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크

### 스프링과 객체 지향
- 다형성이 가장 중요하다
- 스프링은 다형성을 극대화해서 이용할 수 있게 도와준다.
- 스프링에서 이야기하는 제어의 역전(IoC), 의존관계 주입(DI)은 다형성을 활용해서 역할과 구현을 편리하게 다룰 수 있도록 지원한다.
- 스프링을 사용하면 구현을 편리하게 변경할 수 있다.

  </div>
</details>

<details>
  <summary> 💡 SOLID </summary>
  <div markdonw = "2">
  
## SOLID
- 클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리

```
SRP: 단일 책임 원칙(single responsibility principle)
OCP: 개방-폐쇄 원칙 (Open/closed principle)
LSP: 리스코프 치환 원칙 (Liskov substitution principle)
ISP: 인터페이스 분리 원칙 (Interface segregation principle)
DIP: 의존관계 역전 원칙 (Dependency inversion principle) 
```

### 1️⃣ SRP 단일 책임 원칙
- 한 클래스는 하나의 책임만 가져야 한다.
- 하나의 책임이라는 것은 모호하다.
    - 클 수 있고, 작을 수 있다.
    - 문맥과 상황에 따라 다르다.
- 중요한 기준은 변경이다. 변경이 있을때 파급효과가 적으면 단일 책임 원칙을 잘 따른 것
> ex) UI 변경, 객체의 생성과 사용을 분리

### 2️⃣ OCP 개방-폐쇄 원칙
- 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
- 다형성을 활용.
- 인터페이스를 구현한 새로운 클래스 하나 만들어서 새로운 기능을 구현.
- 역할과 구현의 분리 생각.

``` JAVA
public class MemberService {
  private MemberRepository memberRepository = new MemoryMemberRepository();
}
```

``` JAVA
public class MemberService {
// private MemberRepository memberRepository = new MemoryMemberRepository();
   private MemberRepository memberRepository = new JdbcMemberRepository();
}
```

❗ 문제점
- MemberService 클라이언트가 구현 클래스를 직접 선택
    - MemberRepository m = new MemoryMemberRepository(); //기존 코드
    - MemberRepository m = new JdbcMemberRepository(); //변경 코드
- 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
- 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.
- 해결방법: 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다.

### 3️⃣ LSP 리스크프 치환 원칙
- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙, 인테페이스를 구현한 구현체는 믿고 사용하려면 이 원칙이 필요.
> ex) 자동차 인터페이스의 엑셀은 앞으로 가라는 기능, 뒤로 가게 구현하면 LSP 위반, 느리더라도 앞으로 가야함.

### 4️⃣ ISP 인터페이스 분리 원칙
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
- 사용자 클라이언트 -> 운전자 클라이언트, 정비사 클라이언트로 분리
- 분리하면 정비 인터펭스 자체가 변해도 운전자 클라이언트에 영향을 주지 않음
- 인터페이스가 명확해지고, 대체 가능성이 높아진다.

### 5️⃣ DIP 의존관계 역전 원칙
- 프로그래머는 `추상화에 의존해야지, 구체화에 의존하면 안된다.` 의존성 주입은 이 원칙을 따르는 방법 중 하나이다.
- 구현 클래스에 의존하지 말고, 인터페이스에 의존.

❗ DIP 위반
- OCP에서 설명한 MemberService는 인터페이스에 의존하지만, 구현 클래스도 동시에 의존한다.
- 위반

## ✔ 정리
- 객체 지향의 핵심은 다형성
- 다형성만으로는 쉽게 부품을 갈아 끼우듯이 개발할 수 없다.
- 다형성만으로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경된다.
- 다형성만으로는 OCP, DIP를 지킬 수 없다.
- 뭔가 더 필요하다.

#### 스프링
- 스프링은 다음 기술로 OCP, DIP를 가능하게 지원
  - DI 의존관계, 의존성 주입
  - DI 컨테이너 제공
- 클라이언트 코드 변경업쇼이 기능 확장
- 쉽게 부품을 교체하듯 개발가능

  </div>
</details>
  

</div>
</details>

<details>

<summary>  스프링 컨테이너와 스프링 빈 </summary>
<div markdown="1">

## 스프링 컨테이너 생성

``` JAVA
//스프링 컨테이너 생성
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

- `ApplicationContext`를 스프링 컨테이너라 한다.
- `ApplicationContext`는 인터페이스다.
- 스프링 컨테이너는 XML을 기반으로 만들 수 있고, 애노테이션 기반의 자바 설정 클래스로 만들 수 있다.
- [AppConfig](core/src/main/java/hello/core/AppConfig.java)를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든것이다.

> 스프링 컨테이너를 부를 때 `BeanFactory`, `ApplicationContext`로 구분해서 이야기한다. 일반적으로 `ApplicationContext`를 스프링 컨테이너라고 한다.

## 스프링 컨테이너의 생성 과정
#### 1️⃣ 스프링 컨테이너 생성

![image](https://user-images.githubusercontent.com/87464750/180954942-529ce696-5144-45aa-a63f-b770c7d60214.png)

- `new AnnotationConfigApplicationContext(AppConfig.class)`
- 스프링 컨테이너를 생성할 때는 구성 정보를 지정해주어야 한다.
- 여기서는 `AppConfig.class`를 구성 정보로 지정했다.

#### 2️⃣ 스프링 빈 등록

![image](https://user-images.githubusercontent.com/87464750/180955502-f7bebbbc-c389-4674-b186-15aa8b8e71e3.png)

- 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록한다.

##### 빈 이름
- 빈 이름은 메서드 이름을 사용한다.
- 빈 이름을 직접 부여할수도 있다.
   - ` @Bean(name = "직접 부여할 이름") `
 
❗ 주의: 빈 이름은 항상 다른 이름을 부여해야 한다. 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.

#### 3️⃣ 스프링 빈 의존관계 설정 - 준비

![image](https://user-images.githubusercontent.com/87464750/180956281-7ee456c1-9fea-4aa7-84db-c05b16249ad9.png)

#### 4️⃣ 스프링 빈 의존관계 설정 - 완료

![image](https://user-images.githubusercontent.com/87464750/180956377-78caf41e-4b05-4901-8553-ec8e1fb6e236.png)

- 스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)한다.
- 단순히 자바 코드를 호출하는 것 같지만, 차이가 있다.

> 스프링 빈을 생성하고, 주입하는 단계가 나누어져 있다. 그런데 이렇게 자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다. 

## 빈 조회
#### 1️⃣ 컨테이너에 등록된 빈 조회
#### 모든 빈 출력하기
- 실행하면 스프링에 등록된 모든 빈 정보를 출력할 수 있다.
- `ac.getBeanDefinitionNames`: 스프링에 등록된 모든 빈 이름을 조회한다.
- `ac.getBean()`: 빈 이름으로 빈 객체(인스턴스)를 조회한다.

#### 애플리케이션 빈 출력하기
- 스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력
- 스프링이 내부에서 사용하는 빈은 `getRole()`로 구분할 수 있다.
    - `ROLE_APPLICATION`: 일반적으로 사용자가 정의한 빈
    - `ROLE_INFRASTRUCTURE`: 스프링이 내부에서 사용하는 빈
    
#### 2️⃣ 스프링 빈 조회 -기본
#### 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
- `ac.getBean(빈이름, 타입)`
- `ac.getBean(타입)`
#### 조회 대상 스프링 빈이 없으면 예외 발생
- `NoSuchBeanDefinitionException: No bean named 'XXXX' available

[예제코드](core/src/test/java/hello/core/beanfind/ApplicationContextBasicFindTest.java)

> 구체 타입으로 조회하면 변경시 유연성이 떨어진다.

#### 3️⃣ 스프링 빈 조회 - 동일한 타입이 둘 이상
- 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다.
    - 빈이름을 지정
- `ac.getBeanOfType()`을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.

[예제코드](core/src/test/java/hello/core/beanfind/ApplicationContextSameBeanFindTest.java)

#### 4️⃣ 스프링 빈 조회 - 상속관계
- 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
- 그래서 모든 자바 객체의 최고 부모인 `Object` 타입으로 조회하면, 모든 스프링 빈을 조회한다.

![image](https://user-images.githubusercontent.com/87464750/180959446-881e33e5-3221-4fe0-9814-b01e5c4b351a.png)

[예제코드](core/src/test/java/hello/core/beanfind/ApplicationContextExtendsFindTest.java)

## BeanFactory와 ApplicationContext
![image](https://user-images.githubusercontent.com/87464750/180959790-b12c67d7-87a7-40e8-b441-9249d1d1cf0d.png)

#### BeanFactory
- 스프링 컨테이너의 최상위 인터페이스다.
- 스프링 빈을 관리하고 조회하는 역할을 담당한다.
- `getBean()`을 제공한다.
- 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능이다.

#### ApplicationContext
- BeanFactory 기능을 모다 상속받아서 제공한다.
- 빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주는, 그 둘의 차이는?
- 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능은 물론이고, 수 많은 부가기능이 필요하다.

`ApplicationContext`가 제공하는 부가기능
  
![image](https://user-images.githubusercontent.com/87464750/180960514-267a8150-d995-40f3-ab9f-0014f6da1dfd.png)

- 메시지소스를 활용한 국제화 기능
    > 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력
- 환경변수
    > 로컬, 개발, 운영등을 구분해서 처리
- 애플리케이션 이벤트
    > 이벤트를 발생하고 구독하는 모델을 편리하게 지원
- 편리한 리소스 조회
    > 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회
    
### 💡 정리
- ApplicationContext는 BeanFactory의 기능을 상속받는다.
- ApplicationContext는 빈 관리기능 + 편리한 부가 기능을 제공한다.
- BeanFactory를 직접 사용할 일은 거의 없다. 부가기능이 포함된 ApplicationContext를 사용한다.
- BeanFactory나 ApplicationContext를 스프링 컨테이너라 한다.


  </div>
</details>

<details>

<summary>  싱글톤 컨테이너 </summary>
<div markdown="1">



#### ❗ `싱글톤 패턴` 문제점
- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
- 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP 위반
- 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
- 테스트하기가 어렵다.

## 싱글톤 컨테이너
- 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
    - 컨테이너는 객체를 하나만 생성해서 관리.
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다.
    - 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
- 스프링 컨테이너의 이런 기능 덕분에 싱글톤 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다.
    - 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다
    - DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.
    
 #### 싱글톤 컨테이너 적용 후
 ![image](https://user-images.githubusercontent.com/87464750/181879242-6397b806-31a6-4201-a1bf-ddd69b06111d.png)

> 참고: 스프링 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 것은 아니다. 요청할때 마다 새로운 객체를 생성해서 반환하는 기능도 제공한다.

### ❗ 싱글톤 방식의 주의점
- 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 stateful하기 설계하면 안된다.
- stateless로 설계해야 한다.
    - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
    - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
    - 가급적 읽기만 가능해야 한다.
    - 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
- 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다.

## @Configuration과 바이트코드
- `@Configuration`을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장.

![image](https://user-images.githubusercontent.com/87464750/181879475-811d6b03-a81b-40ef-a6c7-4de5f96c1426.png)

- 임의의 다른 클래스가 싱글톤이 보장되도록 해줌.


``` JAVA
@Bean
public MemberRepository memberRepository() {
 
 if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
      return 스프링 컨테이너에서 찾아서 반환;
 } else { //스프링 컨테이너에 없으면
      기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
       return 반환
   }
}
```
- @Bean 이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
- 덕분에 싱글톤이 보장된다.

#### ❓ `@Configuration`을 적용하지 않고, `@Bean`만 적용하면?
- 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
    - 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.

  </div>
</details>

<details>

<summary>  컴포넌트 스캔 </summary>
<div markdown="1">

## 컴포넌트 스캔과 의존관계 자동주입

</br>등록해야 할 스프링 빈이 수십, 수백개가 되면 일일이 등록하기 귀찮고, 설정 정보도 커지고, 누락하는 문제도 발생한다.
그래서 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다.
또, 의존관계도 자동으로 주입하는 `@Autowired` 라는 기능도 제공한다.

[AutoAppConfig](core/src/main/java/hello/core/AutoAppConfig.java)

- 컴포넌트 스캔을 사용하려면 먼저 `@Componentscan` 을 설정 정보에 붙여주어야 한다.
- 기존의 [AppConfig](core/src/main/java/hello/core/AppConfig.java) 와는 다르게 @Bean으로 등록한 클래스가 하나도 없다.

> 참괴 컴포넌트 스캔을 사용하면 `@Configuration`이 붙은 설정 정보도 자동으로 등록되기 때문에, </br> AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다. </br> 그래서 `excludeFilters`를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외한다. 보통 설정 정보를 컴포넌트 스캔 대상에서</br> 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택

- 컴포넌트 스캔은 이름 그대로 `@Component` 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.

> `@Configuration`이 컴포넌트 스캔의 대상이 된 이유도 `@Configuration` 소스코드를 열어보면 `@Component` 애노테이션이 붙어있기 때문이다.

- 각 클래스가 컴포넌트 스캔의 대상이 되도록 `@Component` 애노테이션을 붙여준다.
- 이전에 [AppConfig](core/src/main/java/hello/core/AppConfig.java) 에서는 `@Bean`으로 직접 설정 정보를 작성했고, 의존관계도 직접 명시했다. 이제는 설정정보 자체가 없기 때문에, 의존관계 주입도 클래스 안에서 해결해야 한다.
- `@Autowired`는 의존관계를 자동으로 주입해준다.
- `@Autowired`를 사용하면 생성자에서 여러 의존관계도 한번에 주입 받을 수 있다.

#### 1️⃣ @ComponentScan
![image](https://user-images.githubusercontent.com/87464750/181229541-c80cd20e-5b20-420f-a0db-48f1ba63a69c.png)

- `@ComponentScan`은 `@Component`가 붙은 모든 클래스를 스프링 빈으로 등록한다.
- 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
    - 빈 이름 기본 전략: MemberServiceImol 클래스 -> memberServiceImpl
    - 빈 이름 직접 지정: 만약 스프링 이름을 직접 지정하고 싶으면 `@Component("memberService2)` 이런식으로 부여하면 된다.
    
#### 2️⃣ @ Autowired 의존관계 자동 주입
![image](https://user-images.githubusercontent.com/87464750/181230105-09acb29e-ddf2-4fed-af29-0401646eef4f.png)

- 생성자에서 `@Autowrired` 를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
- 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
    - `getBean(MemberRepository.class)` 와 동일하다고 이해하면 됨.
    
![image](https://user-images.githubusercontent.com/87464750/181230368-16c24039-d5af-47f7-a31a-798c8f9a8a7e.png)

- 생성자에 파라미터가 많아도 다 찾아서 자동으로 주입한다.

## 탐색 위치와 기본 스캔 대상
#### 탐색할 위치와 기본 스캔 대상

##### 탐색할 패키지의 시작 위치 지정
- 모든 자바 클래스를 다 컴포넌트 스캔하면 시간이 오래 걸린다. 그래서 꼭 필요한 위치부터 탐색하도록 시작 위치를 지정할 수 있다.

``` JAVA
@ComponentScan(
 basePackages = "hello.core",
}
```

- `basePakages`: 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
    - `basePackages = {"hello.core", "hello.service"}` 이렇게 여러 시작 위치를 지정할 수도 있다.
- `basePackageClasses`: 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
- 만약 지정하지 않으면 `@ComponentScan`이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

##### ✔ 권장하는 방법
- 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것.

#### 컴포넌트 스캔 기본 대상
- 컴포넌트 스캔은 `@Component` 뿐만 아니라 다음과 같은 내용도 추가로 대상에 포함된다.
    - `@Component`: 컴포넌트 스캔에서 사용
    - `@Controller`: 스프링 MVC 컨트롤러에서 사용
    - `@Service`: 스프링 비즈니스 로직에서 사용
    - `@Reposiotry`: 스프링 데이터 접근 계층에서 사용
    - `@Configuration`: 스프링 설정 정보에서 사용 
    
## 필터
- `includeFilters` : 컴포넌트 스캔 대상을 추가로 지정한다.
- `excludeFilter`s : 컴포넌트 스캔에서 제외할 대상을 지정한다

#### FilterType 옵션
FilterType은 5가지 옵션이 있다.
- `ANNOTATION`: 기본값, 애노테이션을 인식해서 동작한다.
    - ex) `org.example.SomeAnnotation`
- `ASSIGNABLE_TYPE`: 지정한 타입과 자식 타입을 인식해서 동작한다.
    - `ex) org.example.SomeClass`
- `ASPECTJ`: AspectJ 패턴 사용
    - `ex) org.example..*Service+`
- `REGEX`: 정규 표현식
    - `ex) org\.example\.Default.*`
- `CUSTOM`: TypeFilter 이라는 인터페이스를 구현해서 처리
    - `ex) org.example.MyTypeFilter`
    

  </div>
</details>

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
[링크로 연결](JAVA.md)
    </div>
</details>

  </div>
</details>

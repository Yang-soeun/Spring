 ## ✏ 스프링 핵심 원리 - 기본
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

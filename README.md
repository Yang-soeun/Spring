
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

<details>
  <summary> Exception in thread "main" java.lang.NoClassDefFoundError: javax/xml/bind/JAXBException </summary>
  <div markdonw = "2">
  
  - JAVA11에서 발생
  
  ![image](https://user-images.githubusercontent.com/87464750/192098990-1a614e1b-08f7-4006-861b-768c0016136e.png)

-  해결방법
- pom.xm1에 코드 추가

```
<dependency>
    <groupId>javax.xml.bind</groupId>
     <artifactId>jaxb-api</artifactId>
    <version>2.3.0</version>
 </dependency>
```

  </div>
</details>

<details>
  <summary> H2 Database 연결(not found 오류❗) </summary>
  <div markdonw = "2">
  
 ### 1️⃣ bin-> h2.bat 실행
 ### 2️⃣ 데이터베이스 생성
  ![image](https://user-images.githubusercontent.com/87464750/198990301-32baf5a1-3efb-41d2-80a8-bf7ccc38593c.png)

- Generic H2(Embedded) 선택
- JDBC URL에 jdbc:h2~/(원하는 데이터 베이스 이름) 입력

### 3️⃣ Generic H2(Server)로 변경하고 접속

   </div>
</details>

<details>
  <summary> No runnable methods </summary>
  <div markdonw = "2">
  
  ![image](https://user-images.githubusercontent.com/87464750/201090863-57421e4a-1895-4fac-9295-d693dc005017.png)

### 해결방법
#### import org.junit.jupiter.api.Test; -> import org.junit.Test;로 변경하기

   </div>
</details>


</div>
</details>

<details>

<summary>⭐ MySQL 연결 </summary>
<div markdown="1">

1️⃣ build.gradle
```
dependencies {
	implementation 'mysql:mysql-connector-java'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}
```

2️⃣ resources -> application.properties
```
# MySQL ??
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# DB Source URL
spring.datasource.url=jdbc:mysql://localhost:3306/<dbname>?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul

# DB username
spring.datasource.username=root

# DB password
spring.datasource.password=1234

# true ??? JPA ??? ?? ??
spring.jpa.show-sql=true

# DDL(create, alter, drop) ??? DB? ?? ??? ??? ? ??.
spring.jpa.hibernate.ddl-auto=update

# JPA? ???? Hibernate? ????? ??? SQL? ???? ????.
spring.jpa.properties.hibernate.format_sql=true
```

  </div>
</details>

<details>

<summary> ⭐ gradle dependency </summary>
<div markdown="1">

### oauth2-client dependency
```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
}
```
  </div>
</details>

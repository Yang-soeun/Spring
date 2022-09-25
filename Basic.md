<details>

<summary> 📑 영속성 관리 </summary>
<div markdown="1">
  
### 영속성 컨텍스트
- 엔터티를 영구 저장하는 환경
- `EntityManager.persist(entity);`
  
### 엔터티의 생명주기
- 비영속(new/transient)
    - 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 영속(managed)
    - 영속성 컨텍스트에 관리되는 상태
- 준영속(detached)
    - 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제(removed)
    - 삭제된 상태

![image](https://user-images.githubusercontent.com/87464750/192141273-36d8472b-6da9-441a-9783-51622b5d15a3.png)

  
### 비영속
![image](https://user-images.githubusercontent.com/87464750/192141296-613e0484-8b60-4f91-8a41-cfd09994c6cd.png)

```
  //객체를 생성한 상태(비영속)
  Member member = new Member();
  member.setId("memberId");
  member.setUsername("회원");
```
  
### 영속
![image](https://user-images.githubusercontent.com/87464750/192141343-d54f41f1-f85e-45b6-b651-258b53dc77aa.png)

```
//객체를 생성한 상태(비영속) 
Member member = new Member(); 
member.setId("member1"); 
member.setUsername(“회원1”);
  
EntityManager em = emf.createEntityManager();
em.getTransaction().begin();
  
//객체를 저장한 상태(영속)
em.persist(member);

```
  
### 준영속, 삭제
#### 준영속 상태로 만드는 방법
- em.detach(emtity)
    - 특정 엔터티만 준영속 상태로 전환
- em.clear()
    - 영속성 컨텍스트를 완전히 초기화
- em.close()
    - 영속성 컨텍스트를 종료
  
```
  //회원 엔터티를 영속성 컨텍스트에서 분리, 준영속 상태
  em.detach(member);
  
  //객체를 삭제한 상태(삭제)
  em.remove(member);
```
  
### 💡 영속성 컨텍스트의 이점
- 1차 캐시
- 동일성 보장
- 트랜잭션을 지원하는 쓰기 지연
- 변경 감지
- 지연 로딩
  
### 플러시
- 영속성 켄텍스트의 변경 내용을 데이터베이스에 반영
- 플러시는 영속성 컨텍스트를 비우지 않음
- 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
- 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨
  
### 플러시 발생
- 변경 감지
- 수정된 엔터티 쓰기 지연 SQL 저장소에 등록
- 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송(등록, 수정, 삭제 쿼리)
  
### 영속성 컨텍스트를 플러시하는 방법
- em.flush() -> 직접 호출
- 트랜잭션 커밋 -> 플러시 자동 호출
- JPQL 쿼리 실행 -> 플러시 자동 호출
  
### 플러시 모드 옵션
- `em.setFlushMode(FlushModeType.COMMIT)`
- `FlushModeType.AUTO`
    - 커밋이나 쿼리를 실행할때 플러시함 -> 기본값
- `FlushModeType.COMMIT`
    - 커밋할때만 플러시
  
  </div>
</details>

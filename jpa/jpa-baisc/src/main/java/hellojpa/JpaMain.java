package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//하나만 만들기

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();//꼭 트랜잭션 안에서 작업을 해야함
        tx.begin();

        //code
        try{

//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//
//            em.persist(member);//멤버 저장방법

            //수정할떄
            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.getName = " + findMember.getName());
            findMember.setName("HelloJPA");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally{
            em.close();//사용 후 꼭 닫아야함
        }

        emf.close();

    }
}

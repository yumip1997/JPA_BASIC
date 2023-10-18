package com.hello.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        // 트랜잭션 시작
        tx.begin();
        try{
            // JPQL : 객체지향적 SQL
            // 페이징 관련 설정을 해주면 JPA가 벤더 사의 페이징 방언에 따라 쿼리를 만들어준다.
            // DB 벤더 사가 바뀌어도 코드는 수정하지 않아도 됨
            List<Member> memberList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(3)
                    .getResultList();

            for (Member member1 : memberList) {
                System.out.println(member1.getName());
            }

            //트랜잭션 커밋
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

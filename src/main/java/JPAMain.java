import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em  = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
        }catch (Exception e){
          tx.rollback();
        }finally {
            tx.commit();
        }
    }
}

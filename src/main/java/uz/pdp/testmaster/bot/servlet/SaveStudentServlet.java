package uz.pdp.testmaster.bot.servlet;

import jakarta.persistence.EntityManager;
import uz.pdp.testmaster.bot.entity.TgStudent;

import static uz.pdp.testmaster.web.util.MyListener.EMF;

public class SaveStudentServlet {
    public static void saveUserToDB(TgStudent student) {
        try (
                EntityManager em = EMF.createEntityManager()
        ) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

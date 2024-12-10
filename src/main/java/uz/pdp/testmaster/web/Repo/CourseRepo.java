package uz.pdp.testmaster.web.Repo;

import jakarta.persistence.EntityManager;
import uz.pdp.testmaster.web.entity.Course;
import uz.pdp.testmaster.web.entity.Groups;
import uz.pdp.testmaster.web.util.MyListener;

import javax.servlet.http.HttpServlet;

import java.util.List;

import static uz.pdp.testmaster.web.util.MyListener.EMF;


public class CourseRepo extends HttpServlet {
    public static List<Course> getCoursesList() {
        try (
                EntityManager em = EMF.createEntityManager();

        ) {
            return em.createQuery("select c from Course c", Course.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Course getById(Integer courseId) {
        try (
                EntityManager em = EMF.createEntityManager();

        ) {
            return em.find(Course.class, courseId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package uz.pdp.testmaster.web.Repo;

import jakarta.persistence.EntityManager;
import uz.pdp.testmaster.web.entity.Groups;

import java.util.List;

import static uz.pdp.testmaster.web.util.MyListener.EMF;

public class GroupRepo{
    public static List<Groups> getAllGroups() {
        try (
                EntityManager em = EMF.createEntityManager();

        ) {
            return em.createQuery("select g from Groups g", Groups.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Groups getById(Integer groupId) {
        try (
                EntityManager em = EMF.createEntityManager();

        ) {
            return em.find(Groups.class, groupId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

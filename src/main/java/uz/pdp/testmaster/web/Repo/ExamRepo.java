    package uz.pdp.testmaster.web.Repo;

    import jakarta.persistence.EntityManager;
    import jakarta.persistence.Query;
    import uz.pdp.testmaster.web.entity.Exam;

    import java.util.List;

    import static uz.pdp.testmaster.util.MyListener.EMF;

    public class ExamRepo {
        public static void save(Exam exam) {
            try (
                    EntityManager em = EMF.createEntityManager();

            ) {
                em.getTransaction().begin();
                em.persist(exam);
                em.getTransaction().commit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static List<Exam> getAllExams(String search, int pagejon, String sortOrder) {
            try (EntityManager em = EMF.createEntityManager()) {

                // "sortOrder" yordamida tartibni aniqlash
                String orderBy = "e.date " + (sortOrder.equals("desc") ? "DESC" : "ASC");

                // JPQL so'rovini yaratish
                return em.createQuery(
                                "select e from Exam e where  LOWER(e.group.groupName)  like LOWER(CONCAT('%', :search, '%')) order by " + orderBy,
                                Exam.class)
                        .setParameter("search", search)
                        .setFirstResult(pagejon * 5) // Offset
                        .setMaxResults(5) // LIMIT
                        .getResultList();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static long count(String search) {
            try (EntityManager em = EMF.createEntityManager()) {
                Query selectFromGroups = em.createQuery("SELECT count(s) FROM Exam s WHERE LOWER(s.group.groupName) LIKE LOWER(CONCAT('%', :search, '%'))", Long.class)
                        .setParameter("search", search);
                return (Long) selectFromGroups.getSingleResult();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static Exam getById(Integer examId) {
            try (
                    EntityManager em = EMF.createEntityManager();

            ) {
                return em.find(Exam.class, examId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

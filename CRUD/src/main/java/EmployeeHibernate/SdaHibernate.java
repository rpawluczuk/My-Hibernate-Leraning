package EmployeeHibernate;

        import entity.Student;
        import org.hibernate.Session;
        import org.hibernate.Transaction;

        import java.util.List;

public class SdaHibernate implements SdaHibernateInterface{


    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            transaction = null;
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
            transaction = null;
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

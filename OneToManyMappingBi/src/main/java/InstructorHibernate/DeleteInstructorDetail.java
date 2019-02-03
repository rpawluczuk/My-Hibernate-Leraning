package InstructorHibernate;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetail {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 7;
            InstructorDetail instructorDetail =
                    session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("InstructorDetail: " + instructorDetail);

            // print the associated instructor
            System.out.println("the associate instructor: " +
                    instructorDetail.getInstructor());

            // delete instructor detail
            System.out.println("Deleting instructor detail " +
                    instructorDetail);

            // remove tha associated object reference
            // break bi-direc
            instructorDetail.getInstructor().setInstructorDetail(null);
            session.delete(instructorDetail);

            // commit transaction
            Transaction t = session.getTransaction();
            t.commit();

            System.out.println("Done!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            // handle connection leak issue
            session.close();

            factory.close();
        }
    }
}






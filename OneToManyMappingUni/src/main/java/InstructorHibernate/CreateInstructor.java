package InstructorHibernate;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructor {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a object
            Instructor instructor =
                    new Instructor("Radek",
                            "Pawluczuk",
                            "rpaw@gmail.com");

            InstructorDetail instructorDetail =
                    new InstructorDetail(
                            "www.rpaw.com/youtube",
                            "kalistenika");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            // Note: this will also save  the details object
            // becouse of CascadeType.All

            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            // add clean up code
            session.close();

            factory.close();
        }
    }
}






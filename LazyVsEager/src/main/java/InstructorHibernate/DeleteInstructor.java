package InstructorHibernate;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteInstructor {

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

            // get instructor by primary key
            int theId = 3;

            Instructor instructor = session.get(Instructor.class, theId);

            System.out.println("Found instructor: " + instructor);

            // delete the instructors
            if (instructor != null){
                System.out.println("Deleting: " + instructor);

                // Note: will also delete associated "details" object
                // because of CascadeType.ALL

                session.delete(instructor);
            }
            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}






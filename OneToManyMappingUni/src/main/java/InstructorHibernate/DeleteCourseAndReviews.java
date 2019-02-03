package InstructorHibernate;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviews {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the course
            int theId = 10;
            Course course = session.get(Course.class, theId);

            // print the course
            System.out.println(course);

            // print the course reviews
            System.out.println(course.getReviews());

            // delete the course
            session.delete(course);

            // commit transaction
            session.getTransaction().commit();

            session.close();

            System.out.println("Done!");
        } finally {
            // add clean up code
            session.close();

            factory.close();
        }
    }
}

package InstructorHibernate;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesToMarek {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the student Marek from database
            int studentId = 2;
            Student student = session.get(Student.class, studentId);

            System.out.println("\nLoaded student: " + student);
            System.out.println("Courses: " + student.getCourses());

            // create more courses
            Course course1 = new Course("Improve your english");
            Course course2 = new Course("Minecraft for serious players");

            // add student to courses
            course1.addStudent(student);
            course2.addStudent(student);

            // save the courses
            System.out.println("\nSaving the courses...");
            session.save(course1);
            session.save(course2);

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

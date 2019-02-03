package InstructorHibernate;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudent {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudent = session.createQuery("from Student").list();

            // display the students
            displayTheStudents(theStudent);

            // query students: lastName= 'Doe'
            theStudent = session.createQuery("from Student s where s.lastName='Duck'").list();

            System.out.println("\nStudents who have last name of Duck");
            displayTheStudents(theStudent);

            // query students: lastName='Pawluczuk' OR firstaname= 'Duffy'
            theStudent = session.createQuery("from Student s where s.lastName='Pawluczuk' or s.firstName='Duffy'").list();

            System.out.println("\nStudents who have last name of Pawluczuk or first name of Duffy");
            displayTheStudents(theStudent);

            // query students where email like %gmail.com
            theStudent = session.createQuery("from Student s where s.email like '%gmail.com'").list();

            System.out.println("\nStudents where email ends with gmail.com");
            displayTheStudents(theStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayTheStudents(List<Student> theStudent) {
        for (Student tempStudent : theStudent){
            System.out.println(tempStudent);
        }
    }
}






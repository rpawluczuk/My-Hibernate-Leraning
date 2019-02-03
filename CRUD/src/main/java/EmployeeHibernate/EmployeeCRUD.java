package EmployeeHibernate;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class EmployeeCRUD {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            createEmployee(session, "Tomasz", "Bouszewski", "SMP");
            readEmployeeById(factory.getCurrentSession(), 1);
            findEmployeeByCompany(factory.getCurrentSession(), "SMP");
            deleteEmployeeById(factory.getCurrentSession(), 2);
        }
        finally {
            factory.close();
        }
    }

    private static void createEmployee(Session session, String firstName, String lastName, String company) {
        // create a student object
        System.out.println("Creating new employee object...");
        Employee tempEmployee = new Employee(firstName, lastName, company);

        // start a transaction
        session.beginTransaction();

        // save the student object
        System.out.println("Saving the student...");
        session.save(tempEmployee);

        // commit transaction
        session.getTransaction().commit();

        System.out.println("Done!");
    }

    private static void readEmployeeById(Session session, int id) {

        // start a transaction
        session.beginTransaction();

        // retrieve employee based on the id: primary key
        System.out.println("\nGetting employee with id: " + id);

        Employee myEmployee = session.get(Employee.class, id);

        System.out.println("Get complete: " + myEmployee);
        // commit transaction
        session.getTransaction().commit();

        System.out.println("Done!");
    }

    private static void findEmployeeByCompany(Session session, String company) {

        // start a transaction
        session.beginTransaction();

        // query students
        List<Employee> theEmployee = session.createQuery("from Employee e where e.company='" + company +"'").getResultList();

        // retrieve employee based on the id: primary key
        System.out.println("\nGetting employee with company: " +company);

        for (Employee tempEmployee : theEmployee){
            System.out.println(tempEmployee);
        }

        session.getTransaction().commit();

        System.out.println("Done!");
    }

    private static void deleteEmployeeById(Session session, int id) {

        // start a transaction
        session.beginTransaction();

        // retrieve employee based on the id: primary key
        System.out.println("\nGetting employee with id: " + id);

        Employee myEmployee = session.get(Employee.class, id);

        // delete student id=2
        System.out.println("Deleting employee id = 2");
        session.createQuery("delete from Employee where id=2").executeUpdate();

        System.out.println("You delete employee: " + myEmployee);
        // commit transaction
        session.getTransaction().commit();

        System.out.println("Done!");
    }

}

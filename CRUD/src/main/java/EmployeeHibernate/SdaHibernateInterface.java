package EmployeeHibernate;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface SdaHibernateInterface {

    public List<Student> findAll();

    public Student findById(Long id);

    public void save(Student student);

    public void update(Student student);

    public void delete(Student student);
}

package com.ky.hibernate.demo;

import com.ky.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session

        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object

            // create a student object
            System.out.println("Create Student Object");
            Student student = new Student("Paul", "Wall", "paulwall@gmail.com");

            // start a transaction
            session.beginTransaction();

            // query the student object
            System.out.println("Querying All Student Object");
            List<Student> list = session.createQuery("from Student").getResultList();

            displayStudents(list);

            System.out.println("Querying Student whose last name is Doe");
            // query the student object with lastName = "Doe"
            list = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();

            displayStudents(list);

            // query the student object with lastName == "Doe" or firstName = 'Daffy'
            list = session.createQuery("from Student s"
                                        + " where s.lastName = 'Doe' or s.firstName = 'Daffy'")
                                        .getResultList();
            displayStudents(list);

            list = session.createQuery("from Student s where s.email"
                                    + " LIKE '%gmail.com'")
                                    .getResultList();
            displayStudents(list);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Commit Successful");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> list) {
        for (Student s : list) {
            System.out.println(s);
        }
    }

}

package com.ky.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ky.hibernate.demo.entity.Student;

public class CreateStudentDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
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
            // save the student object
            System.out.println("Saving Student Object");
            session.save(student);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Commit Successful");
        }
        finally {
            factory.close();
        }
    }

}

package com.ky.hibernate.demo;

import com.ky.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session

        Session session = factory.getCurrentSession();

        try {
            // get new session and start transaction
            int id = 1;
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("Getting student with id: " + id);

            Student myStudent = session.get(Student.class, id);

            System.out.println("Get complete: " + myStudent);

            // set the new name
            System.out.println("Update with new name: ");
            myStudent.setFirstName("Rick");
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");


            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Updating email for all student");
            session.createQuery("update Student set email='foo@g2.gmail.com'").executeUpdate();

            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}

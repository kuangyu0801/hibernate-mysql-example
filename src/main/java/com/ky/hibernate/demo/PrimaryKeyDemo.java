package com.ky.hibernate.demo;

import com.ky.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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
            Student student1 = new Student("John", "Doe", "john@gmail.com");
            Student student2 = new Student("Mary", "Public", "mary@gmail.com");
            Student student3 = new Student("Bonita", "Applebum", "bonita@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            System.out.println("Saving Student Object");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Commit Successful");


            // get new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on the id: primary key
            System.out.println("Getting student with id: " + student1.getId());

            Student myStudent = session.get(Student.class, student1.getId());

            System.out.println("Get complete: " + myStudent);

            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}

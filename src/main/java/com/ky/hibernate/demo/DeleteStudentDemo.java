package com.ky.hibernate.demo;

import com.ky.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteStudentDemo {

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

            System.out.println("Delete");

            // delete student
            // session.delete(myStudent);

            // delete student with query
            System.out.println("Deleting with query");

            session.createQuery("delete from Student where id=2").executeUpdate();

            // commit the transaction
            session.getTransaction().commit();
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

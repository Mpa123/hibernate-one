package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	
	public static void main(String[] args) {
		
		//create session factory heavy weight object - created only once in program
		SessionFactory sf = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session sess = sf.getCurrentSession();
		
		
		try {
			
			Student newStudent = new Student("Paul", "Wall", "paul@mail.com");
			//open transaction for saving
			sess.beginTransaction();
			
			//save object
			sess.save(newStudent);
			
			//commit transaction
			sess.getTransaction().commit();
			
			//get students id
			System.out.println("Saved student. Generated id: " + newStudent.getId());
			
			//create new session for read
			sess = sf.getCurrentSession();
			sess.beginTransaction();
			
			//read object from database
			Student stud1 = sess.get(Student.class, newStudent.getId());
			
			System.out.println("Get complete: " + stud1.toString());
			
			//commit transaction
			sess.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			//close session
			sf.close();
		}
	}

}

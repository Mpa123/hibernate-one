package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	
	public static void main(String[] args) {
		
		
		SessionFactory sf = new Configuration()
				.configure()
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session sess = sf.getCurrentSession();
		
		try {
			
			sess.beginTransaction();
			
			List<Student> list = sess.createQuery("FROM Student WHERE lastName LIKE 'Wa%' ").getResultList();
			
			displayStudents(list);
			
			//create new query
			list = sess.createQuery("FROM Student s"
					+ " WHERE s.lastName LIKE 'John%'").getResultList();
			displayStudents(list);
			
			sess.getTransaction().commit();
		}
		finally {
			sf.close();
		}
	}

	private static void displayStudents(List<Student> list) {
		for(Student stud : list) {
			System.out.println("student: " + stud.toString());
		}
	}

}

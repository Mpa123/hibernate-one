package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	
	public static void main(String[] args) {
		
		
		//create session factory
		SessionFactory sf = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session sess = sf.getCurrentSession();
		
		try {
			sess.beginTransaction();
			
			int studId = 3;
			
			Student stud = sess.get(Student.class, studId);
			stud.setFirstName("Thomas");
			
			sess.getTransaction().commit();
			
			//new session
			sess = sf.getCurrentSession();
			sess.beginTransaction();
			
			// update email for all students
			sess.createQuery("UPDATE Student SET email='foo@gmail.com'"
					+ "WHERE lastName LIKE 'Wa%'").executeUpdate();
			
			sess.getTransaction().commit();
			
		}
		finally {
			sf.close();
		}
		
	}

}

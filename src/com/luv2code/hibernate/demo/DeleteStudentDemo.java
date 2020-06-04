package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	
	public static void main(String[] args) {
		
		//create sessionfactory object
		SessionFactory sf = new Configuration()
				.configure()
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		
		//work with DB
		try {
			
			//create session
			Session sess = sf.getCurrentSession();
			
			// begin transaction
			sess.beginTransaction();
			
			//delete, using executeUpdate()
			sess.createQuery("DELETE FROM Student s WHERE s.id = 4" ).executeUpdate();
			
			//commit
			sess.getTransaction().commit();
			
			//create new session, delete without query
			sess = sf.getCurrentSession();
			sess.beginTransaction();
			int studId = 1;
			Student stud = sess.get(Student.class, studId);
			sess.delete(stud);
			sess.getTransaction().commit();
			
			
		}
		finally {
			sf.close();
		}
	}

}

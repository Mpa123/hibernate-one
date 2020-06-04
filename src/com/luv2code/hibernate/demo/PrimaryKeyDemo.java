package com.luv2code.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//create Session Factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//creating 3 student objects
			List<Student> studentList = new ArrayList<>();
			Student student1 = new Student("Anthony", "Johnson", null, "rumbe@mail.com");
			Student student2 = new Student("Anthony", "Joshua", null, "joshua@mail.com");
			Student student3 = new Student("Bruce", "Wayne", null, "bruce@mail.com");
			studentList.add(student1);
			studentList.add(student2);
			studentList.add(student3);
			
			//open transaction
			session.beginTransaction();
			
			//save object
			for (Student s : studentList) {
				session.save(s);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
		}
		finally {
			//close session
			factory.close();
		}

	}

}

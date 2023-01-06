package com.acc.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.acc.entity.Student;
import com.acc.interfaces.Manager;

public class HibernateManager implements Manager {
	
	SessionFactory f;
	Session session;
	Transaction t;
	public void connect() {
//		Configuration conf = new Configuration();
//		Configuration x = conf.configure("hibernate.cfg.xml");
		
		//SessionFactory
		//Build SessionFactory with config of xml and entity.class
		f = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//Session
		//Retrieve session from SessionFactory
		session = f.getCurrentSession();
		
		System.out.println("Connected to database");
		System.out.println(f);
		
	}
	
	public void addStudent(Student s) {
		session = f.getCurrentSession();
		Transaction t = session.beginTransaction();
		session.save(s);
		t.commit();
		System.out.println("Student data added");
	}
	
	public void fetchByFName(String sfn) {
		session = f.getCurrentSession();
		t = session.beginTransaction();
		List<Student> details = session.createQuery("from Student s where s.sfn='Tim'").list();
		
		for(Student s : details) {
			System.out.println(s.toString());
		}
		t.commit();
	}
	
	public void fetchByID(String sid) {
		session = f.getCurrentSession();
		t = session.beginTransaction();
		Student s= (Student) session.get(Student.class, sid);
		t.commit();
	}
	
	public void fetchAllStudent() {
		session = f.getCurrentSession();
		t = session.beginTransaction();
		List<Student> details = session.createQuery("from Student").list();
		
		for(Student s : details) {
			System.out.println(s.toString());
		}
		t.commit();
	}
	
	public void updateEmail(String email, int sid) {
		session = f.getCurrentSession();
		t = session.beginTransaction();
		Student s= (Student) session.get(Student.class, sid);
		System.out.println("Old email: "+s.getSemail());
		s.setSemail(email);
		t.commit();
		dispChanges(sid);
//		session = f.getCurrentSession();
//		t = session.beginTransaction();
//		s= (Student) session.get(Student.class, sid);
//		System.out.println("Current email: "+s.getSemail());
//		t.commit();
//		
	}
	
	public void dispChanges(int sid) {
		session = f.getCurrentSession();
		t = session.beginTransaction();
		Student s= (Student) session.get(Student.class, sid);
		System.out.println("Current email: "+s.getSemail());
		t.commit();
	}
	
	public void deleteByID(int sid) {
		session = f.getCurrentSession();
		t = session.beginTransaction();
//		Student s= (Student) session.get(Student.class, sid);
//		session.delete(s);
		session.delete(session.get(Student.class, sid));
		t.commit();
		System.out.println("Student ID #" + sid + " Deleted");
	}
}

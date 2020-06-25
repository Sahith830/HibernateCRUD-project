package com.testing.DemoProject;

import java.util.Scanner;
import org.hibernate.Session;
import service.Student;


public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	Session session = util.HibernateUtil.getSessionFactory().openSession();
    	System.out.println("1.Create a student\n2.Retrieve a student by id\n3.Delete a student\n4.Update a student\nEnter your choice : ");
    	Scanner sc = new Scanner(System.in);
    	int choice = sc.nextInt();
    	switch(choice) {
    	case 1 :
    		createStudent(session);
    		break;
    	case 2 :
    		getStudentbyId(session);
    		break;
    	case 3 :
    		deleteStudentById(session);
    		break;
    	case 4 :
    		updateStudentById(session);
    		break;
    	default: System.out.println("Invalid Option");
    	}
    	sc.close();
    	}
	
	
	private static void createStudent(Session session) {
		session.beginTransaction();
		Integer id =(Integer)session.save(getStudent());
		System.out.println("Employee is created  with Id::"+id);
		session.getTransaction().commit();
	}
	
	private static Student getStudent(){
		Student student= new Student();
		student.setName("sahith");
		student.setEmail("sahith@gmail.com");
		student.setPassword("Sahith#1234");
		return student;
	}
	
	
	private static void getStudentbyId(Session session) {
		Student student = session.get(Student.class, 1);
		if(student != null){
			System.out.println(student.getEmail());
		}else{
			System.out.println("Employee doesn't exist with provideded Id..");
		}	
	}
	
	private static void updateStudentById(Session session) {
		Student student = session.get(Student.class, 2);
		if(student != null){
			student.setPassword("Sahith1234");
			session.beginTransaction();
			session.update(student);
			session.getTransaction().commit();
			System.out.println("Updated your password !");
		}else{
			System.out.println("Employee doesn't exist with provideded Id..");
		}
	}
	
	private static void deleteStudentById(Session session) {
		Student student = session.get(Student.class, 1);
		if(student != null){
			session.beginTransaction();
			session.delete(student);
			session.getTransaction().commit();
			System.out.println("Deleted Successfully");
		}else{
			System.out.println("Employee doesn't exist with provideded Id..");
		}
	}
	
	}

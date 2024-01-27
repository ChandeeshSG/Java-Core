package edu.kec.sms;

import java.util.Scanner;

import edu.kec.sms.iservice.IStudentService;
import edu.kec.sms.model.Student;
import edu.kec.sms.service.StudentService;

public class Main {

	public static void main(String[] args) {
		IStudentService studentService = new StudentService();
		Student student = readStudent();
		if(studentService.add(student)) {
			System.out.println("Student added....");
		}
		else {
			System.out.println("Failed to add student....");
		}
		Student arrayStudent = studentService.getStudentById(1);
		System.out.println(arrayStudent.getName());
		
	}
	
	public static Student readStudent() {
		 Scanner scanner = new Scanner(System.in);
		 Student student = new Student();
		 System.out.print("Enter Name: ");
		 student.setName(scanner.nextLine());
		 System.out.print("Enter Email: ");
		 student.setEmail(scanner.nextLine());
		 System.out.print("Enter Id: ");
		 student.setId(scanner.nextInt());
		 System.out.print("Enter MObile: ");
		 student.setMobile(scanner.nextLong());
		 scanner.close();
		 return student;
	}
}

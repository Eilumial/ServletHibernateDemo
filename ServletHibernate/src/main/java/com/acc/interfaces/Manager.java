package com.acc.interfaces;

import com.acc.entity.Student;

public interface Manager {
	void connect();
	void addStudent(Student s);
	void fetchByFName(String sfn);
	void fetchByID(String sid);
	void fetchAllStudent();
	void updateEmail(String email, int sid);
	void deleteByID(int sid);
}

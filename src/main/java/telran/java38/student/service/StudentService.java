package telran.java38.student.service;

import java.util.List;

import telran.java38.student.dto.ScoreDto;
import telran.java38.student.dto.StudentBaseDto;
import telran.java38.student.dto.StudentDto;
import telran.java38.student.dto.StudentUpdateDto;

public interface StudentService {
	boolean addStudent(StudentBaseDto studentCreateDto);

	StudentDto findStudentById(Integer id);

	StudentDto removeStudent(Integer id);

	StudentBaseDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

	boolean addScore(Integer id, ScoreDto scoreDto);

	List<StudentDto> findStudentsByGroup(String group);

	List<StudentDto> findStudentsByParameters(String group, String name);
	
	long countStudentsByGroup(String group);
	
	List<StudentDto> findStudentsByExamScore(String exam, int minScore);
}

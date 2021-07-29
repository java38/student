package telran.java38.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java38.student.dto.ScoreDto;
import telran.java38.student.dto.StudentBaseDto;
import telran.java38.student.dto.StudentDto;
import telran.java38.student.dto.StudentUpdateDto;
import telran.java38.student.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/student")
	public boolean addStudent(@RequestBody StudentBaseDto studentCreateDto) {
		return studentService.addStudent(studentCreateDto);
	}

	@GetMapping("/student/{id}")
	public StudentDto findStudent(@PathVariable Integer id) {
		return studentService.findStudentById(id);
	}

	@DeleteMapping("/student/{id}")
	public StudentDto deleteStudent(@PathVariable Integer id) {
		return studentService.removeStudent(id);
	}

	@PutMapping("/student/{id}")
	public StudentBaseDto updateStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
		return studentService.updateStudent(id, studentUpdateDto);
	}

	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
		return studentService.addScore(id, scoreDto);
	}

	@GetMapping("/students/group/{group}")
	public List<StudentDto> findStudentsByGroup(@PathVariable String group) {
		return studentService.findStudentsByGroup(group);
	}

	@GetMapping("/students/group/{group}/name/{name}")
	public List<StudentDto> findStudentsByParams(@PathVariable String group, @PathVariable String name) {
		return studentService.findStudentsByParameters(group, name);
	}
	
	@GetMapping("/count/group/{group}")
	public long numberStudentsByGroup(@PathVariable String group) {
		return studentService.countStudentsByGroup(group);
	}
	
	@GetMapping("/students/exam/{exam}/minScore/{score}")
	public List<StudentDto> findStudentsByExamScore(@PathVariable String exam, @PathVariable Integer score) {
		return studentService.findStudentsByExamScore(exam, score);
	}

}

package telran.java38.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.student.dao.StudentMongoRepository;
import telran.java38.student.dto.ScoreDto;
import telran.java38.student.dto.StudentBaseDto;
import telran.java38.student.dto.StudentDto;
import telran.java38.student.dto.StudentUpdateDto;
import telran.java38.student.dto.exceptions.StudentNotFoundException;
import telran.java38.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentMongoRepository studentRepository;

	@Override
	public boolean addStudent(StudentBaseDto studentCreateDto) {
		boolean res = studentRepository.existsById(studentCreateDto.getId());
		if (res) {
			return false;
		}
		Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(),
				studentCreateDto.getPassword(), studentCreateDto.getGroup());
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudentById(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		return convertToStudentDto(student);
	}

	@Override
	public StudentDto removeStudent(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		studentRepository.delete(student);
		return convertToStudentDto(student);
	}
	
	private StudentDto convertToStudentDto(Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.name(student.getName())
				.scores(student.getScores())
				.group(student.getGroup())
				.build();
	}

	@Override
	public StudentBaseDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		if (studentUpdateDto.getName() != null) {
			student.setName(studentUpdateDto.getName());
		}
		if (studentUpdateDto.getPassword() != null) {
			student.setPassword(studentUpdateDto.getPassword());
		}
		studentRepository.save(student);
		return convertToStudentBaseDto(student);
	}

	private StudentBaseDto convertToStudentBaseDto(Student student) {
		return StudentBaseDto.builder()
				.id(student.getId())
				.name(student.getName())
				.password(student.getPassword())
				.build();
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return res;
	}

	@Override
	public List<StudentDto> findStudentsByGroup(String group) {
		return studentRepository.findByGroup(group)
				.map(s -> convertToStudentDto(s))
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> findStudentsByParameters(String group, String name) {
		return studentRepository.findByGroupAndName(group, name)
				.map(s -> convertToStudentDto(s))
				.collect(Collectors.toList());
	}

	@Override
	public long countStudentsByGroup(String group) {
		return studentRepository.countByGroup(group);
	}

	@Override
	public List<StudentDto> findStudentsByExamScore(String exam, int minScore) {
		return studentRepository.findByExamMinScore(exam, minScore)
				.map(s -> convertToStudentDto(s))
				.collect(Collectors.toList());
	}

}

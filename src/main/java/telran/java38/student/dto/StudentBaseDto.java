package telran.java38.student.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentBaseDto {
	Integer id;
	String name;
	String password;
	String group;
}

package telran.java38.student.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6694353753271641330L;
	
	public StudentNotFoundException() {
	}
	
	public StudentNotFoundException(int id) {
		super("student id: " + id + ", not found");
	}

}

package telran.java38.student.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3873062824642965797L;

}

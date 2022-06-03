package ma.cam.auth.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ma.cam.auth.exception.base.enums.ExceptionEnum;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BusinessException {

	private static final long serialVersionUID = -6958128182491079251L;

	public UnauthorizedException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum);
	}

	public UnauthorizedException(ExceptionEnum exceptionEnum, Object... args) {
		super(exceptionEnum, args);
	}
}
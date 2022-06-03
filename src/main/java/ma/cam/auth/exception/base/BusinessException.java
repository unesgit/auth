package ma.cam.auth.exception.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.cam.auth.exception.base.enums.ExceptionEnum;

/**
 * @author 10061004
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BusinessException extends Exception {

	private final String code;
	private final String message;

	private static final long serialVersionUID = 1L;

	public BusinessException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BusinessException(ExceptionEnum exceptionEnum) {
		super();
		this.code = exceptionEnum.getCode();
		this.message = exceptionEnum.getLabel();
	}

	public BusinessException(ExceptionEnum exceptionEnum, Object... args) {
		super();
		this.code = exceptionEnum.getCode();
		this.message = String.format(exceptionEnum.getLabel(), args);
	}
}

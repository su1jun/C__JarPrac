package cloneproject.Instagram.domain.member.exception;

import cloneproject.Instagram.global.error.ErrorCode;
import cloneproject.Instagram.global.error.exception.BusinessException;

public class JwtExpiredException extends BusinessException {
	public JwtExpiredException() {
		super(ErrorCode.JWT_EXPIRED);
	}

}

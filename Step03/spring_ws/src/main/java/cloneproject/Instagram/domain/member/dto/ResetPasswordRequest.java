package cloneproject.Instagram.domain.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResetPasswordRequest {

	@ApiModelProperty(value = "유저네임", example = "dlwlrma", required = true)
	@NotBlank(message = "username을 입력해주세요")
	@Length(min = 4, max = 12, message = "사용자 이름은 4문자 이상 12문자 이하여야 합니다")
	@Pattern(regexp = "^[0-9a-zA-Z]+$", message = "username엔 대소문자, 숫자만 사용할 수 있습니다.")
	private String username;

	@ApiModelProperty(value = "인증코드", example = "ABC123AAAAAAAA1231313123..", required = true)
	@NotBlank(message = "인증코드를 입력해주세요")
	@Length(max = 30, min = 30, message = "인증코드는 30자리 입니다.")
	private String code;

	@ApiModelProperty(value = "새비밀번호", example = "a12341234", required = true)
	@NotBlank(message = "새비밀번호를 입력해주세요")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "비밀번호는 8자 이상, 최소 하나의 문자와 숫자가 필요합니다")
	@Length(max = 20, message = "비밀번호는 20문자 이하여야 합니다")
	private String newPassword;

}

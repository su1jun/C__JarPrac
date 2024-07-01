package cloneproject.Instagram.domain.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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
public class EditProfileRequest {

	@ApiModelProperty(value = "유저네임", example = "dlwlrma", required = true)
	@NotBlank(message = "username을 입력해주세요")
	@Length(min = 4, max = 12, message = "ID는 4문자 이상 12문자 이하여야 합니다")
	private String memberUsername;

	@ApiModelProperty(value = "이름", example = "이지금", required = true)
	@NotBlank(message = "이름을 입력해주세요")
	@Length(min = 2, max = 12, message = "이름은 2문자 이상 12문자 이하여야 합니다")
	private String memberName;

	@ApiModelProperty(value = "웹사이트", example = "http://localhost:8080", required = false)
	@URL(message = "URL 형식이 맞지 않습니다")
	private String memberWebsite;

	@ApiModelProperty(value = "소개", example = "안녕하세요", required = false)
	private String memberIntroduce;

	@ApiModelProperty(value = "이메일", example = "aaa@gmail.com", required = true)
	@NotBlank(message = "이메일은 필수입니다")
	@Email(message = "이메일의 형식이 맞지 않습니다")
	private String memberEmail;

	@ApiModelProperty(value = "전화번호", example = "010-0000-0000", required = false)
	@Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식이 맞지 않습니다")
	private String memberPhone;

	@ApiModelProperty(value = "성별", example = "FEMALE", required = true)
	@Pattern(regexp = "^MALE|FEMALE|PRIVATE$", message = "올바르지 않는 성별입니다")
	@NotBlank(message = "성별을 입력해주세요")
	private String memberGender;

}

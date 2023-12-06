package com.btone.project.api.application.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.application.auth.domain.condition.UserSearchCondition;
import com.btone.project.api.application.auth.domain.model.User;
import com.btone.project.api.application.auth.domain.repository.UserRepository;
import com.btone.project.api.application.auth.domain.repository.UserSearchRepository;
import com.btone.project.api.application.auth.dto.request.UserRequestDTO;
import com.btone.project.api.application.auth.dto.response.UserResponseDTO;
import com.btone.project.api.application.auth.enums.UserMethods;
import com.btone.project.api.application.common.domain.condition.CodeSearchCondition;
import com.btone.project.api.application.common.domain.repository.CodeSearchRepository;
import com.btone.project.api.application.common.dto.response.CodeResponseDTO;
import com.btone.project.api.application.log.domain.model.UserLog;
import com.btone.project.api.application.log.domain.repository.UserLogRepository;
import com.btone.project.api.common.domain.model.ResponseMessage;
import com.btone.project.api.common.domain.specification.CommonSpecification;
import com.btone.project.api.common.enums.CommonMethods;
import com.btone.project.api.common.util.CommonUtils;

import lombok.RequiredArgsConstructor;

/**
* @packageName   : com.btone.project.api.auth.service
* @fileName      : AuthService.java
* @author        : 오수병
* @date          : 2023.11.10
* @description   : 인증관리 서비스
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.10        오수병                최초 생성
*/
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final UserSearchRepository userSearchrepository;
	private final UserLogRepository userLogRepository;
	private final CodeSearchRepository codeSearchRepository;
	private final MessageSourceAccessor messageSource;

	/**
	* @methodName  : methods
	* @author      : 오수병
	* @date        : 2023.11.10
	* @description : 회원관리
	* @param method
	* @param input
	* @return
	*/
	public ResponseMessage methods(String method, UserRequestDTO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(UserMethods.CHECKID.getKey().equals(method) || UserMethods.SIGNUP.getKey().equals(method)) {
			return signup(method, input, searchKeys);
		}else if(UserMethods.EDIT.getKey().equals(method) || UserMethods.CANCEL.getKey().equals(method) || UserMethods.RESETPASSWORD.getKey().equals(method)) {
			return edit(method, input, searchKeys);
		}else if(CommonMethods.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"), null);
	}

	/**
	* @methodName  : signup
	* @author      : 오수병
	* @date        : 2023.11.10
	* @description : 회원가입, 아이디중복체크
	* @param method
	* @param input
	* @param searchKeys
	* @return
	*/
	@Transactional
	public ResponseMessage signup(String method, UserRequestDTO input, Map<String, Object> searchKeys) {
		String message = messageSource.getMessage("user.signup.success");
		searchKeys.put("userId", input.getUserId());
		List<UserLog> list = userLogRepository.findAll(CommonSpecification.searchCondition(searchKeys));

		if(list.size() > 0) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.checkid.duplicated"), null);
		}

		CodeResponseDTO code = codeSearchRepository.search(CodeSearchCondition.build("ROLE_CD", "", "", "", "", ""));

		if(code == null) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.rolecd.notexists"), null);
		}

		if(UserMethods.SIGNUP.getKey().equals(method)) {
			User user = User.builder()
					.userId(input.getUserId())
					.pwd(input.getPwd())
					.actvNm(input.getActvNm())
					.roleCd(code.getCd())
					.build();

			repository.save(user);

			UserLog userLog = UserLog.builder()
					.userSn(user.getUserSn())
					.userId(user.getUserId())
					.pwd(user.getPwd())
					.actvNm(user.getActvNm())
					.roleCd(code.getCd())
					.build();

			userLogRepository.save(userLog);
		}else {
			message = messageSource.getMessage("user.checkid.success");
		}

		return ResponseMessage.ok(null, message, null);
	}

	/**
	* @methodName  : edit
	* @author      : 오수병
	* @date        : 2023.11.10
	* @description : 회원정보수정/회원탈퇴
	* @param method
	* @param input
	* @param searchKeys
	* @return
	*/
	@Transactional
	public ResponseMessage edit(String method, UserRequestDTO input, Map<String, Object> searchKeys) {
		String message = "";
		searchKeys.put("delYn", "N");
		searchKeys.put("userSn", input.getUserSn());
		Optional<User> optionalUser = repository.findOne(CommonSpecification.searchCondition(searchKeys));

		if(optionalUser.isEmpty()) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.notexists"), null);
		}

		User user = optionalUser.get();

		if(UserMethods.EDIT.getKey().equals(method)) {
			user.setActvNm(input.getActvNm());
			user.setPwd(input.getPwd());
			message = messageSource.getMessage("user.edit.success");
		}else if(UserMethods.CANCEL.getKey().equals(method)){
			user.setDelYn("Y");
			message = messageSource.getMessage("user.cancel.success");
		}else if(UserMethods.RESETPASSWORD.getKey().equals(method)) {
			String tmpPwd = CommonUtils.getRamdomPassword(20);
			user.setRsPwdYn("Y");
			user.setTmpPwd(tmpPwd);
			user.setPwd(tmpPwd);
			message = messageSource.getMessage("user.reset-password.success");
		}

		return ResponseMessage.ok(user, message, null);
	}

	/**
	* @methodName  : search
	* @author      : 오수병
	* @date        : 2023.11.10
	* @description : 회원목록조회
	* @param input
	* @param searchKeys
	* @return
	*/
	@Transactional
	public ResponseMessage search(UserRequestDTO input, Map<String, Object> searchKeys) {
		List<UserResponseDTO> list = userSearchrepository.search(UserSearchCondition.build(input.getActvNm(), input.getRoleCd(), input.getRoleNm()));

		if(list.size() == 0) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.notexists"), null);
		}

		return ResponseMessage.ok(list, messageSource.getMessage("user.search.success"), null);
	}
}

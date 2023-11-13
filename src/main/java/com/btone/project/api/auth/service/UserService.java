package com.btone.project.api.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.auth.entity.Role;
import com.btone.project.api.auth.entity.User;
import com.btone.project.api.auth.enums.Method;
import com.btone.project.api.auth.repository.UserRepository;
import com.btone.project.api.auth.vo.UserVO;
import com.btone.project.api.common.model.ResponseMessage;
import com.btone.project.api.common.specification.CommonSpecification;
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
@Transactional
public class UserService {

	private final UserRepository repository;
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
	public ResponseMessage methods(String method, UserVO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(Method.CHECKID.getKey().equals(method) || Method.SIGNUP.getKey().equals(method)) {
			return signup(method, input, searchKeys);
		}else if(Method.EDIT.getKey().equals(method) || Method.CANCEL.getKey().equals(method) || Method.RESETPASSWORD.getKey().equals(method)) {
			return edit(method, input, searchKeys);
		}else if(Method.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"));
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
	public ResponseMessage signup(String method, UserVO input, Map<String, Object> searchKeys) {
		String message = messageSource.getMessage("user.signup.success");
		try {
			searchKeys.put("userId", input.getUserId());
			List<User> list = repository.findAll(CommonSpecification.searchCondition(searchKeys));

			if(list.size() > 0) {
				if("Y".equals(list.get(0).getDelYn())) {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.checkid.canceled"));
				}else {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.checkid.duplicated"));
				}
			}

			if(Method.SIGNUP.getKey().equals(method)) {
				Role role = Role.builder()
						.roleCd(input.getRoleCd())
						.build();

				User user = User.builder()
						.userId(input.getUserId())
						.pwd(input.getPwd())
						.actvNm(input.getActvNm())
						.role(role)
						.build();

				repository.save(user);
			}else {
				message = messageSource.getMessage("user.checkid.success");
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, message);
	}

	/**
	* @methodName  : edit
	* @author      : 오수병
	* @date        : 2023.11.10
	* @description : 회원정보수정, 회원탈퇴
	* @param method
	* @param input
	* @param searchKeys
	* @return
	*/
	public ResponseMessage edit(String method, UserVO input, Map<String, Object> searchKeys) {
		String message = messageSource.getMessage("user.edit.success");
		User user = null;
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("userSn", input.getUserSn());
			Optional<User> optionalUser = repository.findOne(CommonSpecification.searchCondition(searchKeys));

			if(optionalUser.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.notexists"));
			}

			user = optionalUser.get();

			if(Method.EDIT.getKey().equals(method)) {
				user.setActvNm(input.getActvNm());
				user.setPwd(input.getPwd());
			}else if(Method.CANCEL.getKey().equals(method)){
				user.setDelYn("Y");
				message = messageSource.getMessage("user.cancel.success");
			}else if(Method.RESETPASSWORD.getKey().equals(method)) {
				String tmpPwd = CommonUtils.getRamdomPassword(20);
				user.setRsPwdYn("Y");
				user.setTmpPwd(tmpPwd);
				user.setPwd(tmpPwd);
				message = messageSource.getMessage("user.reset-password.success");
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(user, message);
	}

	/**
	* @methodName  : search
	* @author      : 오수병
	* @date        : 2023.11.10
	* @description : 회원정보조회
	* @param input
	* @param searchKeys
	* @return
	*/
	public ResponseMessage search(UserVO input, Map<String, Object> searchKeys) {
		List<User> list = new ArrayList<>();
		try {
			list = repository.findAll();

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("user.notexists"));
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(list, messageSource.getMessage("user.search.success"));
	}
}

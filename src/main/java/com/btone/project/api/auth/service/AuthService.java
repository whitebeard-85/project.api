package com.btone.project.api.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.auth.entity.Role;
import com.btone.project.api.auth.entity.User;
import com.btone.project.api.auth.repository.UserRepository;
import com.btone.project.api.auth.specification.UserSpecification;
import com.btone.project.api.auth.vo.AuthVO;
import com.btone.project.api.common.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
@EnableJpaAuditing
public class AuthService {

	private final UserRepository userRepository;
	private final MessageSourceAccessor messageSource;

	public ResponseMessage account(String method, AuthVO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if("checkId".equals(method)) {
			return checkId(input, searchKeys);
		}else if("signup".equals(method)) {
			return signup(input, searchKeys);
		}else if("edit".equals(method)) {
			return edit(input, searchKeys);
		}else if("cancel".equals(method)) {
			return cancel(input, searchKeys);
		}else if("lookup".equals(method)) {
			return lookup(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, "유효하지 않은 path 입니다.");
	}

	public ResponseMessage checkId(AuthVO input, Map<String, Object> searchKeys) {
		try {
			searchKeys.put("userId", input.getUserId());
			List<User> list = userRepository.findAll(UserSpecification.searchUser(searchKeys));

			if(list.size() > 0) {
				if("Y".equals(list.get(0).getDelYn())) {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.canceled"));
				}else {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.duplicated"));
				}
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.checkid.success"));
	}

	public ResponseMessage signup(AuthVO input, Map<String, Object> searchKeys) {
		try {
			searchKeys.put("userId", input.getUserId());
			List<User> list = userRepository.findAll(UserSpecification.searchUser(searchKeys));

			if(list.size() > 0) {
				if("Y".equals(list.get(0).getDelYn())) {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.canceled"));
				}else {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.duplicated"));
				}
			}

			User user = User.builder()
					.userId(input.getUserId())
					.pwd(input.getPwd())
					.actvNm(input.getActvNm())
					.role(Role.USER)
					.build();

			userRepository.save(user);
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.signup.success"));
	}

	public ResponseMessage edit(AuthVO input, Map<String, Object> searchKeys) {
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("userSn", input.getUserSn());
			Optional<User> optionalUser = userRepository.findOne(UserSpecification.searchUser(searchKeys));

			if(optionalUser.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.notexists"));
			}

			User user = optionalUser.get();
			user.setActvNm(input.getActvNm());
			user.setPwd(input.getPwd());
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.edit.success"));
	}

	public ResponseMessage cancel(AuthVO input, Map<String, Object> searchKeys) {
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("userSn", input.getUserSn());
			Optional<User> optionalUser = userRepository.findOne(UserSpecification.searchUser(searchKeys));

			if(optionalUser.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.notexists"));
			}

			User user = optionalUser.get();
			user.setDelYn("Y");
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.cancel.success"));
	}

	public ResponseMessage lookup(AuthVO input, Map<String, Object> searchKeys) {
		List<User> list = new ArrayList<>();
		try {
			list = userRepository.findAll();

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.notexists"));
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(list, messageSource.getMessage("account.lookup.success"));
	}
}

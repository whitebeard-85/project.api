package com.btone.project.api.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.btone.project.api.auth.mapper.AuthMapper;
import com.btone.project.api.auth.vo.AuthVO;
import com.btone.project.api.common.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthMapper mapper;
	private final MessageSourceAccessor messageSource;
	private final PasswordEncoder passwordEncoder;

	public ResponseMessage account(String method, AuthVO input) {
		if("checkId".equals(method)) {
			return checkId(input);
		}else if("signup".equals(method)) {
			return signup(input);
		}else if("edit".equals(method)) {
			return edit(input);
		}else if("cancel".equals(method)) {
			return cancel(input);
		}else if("lookup".equals(method)) {
			return lookup(input);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, "유효하지 않은 path 입니다.");
	}

	public ResponseMessage checkId(AuthVO input) {
		try {
			List<AuthVO> list = mapper.lookup(input);

			if(list.size() > 0) {
				if("Y".equals(list.get(0).getDelYn())) {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.canceled"));
				}else {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.duplicated"));
				}
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", e.getMessage()));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.checkid.success"));
	}

	public ResponseMessage signup(AuthVO input) {
		try {
			List<AuthVO> list = mapper.lookup(input);

			if(list.size() > 0) {
				if("Y".equals(list.get(0).getDelYn())) {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.canceled"));
				}else {
					return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.checkid.duplicated"));
				}
			}

			input.setPwd(passwordEncoder.encode(input.getPwd()));
			mapper.signup(input);
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", e.getMessage()));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.signup.success"));
	}

	public ResponseMessage edit(AuthVO input) {
		try {
			List<AuthVO> list = mapper.lookup(input);

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.notexists"));
			}

			input.setPwd(passwordEncoder.encode(input.getPwd()));
			mapper.edit(input);
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", e.getMessage()));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.edit.success"));
	}

	public ResponseMessage cancel(AuthVO input) {
		try {
			List<AuthVO> list = mapper.lookup(input);

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.notexists"));
			}

			mapper.cancel(input);
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", e.getMessage()));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("account.cancel.success"));
	}

	public ResponseMessage lookup(AuthVO input) {
		List<AuthVO> list = new ArrayList<>();
		try {
			list = mapper.lookup(input);

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("account.notexists"));
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", e.getMessage()));
		}

		return ResponseMessage.ok(list, messageSource.getMessage("account.lookup.success"));
	}
}

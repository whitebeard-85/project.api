package com.btone.project.api.application.auth.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btone.project.api.application.auth.dto.request.UserRequestDTO;
import com.btone.project.api.application.auth.service.UserService;
import com.btone.project.api.common.domain.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

/**
* @packageName   : com.btone.project.api.auth.controller
* @fileName      : AuthController.java
* @author        : 오수병
* @date          : 2023.11.10
* @description   : 인증관리 컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.10        오수병                최초 생성
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/user")
public class UserController {

	private final UserService service;
	private final MessageSourceAccessor messageSource;

	/**
	* @methodName  : methods
	* @author      : 오수병
	* @date        : 2023.11.14
	* @description : 회원관리
	* @param method
	* @param input
	* @return
	*/
	@PostMapping("/{method}")
	public ResponseEntity<?> methods(@PathVariable String method, @RequestBody UserRequestDTO input) {
		ResponseMessage response = null;

		try {
			response = service.methods(method, input);
		} catch (Exception e) {
			response = ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error"), e.getMessage());
		}

		return ResponseEntity.ok(response);
	}
}
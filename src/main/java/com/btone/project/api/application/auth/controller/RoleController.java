package com.btone.project.api.application.auth.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btone.project.api.application.auth.dto.request.RoleRequestDTO;
import com.btone.project.api.application.auth.service.RoleService;
import com.btone.project.api.common.domain.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

/**
* @packageName   : com.btone.project.api.auth.controller
* @fileName      : RoleController.java
* @author        : 오수병
* @date          : 2023.11.13
* @description   : 권한관리 컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.13        오수병                최초 생성
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/role")
public class RoleController {

	private final RoleService service;
	private final MessageSourceAccessor messageSource;

	/**
	* @methodName  : methods
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description : 권한관리
	* @param method
	* @param input
	* @return
	*/
	@PostMapping("/{method}")
	public ResponseEntity<?> methods(@PathVariable String method, @RequestBody RoleRequestDTO input) {
		ResponseMessage response = null;

		try {
			response = service.methods(method, input);
		} catch (Exception e) {
			response = ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error"), e.getMessage());
		}

		return ResponseEntity.ok(response);
	}
}
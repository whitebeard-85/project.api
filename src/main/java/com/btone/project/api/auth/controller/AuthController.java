package com.btone.project.api.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btone.project.api.auth.service.AuthService;
import com.btone.project.api.auth.vo.AuthVO;
import com.btone.project.api.common.model.ResponseMessage;

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
@RequestMapping("/auth")
public class AuthController {

	private final AuthService service;

	/**
	* @methodName  : account
	* @author      : 오수병
	* @date        : 2023.11.10
	* @param method
	* @param input
	* @return
	*/
	@PostMapping("/account/{method}")
	public ResponseEntity<?> account(@PathVariable String method, @RequestBody AuthVO input) {
		ResponseMessage response = service.account(method, input);
		return ResponseEntity.ok(response);
	}
}

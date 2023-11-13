package com.btone.project.api.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btone.project.api.auth.service.RoleService;
import com.btone.project.api.auth.vo.RoleVO;
import com.btone.project.api.common.model.ResponseMessage;

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

	/**
	* @methodName  : methods
	* @author      : 오수병
	* @date        : 2023.11.13
	* @description :
	* @param method
	* @param input
	* @return
	*/
	@PostMapping("/{method}")
	public ResponseEntity<?> methods(@PathVariable String method, @RequestBody RoleVO input) {
		ResponseMessage response = service.methods(method, input);
		return ResponseEntity.ok(response);
	}
}

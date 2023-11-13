package com.btone.project.api.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btone.project.api.auth.service.RoleService;
import com.btone.project.api.auth.vo.AuthVO;
import com.btone.project.api.common.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/role")
public class RoleController {

	private final RoleService service;

	@PostMapping("/{method}")
	public ResponseEntity<?> methods(@PathVariable String method, @RequestBody AuthVO input) {
		ResponseMessage response = service.methods(method, input);
		return ResponseEntity.ok(response);
	}
}

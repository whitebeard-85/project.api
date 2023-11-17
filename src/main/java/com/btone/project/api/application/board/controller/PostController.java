package com.btone.project.api.application.board.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btone.project.api.application.board.dto.request.PostRequestDTO;
import com.btone.project.api.application.board.service.PostService;
import com.btone.project.api.common.domain.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/post")
public class PostController {

	private final PostService service;
	private final MessageSourceAccessor messageSource;

	@PostMapping("/{method}")
	public ResponseEntity<?> methods(@PathVariable String method, @RequestBody PostRequestDTO input) {
		ResponseMessage response = null;

		try {
			response = service.methods(method, input);
		} catch (Exception e) {
			response = ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error"), e.getMessage());
		}

		return ResponseEntity.ok(response);
	}
}
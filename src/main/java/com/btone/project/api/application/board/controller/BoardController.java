package com.btone.project.api.application.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btone.project.api.application.board.service.BoardService;
import com.btone.project.api.application.board.vo.BoardVO;
import com.btone.project.api.common.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

	private final BoardService service;

	@PostMapping("/{method}")
	public ResponseEntity<?> methods(@PathVariable String method, @RequestBody BoardVO input) {
		ResponseMessage response = service.methods(method, input);
		return ResponseEntity.ok(response);
	}
}

package com.btone.project.api.application.board.controller;

import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.btone.project.api.application.board.dto.request.PostRequestDTO;
import com.btone.project.api.application.board.service.PostService;
import com.btone.project.api.application.common.dto.request.FileRequestDTO;
import com.btone.project.api.application.common.service.FileService;
import com.btone.project.api.common.domain.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/post")
public class PostController {

	private final PostService service;
	private final FileService fileService;
	private final MessageSourceAccessor messageSource;

	@PostMapping("/{method}")
	public ResponseEntity<?> methods(@PathVariable String method
									, @RequestParam("boardSn") Integer boardSn
									, @RequestParam("title") String title
									, @RequestParam("contents") String contents
									, @RequestParam("writer") String writer
									, @RequestParam("fileList") List<MultipartFile> fileList) {
		ResponseMessage response = null;

		try {
			List<FileRequestDTO> uploadedFileList = fileService.uploadFileList(fileList);

			PostRequestDTO input = new PostRequestDTO();
			input.setBoardSn(boardSn);
			input.setTitle(title);
			input.setContents(contents);
			input.setWriter(writer);
			input.setFileList(uploadedFileList);

			response = service.methods(method, input);
		} catch (Exception e) {
			response = ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error"), e.getMessage());
		}

		return ResponseEntity.ok(response);
	}
}
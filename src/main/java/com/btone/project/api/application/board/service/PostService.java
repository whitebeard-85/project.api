package com.btone.project.api.application.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.application.board.domain.condition.PostSearchCondition;
import com.btone.project.api.application.board.domain.model.Board;
import com.btone.project.api.application.board.domain.model.Post;
import com.btone.project.api.application.board.domain.repository.BoardRepository;
import com.btone.project.api.application.board.domain.repository.PostRepository;
import com.btone.project.api.application.board.domain.repository.PostSearchRepository;
import com.btone.project.api.application.board.dto.request.PostRequestDTO;
import com.btone.project.api.application.board.dto.response.PostResponseDTO;
import com.btone.project.api.application.common.domain.model.File;
import com.btone.project.api.application.common.domain.repository.FileRepository;
import com.btone.project.api.application.common.dto.request.FileRequestDTO;
import com.btone.project.api.common.domain.model.ResponseMessage;
import com.btone.project.api.common.domain.specification.CommonSpecification;
import com.btone.project.api.common.enums.CommonMethods;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository repository;
	private final PostSearchRepository postSearchRepository;
	private final BoardRepository boardRepository;
	private final FileRepository fileRepository;
	private final MessageSourceAccessor messageSource;

	public ResponseMessage methods(String method, PostRequestDTO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(CommonMethods.CREATE.getKey().equals(method)) {
			return create(input, searchKeys);
		} else if(CommonMethods.UPDATE.getKey().equals(method) || CommonMethods.DELETE.getKey().equals(method)) {
			return update(method, input, searchKeys);
		} else if(CommonMethods.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"), null);
	}

	@Transactional
	public ResponseMessage create(PostRequestDTO input, Map<String, Object> searchKeys) {
		searchKeys.put("delYn", "N");
		searchKeys.put("boardSn", input.getBoardSn());
		Optional<Board> optionalBoard = boardRepository.findOne(CommonSpecification.searchCondition(searchKeys));

		if(optionalBoard.isEmpty()) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("post.board.notexists"), null);
		}

		Post post = Post.builder()
				.boardSn(input.getBoardSn())
				.title(input.getTitle())
				.contents(input.getContents())
				.writer(input.getWriter())
				.build();

		repository.save(post);

		if(input.getFileList() != null && input.getFileList().size() > 0) {
			List<FileRequestDTO> fileRequestDTOList = input.getFileList();

			for(FileRequestDTO fileRequestDTO : fileRequestDTOList) {
				File file = File.builder()
						.parentId("post")
						.parentSn(post.getPostSn())
						.uploadPath(fileRequestDTO.getUploadPath())
						.originalFileName(fileRequestDTO.getOriginalFileName())
						.uploadFileName(fileRequestDTO.getUploadFileName())
						.fileSize(fileRequestDTO.getFileSize())
						.fileExt(fileRequestDTO.getFileExt())
						.build();

				fileRepository.save(file);
			}
		}

		return ResponseMessage.ok(null, messageSource.getMessage("post.create.success"), null);
	}

	@Transactional
	public ResponseMessage update(String method, PostRequestDTO input, Map<String, Object> searchKeys) {
		String message = "";
		searchKeys.put("delYn", "N");
		searchKeys.put("postSn", input.getPostSn());
		Optional<Post> optionalRole = repository.findOne(CommonSpecification.searchCondition(searchKeys));

		if(optionalRole.isEmpty()) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("post.notexists"), null);
		}

		Post post = optionalRole.get();

		if(CommonMethods.UPDATE.getKey().equals(method)) {
			post.setTitle(input.getTitle());
			post.setContents(input.getContents());
			message = messageSource.getMessage("post.update.success");
		}else {
			post.setDelYn("Y");
			message = messageSource.getMessage("post.delete.success");
		}

		return ResponseMessage.ok(null, message, null);
	}

	@Transactional
	public ResponseMessage search(PostRequestDTO input, Map<String, Object> searchKeys) {
		List<PostResponseDTO> list = postSearchRepository.search(PostSearchCondition.build(input.getBoardSn(), input.getPostSn(), input.getTitle(), input.getContents(), input.getWriter()));

		if(list.size() == 0) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("post.notexists"), null);
		}

		return ResponseMessage.ok(list, messageSource.getMessage("post.search.success"), null);
	}
}

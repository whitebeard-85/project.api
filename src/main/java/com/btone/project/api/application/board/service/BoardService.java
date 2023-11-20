package com.btone.project.api.application.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.application.board.domain.model.Board;
import com.btone.project.api.application.board.domain.repository.BoardRepository;
import com.btone.project.api.application.board.dto.request.BoardRequestDTO;
import com.btone.project.api.common.domain.model.ResponseMessage;
import com.btone.project.api.common.domain.specification.CommonSpecification;
import com.btone.project.api.common.enums.CommonMethods;

import lombok.RequiredArgsConstructor;

/**
* @packageName   : com.btone.project.api.application.board.service
* @fileName      : BoardService.java
* @author        : 오수병
* @date          : 2023.11.15
* @description   : 게시판관리 서비스
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.15        오수병                최초 생성
*/
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

	private final BoardRepository repository;
	private final MessageSourceAccessor messageSource;

	/**
	* @methodName  : methods
	* @author      : 오수병
	* @date        : 2023.11.15
	* @description : 게시판관리
	* @param method
	* @param input
	* @return
	*/
	public ResponseMessage methods(String method, BoardRequestDTO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(CommonMethods.CREATE.getKey().equals(method)) {
			return create(input);
		} else if(CommonMethods.UPDATE.getKey().equals(method) || CommonMethods.DELETE.getKey().equals(method)) {
			return update(method, input, searchKeys);
		} else if(CommonMethods.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"), null);
	}

	/**
	* @methodName  : create
	* @author      : 오수병
	* @date        : 2023.11.15
	* @description : 게시판등록
	* @param input
	* @return
	*/
	public ResponseMessage create(BoardRequestDTO input) {
		Board board = Board.builder()
				.boardTypeCd(input.getBoardTypeCd())
				.boardNm(input.getBoardNm())
				.boardDesc(input.getBoardDesc())
				.build();

		repository.save(board);

		return ResponseMessage.ok(null, messageSource.getMessage("board.create.success"), null);
	}

	/**
	* @methodName  : ud
	* @author      : 오수병
	* @date        : 2023.11.15
	* @description : 게시판 수정/삭제
	* @param method
	* @param input
	* @param searchKeys
	* @return
	*/
	public ResponseMessage update(String method, BoardRequestDTO input, Map<String, Object> searchKeys) {
		String message = "";
		searchKeys.put("delYn", "N");
		searchKeys.put("boardSn", input.getBoardSn());
		Optional<Board> optionalRole = repository.findOne(CommonSpecification.searchCondition(searchKeys));

		if(optionalRole.isEmpty()) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"), null);
		}

		Board board = optionalRole.get();

		if(CommonMethods.UPDATE.getKey().equals(method)) {
			board.setBoardNm(input.getBoardNm());
			board.setBoardDesc(input.getBoardDesc());
			message = messageSource.getMessage("board.update.success");
		}else {
			board.setDelYn("Y");
			message = messageSource.getMessage("board.delete.success");
		}

		return ResponseMessage.ok(null, message, null);
	}

	/**
	* @methodName  : search
	* @author      : 오수병
	* @date        : 2023.11.15
	* @description : 게시판목록 조회
	* @param input
	* @param searchKeys
	* @return
	*/
	public ResponseMessage search(BoardRequestDTO input, Map<String, Object> searchKeys) {
		List<Board> list = repository.findAll();

		if(list.size() == 0) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"), null);
		}

		return ResponseMessage.ok(list, messageSource.getMessage("board.search.success"), null);
	}
}

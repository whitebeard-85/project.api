package com.btone.project.api.application.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btone.project.api.application.board.entity.Board;
import com.btone.project.api.application.board.enums.BoardType;
import com.btone.project.api.application.board.repository.BoardRepository;
import com.btone.project.api.application.board.vo.BoardVO;
import com.btone.project.api.common.enums.CommonMethods;
import com.btone.project.api.common.model.ResponseMessage;
import com.btone.project.api.common.specification.CommonSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

	private final BoardRepository repository;
	private final MessageSourceAccessor messageSource;

	public ResponseMessage methods(String method, BoardVO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(CommonMethods.CREATE.getKey().equals(method)) {
			return create(input);
		} else if(CommonMethods.UPDATE.getKey().equals(method) || CommonMethods.DELETE.getKey().equals(method)) {
			return ud(method, input, searchKeys);
		} else if(CommonMethods.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"));
	}

	public ResponseMessage create(BoardVO input) {
		try {
			Board manage = Board.builder()
					.boardType(BoardType.find(input.getBoardType()))
					.boardNm(input.getBoardNm())
					.boardDesc(input.getBoardDesc())
					.build();

			repository.save(manage);
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("board.create.success"));
	}

	public ResponseMessage ud(String method, BoardVO input, Map<String, Object> searchKeys) {
		String message = messageSource.getMessage("board.update.success");
		Board board = null;
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("boardSn", input.getBoardSn());
			Optional<Board> optionalRole = repository.findOne(CommonSpecification.searchCondition(searchKeys));

			if(optionalRole.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"));
			}

			board = optionalRole.get();

			if(CommonMethods.UPDATE.getKey().equals(method)) {
				board.setBoardNm(input.getBoardNm());
				board.setBoardDesc(input.getBoardDesc());
			}else {
				board.setDelYn("Y");
				message = messageSource.getMessage("board.delete.success");
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, message);
	}

	public ResponseMessage search(BoardVO input, Map<String, Object> searchKeys) {
		List<Board> list = new ArrayList<>();
		try {
			list = repository.findAll();

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"));
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(list, messageSource.getMessage("board.search.success"));
	}
}
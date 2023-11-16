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

import com.btone.project.api.application.board.domain.condition.PostSearchCondition;
import com.btone.project.api.application.board.domain.repository.PostRepository;
import com.btone.project.api.application.board.domain.repository.PostSearchAndFilterRepository;
import com.btone.project.api.application.board.entity.Board;
import com.btone.project.api.application.board.entity.Post;
import com.btone.project.api.application.board.enums.BoardType;
import com.btone.project.api.application.board.vo.PostVO;
import com.btone.project.api.common.enums.CommonMethods;
import com.btone.project.api.common.model.ResponseMessage;
import com.btone.project.api.common.specification.CommonSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

	private final PostRepository repository;
	private final PostSearchAndFilterRepository postSearchAndFilterrepository;
	private final MessageSourceAccessor messageSource;

	public ResponseMessage methods(String method, PostVO input) {
		Map<String, Object> searchKeys = new HashMap<>();

		if(CommonMethods.CREATE.getKey().equals(method)) {
			return create(input);
		} else if(CommonMethods.UPDATE.getKey().equals(method) || CommonMethods.DELETE.getKey().equals(method)) {
			return update(method, input, searchKeys);
		} else if(CommonMethods.SEARCH.getKey().equals(method)) {
			return search(input, searchKeys);
		}

		return ResponseMessage.of(null, HttpStatus.BAD_REQUEST, messageSource.getMessage("common.error.wrong-method"));
	}

	public ResponseMessage create(PostVO input) {
		try {
			Board board = Board.builder()
					.boardSn(input.getBoardSn())
					.build();

			Post post = Post.builder()
					.board(board)
					.title(input.getTitle())
					.contents(input.getContents())
					.writer(input.getWriter())
					.build();

			repository.save(post);
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, messageSource.getMessage("board.create.success"));
	}

	public ResponseMessage update(String method, PostVO input, Map<String, Object> searchKeys) {
		String message = messageSource.getMessage("board.update.success");
		Post post = null;
		try {
			searchKeys.put("delYn", "N");
			searchKeys.put("postSn", input.getPostSn());
			Optional<Post> optionalRole = repository.findOne(CommonSpecification.searchCondition(searchKeys));

			if(optionalRole.isEmpty()) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"));
			}

			post = optionalRole.get();

			if(CommonMethods.UPDATE.getKey().equals(method)) {
				post.setTitle(input.getTitle());
				post.setContents(input.getContents());
			}else {
				post.setDelYn("Y");
				message = messageSource.getMessage("board.delete.success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(null, message);
	}

	public ResponseMessage search(PostVO input, Map<String, Object> searchKeys) {
		List<Post> list = new ArrayList<>();
		try {
			list = postSearchAndFilterrepository.search(PostSearchCondition.build(input.getBoardSn(), input.getPostSn(), input.getTitle(), input.getContents(), input.getWriter()));

			if(list.size() == 0) {
				return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("board.notexists"));
			}
		} catch (Exception e) {
			return ResponseMessage.of(null, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.error", new String[] {e.getMessage()}));
		}

		return ResponseMessage.ok(list, messageSource.getMessage("board.search.success"));
	}
}

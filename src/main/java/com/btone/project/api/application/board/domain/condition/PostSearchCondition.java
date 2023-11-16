package com.btone.project.api.application.board.domain.condition;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSearchCondition {

	private Integer boardSn;
	private Integer postSn;

	public static PostSearchCondition build(Integer boardSn, Integer postSn) {
		PostSearchCondition postSearch = new PostSearchCondition();
		postSearch.boardSn = boardSn;
		postSearch.postSn = postSn;
		return postSearch;
	}
}

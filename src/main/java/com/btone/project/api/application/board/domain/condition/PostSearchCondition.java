package com.btone.project.api.application.board.domain.condition;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSearchCondition {

	private Integer boardSn;
	private Integer postSn;
	private String title;
	private String contents;
	private String writer;

	public static PostSearchCondition build(Integer boardSn, Integer postSn, String title, String contents, String writer) {
		PostSearchCondition postSearch = new PostSearchCondition();
		postSearch.boardSn = boardSn;
		postSearch.postSn = postSn;
		postSearch.title = title;
		postSearch.contents = contents;
		postSearch.writer = writer;
		return postSearch;
	}
}

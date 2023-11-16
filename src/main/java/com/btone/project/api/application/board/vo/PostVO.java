package com.btone.project.api.application.board.vo;

import lombok.Data;

@Data
public class PostVO {
	private Integer postSn;
	private Integer boardSn;
	private String title;
	private String contents;
	private String writer;
	private String delYn;
}


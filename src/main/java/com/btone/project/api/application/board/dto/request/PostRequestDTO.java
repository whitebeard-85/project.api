package com.btone.project.api.application.board.dto.request;

import lombok.Data;

@Data
public class PostRequestDTO {
	private Integer postSn;
	private Integer boardSn;
	private String title;
	private String contents;
	private String writer;
	private String startDate;
	private String endDate;
	private String useYn;
	private String delYn;
}


package com.btone.project.api.application.board.dto.request;

import lombok.Data;

@Data
public class BoardRequestDTO {

	private Integer boardSn;
	private String boardType;
	private String boardNm;
	private String boardDesc;
	private String delYn;
}

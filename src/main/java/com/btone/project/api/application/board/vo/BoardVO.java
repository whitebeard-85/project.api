package com.btone.project.api.application.board.vo;

import lombok.Data;

@Data
public class BoardVO {

	private Integer boardSn;
	private String boardType;
	private String boardNm;
	private String boardDesc;
	private String delYn;
}

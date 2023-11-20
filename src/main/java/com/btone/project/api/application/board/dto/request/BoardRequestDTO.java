package com.btone.project.api.application.board.dto.request;

import lombok.Data;

@Data
public class BoardRequestDTO {

	private Integer boardSn;
	private String boardTypeCd;
	private String boardNm;
	private String boardDesc;
	private String startDate;
	private String endDate;
	private String useYn;
	private String delYn;
}

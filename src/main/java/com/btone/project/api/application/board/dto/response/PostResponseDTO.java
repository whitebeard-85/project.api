package com.btone.project.api.application.board.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;


@Data
public class PostResponseDTO {
	private Integer boardSn;
	private String boardType;
	private String boardNm;
	private String boardDesc;

	private Integer postSn;
	private String title;
	private String contents;
	private String writer;
	private String delYn;
	private String actvNm;

	@QueryProjection
	public PostResponseDTO(Integer boardSn, String boardType, String boardNm, String boardDesc, Integer postSn,
			String title, String contents, String writer, String delYn, String actvNm) {
		this.boardSn = boardSn;
		this.boardType = boardType;
		this.boardNm = boardNm;
		this.boardDesc = boardDesc;
		this.postSn = postSn;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.delYn = delYn;
		this.actvNm = actvNm;
	}
}

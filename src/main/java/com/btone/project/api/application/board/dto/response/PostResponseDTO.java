package com.btone.project.api.application.board.dto.response;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;


@Data
public class PostResponseDTO {
	private Integer boardSn;
	private Integer postSn;
	private String title;
	private String contents;
	private String actvNm;
	private String delYn;
	private String createdDate;
	private String modifiedDate;

	@QueryProjection
	public PostResponseDTO(Integer boardSn, Integer postSn, String title, String contents, String actvNm,
			String delYn, String createdDate, String modifiedDate) {
		this.boardSn = boardSn;
		this.postSn = postSn;
		this.title = title;
		this.contents = contents;
		this.actvNm = actvNm;
		this.delYn = delYn;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
}

package com.btone.project.api.application.board.dto.request;

import java.util.List;

import com.btone.project.api.application.common.dto.request.FileRequestDTO;

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

	private FileRequestDTO file;
	private List<FileRequestDTO> fileList;
}


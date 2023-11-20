package com.btone.project.api.application.board.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.btone.project.api.application.board.enums.BoardType;
import com.btone.project.api.common.domain.model.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @packageName   : com.btone.project.api.application.board.entity
* @fileName      : Board.java
* @author        : 오수병
* @date          : 2023.11.15
* @description   : 게시판정보 엔티티
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.15        오수병                최초 생성
*/
@Getter //lombok getter
@Setter //lombok setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //엔티티 정의
@Table(name="tb_board") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class Board extends BaseTimeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_sn")
    private Integer boardSn;

	@Enumerated(EnumType.STRING)
	@ColumnDefault("'GENERAL'")
	private BoardType boardType;

	private String boardNm;
	private String boardDesc;
	private String startDate;
	private String endDate;

	@ColumnDefault("'Y'")
	private String useYn;

	@ColumnDefault("'N'")
	private String delYn;
}

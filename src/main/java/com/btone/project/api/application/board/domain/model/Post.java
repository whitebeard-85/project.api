package com.btone.project.api.application.board.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.btone.project.api.common.domain.model.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //lombok getter
@Setter //lombok setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //엔티티 정의
@Table(name="tb_posts") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class Post extends BaseTimeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_sn")
    private Integer postSn;

    private Integer boardSn;
	private String title;
	private String contents;
	private String writer;
	private String startDate;
	private String endDate;

	@ColumnDefault("'N'")
	private String delYn;
}

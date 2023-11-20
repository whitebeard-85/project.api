package com.btone.project.api.application.common.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.btone.project.api.common.domain.model.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name="tb_code") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@IdClass(CodePK.class)
public class Code extends BaseTimeEntity {

	@Id
	private String grpCd;

	@Id
    private String cd;

	private String cdNm;
    private String desc1;
    private String desc2;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sort;

    @ColumnDefault("'Y'")
    private String useYn;

    @ColumnDefault("'N'")
    private String delYn;
}

package com.btone.project.api.application.auth.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.btone.project.api.common.domain.model.BaseTimeEntity;
import com.btone.project.api.common.util.PasswordConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

/**
* @packageName   : com.btone.project.api.auth.entity
* @fileName      : User.java
* @author        : 오수병
* @date          : 2023.11.13
* @description   : 사용자정보 엔티티
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.11.13        오수병                최초 생성
*/
@Getter //lombok getter
@Setter //lombok setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //엔티티 정의
@Table(name="tb_user") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class User extends BaseTimeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_sn")
    private Integer userSn;

	private String userId;

	@Convert(converter = PasswordConverter.class)
    private String pwd; // 비밀번호

    private String actvNm; // 닉네임

    @ColumnDefault("'Y'")
    private String agreeYn;

    private String roleCd;

    @ColumnDefault("'N'")
    private String rsPwdYn;

    @Convert(converter = PasswordConverter.class)
    private String tmpPwd;

    private String socialYn;
    private String socialType; // KAKAO, NAVER, GOOGLE
    private String accessToken; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    @ColumnDefault("'N'")
    private String delYn;
}

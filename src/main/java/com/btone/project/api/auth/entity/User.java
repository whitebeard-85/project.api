package com.btone.project.api.auth.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.btone.project.api.common.entity.BaseTimeEntity;
import com.btone.project.api.common.util.PasswordConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

@Getter //lombok getter
@Setter //lombok setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //엔티티 정의
@Table(name="tb_account") //사용하지 않으면 클래스 이름이 테이블 이름이 됨
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)
    private String refreshToken; // 리프레시 토큰

    @ColumnDefault("'Y'")
    private String agreeYn;

    @ColumnDefault("'N'")
    private String delYn;

    @ColumnDefault("'N'")
    private String rsPwdYn;

    private String tmpPwd;

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.pwd = passwordEncoder.encode(this.pwd);
    }

    //== 유저 필드 업데이트 ==//
    public void updateActvNm(String updateActvNm) {
        this.actvNm = updateActvNm;
    }

    public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
        this.pwd = passwordEncoder.encode(updatePassword);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}

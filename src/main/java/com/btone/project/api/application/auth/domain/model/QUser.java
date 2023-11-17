package com.btone.project.api.application.auth.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1583847938L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.btone.project.api.common.entity.QBaseTimeEntity _super = new com.btone.project.api.common.entity.QBaseTimeEntity(this);

    public final StringPath actvNm = createString("actvNm");

    public final StringPath agreeYn = createString("agreeYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath pwd = createString("pwd");

    public final StringPath refreshToken = createString("refreshToken");

    public final QRole role;

    public final StringPath rsPwdYn = createString("rsPwdYn");

    public final StringPath socialId = createString("socialId");

    public final EnumPath<com.btone.project.api.application.auth.enums.SocialType> socialType = createEnum("socialType", com.btone.project.api.application.auth.enums.SocialType.class);

    public final StringPath tmpPwd = createString("tmpPwd");

    public final StringPath userId = createString("userId");

    public final NumberPath<Integer> userSn = createNumber("userSn", Integer.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}


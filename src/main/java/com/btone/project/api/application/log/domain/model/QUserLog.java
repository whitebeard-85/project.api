package com.btone.project.api.application.log.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLog is a Querydsl query type for UserLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLog extends EntityPathBase<UserLog> {

    private static final long serialVersionUID = -116275038L;

    public static final QUserLog userLog = new QUserLog("userLog");

    public final StringPath accessToken = createString("accessToken");

    public final StringPath actvNm = createString("actvNm");

    public final StringPath agreeYn = createString("agreeYn");

    public final StringPath delYn = createString("delYn");

    public final StringPath pwd = createString("pwd");

    public final StringPath roleCd = createString("roleCd");

    public final StringPath rsPwdYn = createString("rsPwdYn");

    public final StringPath socialType = createString("socialType");

    public final StringPath socialYn = createString("socialYn");

    public final StringPath tmpPwd = createString("tmpPwd");

    public final StringPath userId = createString("userId");

    public final NumberPath<Integer> userSn = createNumber("userSn", Integer.class);

    public QUserLog(String variable) {
        super(UserLog.class, forVariable(variable));
    }

    public QUserLog(Path<? extends UserLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLog(PathMetadata metadata) {
        super(UserLog.class, metadata);
    }

}


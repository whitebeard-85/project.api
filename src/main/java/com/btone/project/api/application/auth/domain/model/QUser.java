package com.btone.project.api.application.auth.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1583847938L;

    public static final QUser user = new QUser("user");

    public final com.btone.project.api.common.domain.model.QBaseTimeEntity _super = new com.btone.project.api.common.domain.model.QBaseTimeEntity(this);

    public final StringPath accessToken = createString("accessToken");

    public final StringPath actvNm = createString("actvNm");

    public final StringPath agreeYn = createString("agreeYn");

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final StringPath pwd = createString("pwd");

    public final StringPath roleCd = createString("roleCd");

    public final StringPath rsPwdYn = createString("rsPwdYn");

    public final StringPath socialType = createString("socialType");

    public final StringPath socialYn = createString("socialYn");

    public final StringPath tmpPwd = createString("tmpPwd");

    public final StringPath userId = createString("userId");

    public final NumberPath<Integer> userSn = createNumber("userSn", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}


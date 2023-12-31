package com.btone.project.api.application.auth.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 1583754925L;

    public static final QRole role = new QRole("role");

    public final com.btone.project.api.common.domain.model.QBaseTimeEntity _super = new com.btone.project.api.common.domain.model.QBaseTimeEntity(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final StringPath roleCd = createString("roleCd");

    public final StringPath roleDesc = createString("roleDesc");

    public final StringPath roleNm = createString("roleNm");

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final StringPath useYn = createString("useYn");

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}


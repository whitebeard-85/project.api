package com.btone.project.api.application.common.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QCode is a Querydsl query type for Code
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCode extends EntityPathBase<Code> {

    private static final long serialVersionUID = 1658651175L;

    public static final QCode code = new QCode("code");

    public final com.btone.project.api.common.domain.model.QBaseTimeEntity _super = new com.btone.project.api.common.domain.model.QBaseTimeEntity(this);

    public final StringPath cd = createString("cd");

    public final StringPath cdNm = createString("cdNm");

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    public final StringPath desc1 = createString("desc1");

    public final StringPath desc2 = createString("desc2");

    public final StringPath grpCd = createString("grpCd");

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final StringPath useYn = createString("useYn");

    public QCode(String variable) {
        super(Code.class, forVariable(variable));
    }

    public QCode(Path<? extends Code> path) {
    	super(path.getType(), path.getMetadata());
    }

    public QCode(PathMetadata metadata) {
    	super(Code.class, metadata);
    }

}


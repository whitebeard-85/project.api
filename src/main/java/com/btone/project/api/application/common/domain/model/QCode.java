package com.btone.project.api.application.common.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCode is a Querydsl query type for Code
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCode extends EntityPathBase<Code> {

    private static final long serialVersionUID = 1658651175L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCode code = new QCode("code");

    public final com.btone.project.api.common.domain.model.QBaseTimeEntity _super = new com.btone.project.api.common.domain.model.QBaseTimeEntity(this);

    public final StringPath cd = createString("cd");

    public final StringPath cdNm = createString("cdNm");

    public final QCodeGrp codeGrp;

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    public final StringPath desc1 = createString("desc1");

    public final StringPath desc2 = createString("desc2");

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final StringPath useYn = createString("useYn");

    public QCode(String variable) {
        this(Code.class, forVariable(variable), INITS);
    }

    public QCode(Path<? extends Code> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCode(PathMetadata metadata, PathInits inits) {
        this(Code.class, metadata, inits);
    }

    public QCode(Class<? extends Code> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.codeGrp = inits.isInitialized("codeGrp") ? new QCodeGrp(forProperty("codeGrp")) : null;
    }

}


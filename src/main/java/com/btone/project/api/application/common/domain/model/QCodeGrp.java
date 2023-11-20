package com.btone.project.api.application.common.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCodeGrp is a Querydsl query type for CodeGrp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCodeGrp extends EntityPathBase<CodeGrp> {

    private static final long serialVersionUID = -721514178L;

    public static final QCodeGrp codeGrp = new QCodeGrp("codeGrp");

    public final com.btone.project.api.common.domain.model.QBaseTimeEntity _super = new com.btone.project.api.common.domain.model.QBaseTimeEntity(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    public final StringPath desc1 = createString("desc1");

    public final StringPath desc2 = createString("desc2");

    public final StringPath grpCd = createString("grpCd");

    public final StringPath grpCdNm = createString("grpCdNm");

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final StringPath useYn = createString("useYn");

    public QCodeGrp(String variable) {
        super(CodeGrp.class, forVariable(variable));
    }

    public QCodeGrp(Path<? extends CodeGrp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodeGrp(PathMetadata metadata) {
        super(CodeGrp.class, metadata);
    }

}


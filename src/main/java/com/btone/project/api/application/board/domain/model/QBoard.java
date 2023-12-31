package com.btone.project.api.application.board.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1480099821L;

    public static final QBoard board = new QBoard("board");

    public final com.btone.project.api.common.domain.model.QBaseTimeEntity _super = new com.btone.project.api.common.domain.model.QBaseTimeEntity(this);

    public final StringPath boardDesc = createString("boardDesc");

    public final StringPath boardNm = createString("boardNm");

    public final NumberPath<Integer> boardSn = createNumber("boardSn", Integer.class);

    public final StringPath boardTypeCd = createString("boardTypeCd");

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    public final StringPath endDate = createString("endDate");

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final StringPath startDate = createString("startDate");

    public final StringPath useYn = createString("useYn");

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}


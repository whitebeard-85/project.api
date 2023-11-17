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

    public final EnumPath<com.btone.project.api.application.board.enums.BoardType> boardType = createEnum("boardType", com.btone.project.api.application.board.enums.BoardType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

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


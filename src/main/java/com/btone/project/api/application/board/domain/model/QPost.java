package com.btone.project.api.application.board.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -1337310535L;

    public static final QPost post = new QPost("post");

    public final com.btone.project.api.common.domain.model.QBaseTimeEntity _super = new com.btone.project.api.common.domain.model.QBaseTimeEntity(this);

    public final NumberPath<Integer> boardSn = createNumber("boardSn", Integer.class);

    public final StringPath contents = createString("contents");

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    public final StringPath endDate = createString("endDate");

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> postSn = createNumber("postSn", Integer.class);

    public final StringPath startDate = createString("startDate");

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}


package com.mhkim.board.entity.board;

import com.mhkim.board.entity.BaseTimeEntity;
import com.mhkim.board.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor
@ToString
@DynamicUpdate
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(Long boardId, String title, String content, User user) {
        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(isNotEmpty(content), "content must be provided.");
        checkNotNull(user, "user must be provided.");

        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void setBoardUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

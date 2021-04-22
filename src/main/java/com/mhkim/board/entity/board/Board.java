package com.mhkim.board.entity.board;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.mhkim.board.entity.BaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.PersistenceConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@DynamicUpdate
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long boardId;
    private String userName;
    private String title;
    private String content;

    @Builder
    @PersistenceConstructor
    public Board(Long boardId, String userName, String title, String content) {

        checkArgument(isNotEmpty(userName), "username must be provided.");
        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(isNotEmpty(content), "content must be provided.");

        this.boardId = boardId;
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public void setBoardUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

package com.mhkim.board.controller.board.dto;

import com.mhkim.board.entity.user.User;
import org.springframework.beans.BeanUtils;

import com.mhkim.board.entity.board.Board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class BoardDto {

    @Getter
    @Setter
    public static class Response {

        @ApiModelProperty(value = "게시글 ID")
        private Long boardId;

        @ApiModelProperty(value = "제목")
        private String title;

        @ApiModelProperty(value = "내용")
        private String content;

        @ApiModelProperty(value = "작성자 정보")
        private User user;

        public Response(Board source) {
            BeanUtils.copyProperties(source, this);
        }

    }

    @Getter
    @Setter
    public static class Add {

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

        @ApiModelProperty(value = "작성자 아이디", required = true)
        private Long userId;

    }

    @Getter
    @Setter
    public static class Mod {

        @ApiModelProperty(value = "게시글 ID")
        private Long boardId;

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

    }

    @Getter
    @Setter
    public static class Del {

        @ApiModelProperty(value = "게시글 ID")
        private Long boardId;

    }

}

package com.mhkim.board.controller.dto;

import org.springframework.beans.BeanUtils;

import com.mhkim.board.entity.Board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

public class BoardDto {

    @Getter
    @Setter
    public static class Response {

        @ApiModelProperty(value = "게시글 ID")
        private Long boardId;

        @ApiModelProperty(value = "글쓴이")
        private String userName;

        @ApiModelProperty(value = "제목")
        private String title;

        @ApiModelProperty(value = "내용")
        private String content;

        public Response(Board source) {
            BeanUtils.copyProperties(source, this);
        }

    }

    @Getter
    @Setter
    public static class Add {

        @ApiModelProperty(value = "글쓴이", required = true)
        private String userName;

        @ApiModelProperty(value = "제목", required = true)
        private String title;

        @ApiModelProperty(value = "내용", required = true)
        private String content;

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

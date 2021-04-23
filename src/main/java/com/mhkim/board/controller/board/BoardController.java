package com.mhkim.board.controller.board;

import static com.mhkim.board.common.ApiResult.ok;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mhkim.board.advice.exception.CDataNotFoundException;
import com.mhkim.board.common.ApiResult;
import com.mhkim.board.controller.board.dto.BoardDto;
import com.mhkim.board.service.board.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"1. Board"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 목록 조회")
    @GetMapping(value = "/boards")
    public ApiResult<List<BoardDto.Response>> getBoardList() {
        return ok(boardService.getBoardList().stream()
                .map(BoardDto.Response::new)
                .collect(toList()), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping(value = "/board/{boardId}")
    public ApiResult<BoardDto.Response> getBoard(@PathVariable("boardId") Long boardId) {
        return ok(boardService.getBoard(boardId)
                .map(BoardDto.Response::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 추가")
    @PostMapping(value = "/board")
    public ApiResult<BoardDto.Response> addBoard(@RequestBody BoardDto.Add param) {
        return ok(boardService.addBoard(param.getTitle(), param.getContent(), param.getUserId())
                .map(BoardDto.Response::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시글 수정")
    @PatchMapping(value = "/board")
    public ApiResult<BoardDto.Response> updateBoard(@RequestBody BoardDto.Mod param) {
        return ok(boardService.updateBoard(param.getBoardId(), param.getTitle(), param.getContent())
                .map(BoardDto.Response::new)
                .orElseThrow(CDataNotFoundException::new), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping(value = "/board")
    public ApiResult<BoardDto.Response> deleteBoard(@RequestBody BoardDto.Del param) {
        boardService.deleteBoard(param.getBoardId());
        return ok(HttpStatus.NO_CONTENT);
    }

}

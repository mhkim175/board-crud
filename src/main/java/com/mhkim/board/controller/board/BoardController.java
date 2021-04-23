package com.mhkim.board.controller.board;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.mhkim.board.entity.board.Board;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mhkim.board.advice.exception.CDataNotFoundException;
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
    public ResponseEntity<List<BoardDto.Response>> getBoardList() {
        return ResponseEntity.ok(
                boardService.getBoardList().stream()
                        .map(BoardDto.Response::new)
                        .collect(toList())
        );
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping(value = "/board/{boardId}")
    public ResponseEntity<BoardDto.Response> getBoard(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok(
                boardService.getBoard(boardId)
                        .map(BoardDto.Response::new)
                        .orElseThrow(() -> new CDataNotFoundException("Board not found"))
        );
    }

    @ApiOperation(value = "게시글 추가")
    @PostMapping(value = "/board")
    public ResponseEntity<BoardDto.Response> addBoard(@RequestBody BoardDto.Add param) {
        Board board = boardService.addBoard(param.getTitle(), param.getContent(), param.getUserId());
        return new ResponseEntity<>(new BoardDto.Response(board), HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시글 수정")
    @PatchMapping(value = "/board")
    public ResponseEntity<BoardDto.Response> updateBoard(@RequestBody BoardDto.Mod param) {
        Board board = boardService.updateBoard(param.getBoardId(), param.getTitle(), param.getContent());
        return ResponseEntity.ok(new BoardDto.Response(board));
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping(value = "/board")
    public ResponseEntity<BoardDto.Response> deleteBoard(@RequestBody BoardDto.Del param) {
        Board board = boardService.deleteBoard(param.getBoardId());
        return ResponseEntity.ok(new BoardDto.Response(board));
    }

}

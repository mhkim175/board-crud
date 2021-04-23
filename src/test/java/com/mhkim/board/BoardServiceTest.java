package com.mhkim.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.mhkim.board.entity.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mhkim.board.entity.board.Board;
import com.mhkim.board.service.board.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    private Board board;
    private User user;

    @BeforeAll
    void setUp() {
        Long boardId = null;
        String userName = "mhkim";
        String title = "title";
        String content = "content";
        user = User.builder().userId(1L).build();
        board = new Board(boardId, title, content, user);
    }

    @Test
    @Order(1)
    void 게시글_등록() {
        Board boardResult = boardService.addBoard(board.getTitle(), board.getContent(), user.getUserId());
        assertThat(boardResult).isNotNull();
        assertThat(boardResult.getTitle()).isEqualTo(board.getTitle());
        assertThat(boardResult.getContent()).isEqualTo(board.getContent());
        log.info("Board: {}", boardResult);
    }

    @Test
    @Order(2)
    void 게시글_전체_조회() {
        List<Board> boards = boardService.getBoardList();
        assertThat(boards).isNotNull().hasSize(1);
        log.info("Found All: {}", boards);
    }

    @Test
    @Order(3)
    void 게시글_단건_조회() {
        Board boardResult = boardService.getBoard(1L).orElse(null);
        assertThat(boardResult).isNotNull();
        log.info("Found by {}: {}", 1L, boardResult);
    }

    @Test
    @Order(4)
    void 게시글_수정() {
        String title = "updatedtitle";
        String content = "updatedcontent";
        Board boardResult = boardService.updateBoard(1L, title, content);
        assertThat(boardResult).isNotNull();
        log.info("Updated Board: {}", boardResult);
    }

    @Test
    @Order(5)
    void 게시글_삭제() {
        Long boardId = 1L;
        boardService.deleteBoard(boardId);
        log.info("Deleted Board: BoardId is {}", boardId);
    }

}

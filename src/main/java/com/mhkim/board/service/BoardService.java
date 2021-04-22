package com.mhkim.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhkim.board.entity.Board;
import com.mhkim.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Transactional
    public Optional<Board> addBoard(String userName, String title, String content) {
        Board board = Board.builder()
                .userName(userName)
                .title(title)
                .content(content).build();
        return Optional.of(boardRepository.save(board));
    }

    @Transactional
    public Optional<Board> updateBoard(Long boardId, String title, String content) {
        return getBoard(boardId).map(board -> {
            board.setBoardUpdate(title, content);
            return board;
        });
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

}

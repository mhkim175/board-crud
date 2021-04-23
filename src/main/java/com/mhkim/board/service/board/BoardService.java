package com.mhkim.board.service.board;

import java.util.List;
import java.util.Optional;

import com.mhkim.board.advice.exception.CDataNotFoundException;
import com.mhkim.board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhkim.board.entity.board.Board;
import com.mhkim.board.repository.board.BoardRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Transactional
    public Board addBoard(String title, String content, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    Board board = Board.builder()
                            .title(title)
                            .content(content)
                            .user(user)
                            .build();
                    return boardRepository.save(board);
                }).orElseThrow(() -> new CDataNotFoundException("User not found"));
    }

    @Transactional
    public Board updateBoard(Long boardId, String title, String content) {
        return getBoard(boardId)
                .map(board -> {
                    board.setBoardUpdate(title, content);
                    return board;
                }).orElseThrow(() -> new CDataNotFoundException("Board not found"));
    }

    @Transactional
    public Board deleteBoard(Long boardId) {
        return getBoard(boardId)
                .map(board -> {
                    boardRepository.deleteById(board.getBoardId());
                    return board;
                }).orElseThrow(() -> new CDataNotFoundException("Board not found"));
    }

}

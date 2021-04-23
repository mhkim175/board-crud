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
    public Optional<Board> addBoard(String title, String content, Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    Board board = Board.builder()
                            .title(title)
                            .content(content)
                            .user(user)
                            .build();
                    return Optional.of(boardRepository.save(board));
                }).orElseThrow(() -> new CDataNotFoundException());
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

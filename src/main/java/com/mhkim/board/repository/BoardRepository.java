package com.mhkim.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhkim.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}

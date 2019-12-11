package com.hyeokdev.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyeokdev.board.model.BoardArticle;

public interface BoardRepository extends JpaRepository<BoardArticle, Integer>{
}

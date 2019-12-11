package com.hyeokdev.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyeokdev.board.model.BoardFile;

public interface BoardFileRepository extends JpaRepository<BoardFile, Integer>{
}

package com.hyeokdev.board.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="board_file")
@Data
public class BoardFile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_id", nullable=false, unique=true, updatable=false)
	private int fileId;
	
	@Column(name="file_original_name", nullable=false)
	private String orgNm;
	
	@Column(name="file_changed_name", nullable=false)
	private String chgNm;
	
	@Column(name="file_size", nullable=false)
	private long fileSize;
	
	@Column(name="file_regdate", nullable=false, updatable=false)
	private LocalDateTime regdate = LocalDateTime.now();
}

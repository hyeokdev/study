package com.hyeokdev.board.model;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="board_article")
@Data
public class BoardArticle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="article_id", nullable=false, unique=true, updatable=false)
	private int articleId;
	
	@NotBlank(message="제목을 입력해주세요.")
	@Column(name="article_subject")
	private String subject;
	
	@NotBlank(message="내용을 입력해주세요.")
	@Column(name="article_contents")
	private String contents;
	
	@NotBlank(message="작성자 이름이 존재하지 않습니다.")
	@Column(name="article_writer")
	private String writer;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="article_regdate", nullable=false, updatable=false)
	private LocalDateTime regdate = LocalDateTime.now();
	
	@Column(name="article_uptdate", nullable=true)
	private LocalDateTime uptdate; 
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="article_id")
	private Collection<BoardFile> fileList;
}

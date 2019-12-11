package com.hyeokdev.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.hyeokdev.board.model.BoardArticle;

public interface BoardService {
	final static String SUCCESS_INSERT = "작성 완료 했습니다.";
	final static String SUCCESS_UPDATE = "수정 완료 했습니다.";
	final static String SUCCESS_DELETE = "삭제 완료 했습니다.";
	final static String NOT_MATCH_PASSWORD = "비밀번호가 일치하지 않습니다.";
	Page<BoardArticle> getArticleListPage(Pageable pageable);
	List<BoardArticle> getArticleList();
	BoardArticle getArticle(int boardId);
	BoardArticle insertArticle(BoardArticle article);
	String updateArticle(int boardId, BoardArticle article);
	void deleteArticle(int boardId);
	String filesUpload(int boardId, MultipartFile[] files);
	void fileDownload(int fileId, HttpServletResponse response);
}

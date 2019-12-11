package com.hyeokdev.board.service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hyeokdev.board.model.BoardArticle;
import com.hyeokdev.board.model.BoardFile;
import com.hyeokdev.board.repository.BoardFileRepository;
import com.hyeokdev.board.repository.BoardRepository;
import com.hyeokdev.error.BoardException;
import com.hyeokdev.util.FileUtil;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardFileRepository boardFileRepository;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public List<BoardArticle> getArticleList() {
		return boardRepository.findAll();
	}

	@Override
	public BoardArticle getArticle(int boardId) {
		Optional<BoardArticle> optional = boardRepository.findById(boardId);
		optional.orElseThrow(() -> new BoardException("게시물을 찾을 수 없습니다."));
		return optional.get();
	}

	@Override
	public BoardArticle insertArticle(BoardArticle article) {
		BoardArticle newArticle = boardRepository.save(article);
		return newArticle;
	}

	@Override
	public String updateArticle(int boardId, BoardArticle article) {
		String msg = SUCCESS_UPDATE;
		article.setUptdate(LocalDateTime.now());
		boardRepository.save(article);
		return msg;
	}

	@Override
	public void deleteArticle(int boardId) {
		boardRepository.deleteById(boardId);
	}

	@Override
	public String filesUpload(int boardId, MultipartFile[] files) {
		BoardArticle article = getArticle(boardId);
		List<BoardFile> fileList = fileUtil.parseFileInfo(files);
		article.setFileList(fileList);
		boardRepository.save(article);
		return "성공";
	}

	@Override
	public Page<BoardArticle> getArticleListPage(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Override
	public void fileDownload(int fileId, HttpServletResponse response) {
		Optional<BoardFile> optional = boardFileRepository.findById(fileId);
		if(optional.isPresent()) {
			BoardFile file = optional.get();
			try {
				byte[] files = FileUtils.readFileToByteArray(new File(file.getChgNm()));
				response.setContentType("application/octet-stream");
				response.setContentLength(files.length);
				response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(file.getOrgNm(),"UTF-8")+"\";");
				response.setHeader("Content-Transfer-Encoding", "binary");
				
				response.getOutputStream().write(files);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new BoardException("파일이 존재하지 않습니다.");
		}
	}

}

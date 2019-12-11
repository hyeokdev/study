package com.hyeokdev.board.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyeokdev.board.model.BoardArticle;
import com.hyeokdev.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/board")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public ResponseEntity<Page<BoardArticle>> articleList(Pageable pageable) {
		Page<BoardArticle> articleList = boardService.getArticleListPage(pageable);
		return new ResponseEntity<Page<BoardArticle>>(articleList, HttpStatus.OK);
	}
	
	@GetMapping("/{boardId}")
	public ResponseEntity<BoardArticle> articleDetail(@PathVariable("boardId") int boardId) {
		BoardArticle article = boardService.getArticle(boardId);
		return new ResponseEntity<BoardArticle>(article, HttpStatus.OK);
	}
	
	@GetMapping("/post")
	public ResponseEntity<String> articleForm() throws Exception{
		return new ResponseEntity<String>("test", HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<BoardArticle> articleInsert(@Valid @RequestBody BoardArticle article) throws Exception{
		BoardArticle newArticle= boardService.insertArticle(article);
		return new ResponseEntity<BoardArticle>(newArticle, HttpStatus.OK);
	}
	
	@PutMapping("/{boardId}/update")
	public ResponseEntity<String> articleUpdate(@PathVariable("boardId") int boardId, @Valid @RequestBody BoardArticle article) {
		String msg = boardService.updateArticle(boardId, article);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@DeleteMapping("/{boardId}")
	public ResponseEntity<String> articleDelete(@PathVariable("boardId") int boardId) {
		boardService.deleteArticle(boardId);
		return new ResponseEntity<String>("" , HttpStatus.OK);
	}
	
	@PostMapping("/{boardId}/files")
	public ResponseEntity<String> filesUpload(@PathVariable("boardId") int boardId, @RequestBody MultipartFile[] files) {
		String msg = boardService.filesUpload(boardId, files);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/{fileId}/download")
	public void downloadFile(@PathVariable("fileId") int fileId, HttpServletResponse response) {
		boardService.fileDownload(fileId, response);
	}
	
	 @RequestMapping(value = "{_:^(?!index\\.html|api).$}") 
	    public String redirectApi() {
	        log.info("URL entered directly into the Browser, so we need to redirect...");
	        return "forward:/";
    }
}

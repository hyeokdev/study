package com.hyeokdev.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hyeokdev.board.model.BoardFile;
import com.hyeokdev.error.BoardException;


@Component
public class FileUtil {
	public List<BoardFile> parseFileInfo(MultipartFile[] files) {
		System.out.println(files.length);
		if(files.length<1) {
			return null;
		}
		List<BoardFile> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		File file = new File(path);
		if(file.exists()==false) {
			file.mkdir();
		}
		
		String newFileName, originalFileExtension, contentType;
		for(MultipartFile articleFile : files) {
			if(articleFile.getContentType()!=null) {
				contentType = articleFile.getContentType();
				if(contentType.isEmpty()) {
					break;
				} else {
					if(contentType.contains("image/jpeg")) {
						originalFileExtension = ".jpg";
					} else if(contentType.contains("image/png")) {
						originalFileExtension = ".png";
					} else if(contentType.contains("image/gif")) {
						originalFileExtension = ".gif";
					} else {
						break;
					}
				}
				newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
				BoardFile boardFile = new BoardFile();
				boardFile.setFileSize(articleFile.getSize());
				boardFile.setOrgNm(articleFile.getOriginalFilename());
				boardFile.setChgNm(path + "/" + newFileName);
				System.out.println(boardFile.toString());
				fileList.add(boardFile);
				file = new File(path + "/" + newFileName);
				try {
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
					bos.write(articleFile.getBytes());
					bos.flush();
					bos.close();
				} catch (IOException e) {
					throw new BoardException("파일업로드 오류입니다.");
				}
			}
		}
		return fileList;
	}
}

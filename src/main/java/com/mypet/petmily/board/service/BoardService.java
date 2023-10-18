package com.mypet.petmily.board.service;

import java.util.*;

import com.mypet.petmily.fileUpload.dao.FileUploadMapper;
import com.mypet.petmily.fileUpload.dto.FileUploadDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.petmily.board.dao.BoardMapper;
import com.mypet.petmily.board.dto.BoardDTO;

@Service
public class BoardService {
	
	private final BoardMapper boardMapper;

	private final FileUploadMapper fileUploadMapper;
	
    public BoardService(BoardMapper boardMapper, FileUploadMapper fileUploadMapper) {

		this.boardMapper = boardMapper;
		this.fileUploadMapper = fileUploadMapper;
    }

	//게시판 목록
    public List<BoardDTO> selectBoardList(BoardDTO boardDTO) throws Exception {
    	int startIndex = (boardDTO.getPage() - 1 ) * boardDTO.getRow();
    	boardDTO.setStartIndex(startIndex);
		return boardMapper.selectBoardList(boardDTO);
    }

	//게시판 1건 상세조회
	public BoardDTO selectBoard(BoardDTO boardDTO) throws Exception {
		return boardMapper.selectBoard(boardDTO);
	}
    
	//게시판 총 개수
	public int selectBoardCnt(BoardDTO boardDTO) throws Exception {
		return boardMapper.selectBoardCnt(boardDTO);
	}
	
	//게시판 저장
	@Transactional
	public int insertBoard(BoardDTO boardDTO) throws Exception {
		return boardMapper.insertBoard(boardDTO);
	}
	
	//게시판 수정
	@Transactional
	public int updateBoard(BoardDTO boardDTO) throws Exception {
		return boardMapper.updateBoard(boardDTO);
	}

	//게시판 삭제
	@Transactional
	public int deleteBoard(Integer boardNo) throws Exception {
		return boardMapper.deleteBoard(boardNo);
	}


	//게시판 조회수 증가
	@Transactional
	public int updateBoardViews(Integer boardNo) throws Exception {
		return boardMapper.updateBoardViews(boardNo);
	}
}


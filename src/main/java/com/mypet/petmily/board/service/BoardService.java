package com.mypet.petmily.board.service;

import com.mypet.petmily.board.dao.BoardMapper;
import com.mypet.petmily.board.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    //게시판 목록
    public List<BoardDTO> selectBoardList(BoardDTO boardDTO) throws Exception {
        int startIndex = (boardDTO.getPage() - 1 ) * boardDTO.getRow();
        boardDTO.setStartIndex(startIndex);
        return boardMapper.selectBoardList(boardDTO);
    }

    //게시판 총 개수
    public int selectBoardCnt(BoardDTO boardDTO) throws Exception {
        return boardMapper.selectBoardCnt(boardDTO);
    }

}
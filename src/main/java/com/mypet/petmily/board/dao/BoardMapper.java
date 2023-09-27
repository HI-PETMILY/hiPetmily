package com.mypet.petmily.board.dao;

import com.mypet.petmily.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardMapper {

    //게시판 목록조회
    List<BoardDTO> selectBoardList(BoardDTO boardDTO) throws Exception;

    //게시판 총 개수
    int selectBoardCnt(BoardDTO boardDTO) throws Exception;
}


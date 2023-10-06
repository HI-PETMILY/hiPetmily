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

    //게시판 1건 상세조회
    BoardDTO selectBoard(BoardDTO boardDTO) throws Exception;

    //게시판 저장
    int insertBoard(BoardDTO boardDTO) throws Exception;

    //게시판 수정
    int updateBoard(BoardDTO boardDTO) throws Exception;

    //게시판 삭제
    int deleteBoard(Integer boardNo) throws Exception;

    //게시판 조회수 업데이트
    int updateBoardViews(Integer boardNo) throws Exception;
}
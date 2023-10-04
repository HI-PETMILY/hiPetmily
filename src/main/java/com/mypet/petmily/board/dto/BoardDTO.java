package com.mypet.petmily.board.dto;

import lombok.Data;

@Data
public class BoardDTO {

    /** ############## 테이블 컬럼 ##############*/
    /** 게시판 번호 */
    private Integer boardNo;
    /** 게시판 제목 */
    private String boardName;
    /** 게시판 내용 */
    private String boardDetail;
    /** 게시판 등록,수정일 조회수*/
    private String boardRegistDate;
    private String boardViews;
    private String boardReviseDate;
    /** 게시판 상태 Y사용중 N삭제 */
    private String boardStat;
    private String boardDeleteDate;
    /** 게시판 구분 1=공지 */
    private String boardSort;
    private String boardSecret;
    /** 회원번호 */
    private Integer memberNo;
    /** 게시판 카테고리 코드 */
    private Integer categoryCode;
    /** 게시판 카테고리 명 */
    private String categoryName;
    private Integer refBoardNo;

    /** ############# 페이징 관련 변수 ################ */
    /** 현재페이지 */
    private int page = 1;
    /** 페이지당 출력 개수 */
    private int row = 5;
    //oracle용 변수
    private int startIndex = 1;

    /** ############# 검색기능 관련 변수 ################ */

    /** 검색구분 제목or내용 */
    private String searchType;
    /*검색 키워드 */
    private String searchTxt;

}
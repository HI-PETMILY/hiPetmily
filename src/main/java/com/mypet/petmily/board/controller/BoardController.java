    package com.mypet.petmily.board.controller;


    import com.mypet.petmily.board.CreatePagingTag;
    import com.mypet.petmily.board.dto.BoardDTO;
    import com.mypet.petmily.board.service.BoardService;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.servlet.ModelAndView;

    @Slf4j
    @Controller
    @RequestMapping("/board")
    public class BoardController {



        private final BoardService boardService;

        public BoardController(BoardService boardService) {
            this.boardService = boardService;
        }


        //게시판-공지사항-수정 페이지 이동
        @GetMapping("/notice/edit")
        public void BoardEdit(){}

        //게시판-공지사항-등록 페이지 이동
        @GetMapping("/notice/write")
        public void BoardWrite(){}

        //게시판-공지사항-조회 페이지 이동
        @GetMapping("/notice/view")
        public void BoardView(){}

        //게시판-공지사항 목록 페이지 이동
        @GetMapping("/notice/list")
        public ModelAndView noticeList(Model m, BoardDTO boardDTO) throws Exception {

            log.info("Controller @GetMapping(/board/notice/list) 게시판-공지사항 목록 화면이동 >>>>>>>>>>>>>>> ");

            // 데이터와 뷰를 동시에 설정이 가능
            ModelAndView mv = new ModelAndView();
		/*
	  		BOARD_SORT
			1 = 공지
			2 = 질문답
			3 = 자주하는질문
		 */

            //게시판 - 공지사항 조회 이기 때문에 BOARD_SORT는 1로 셋팅
            boardDTO.setBoardSort("1");

            if(boardDTO.getPage() > 1) {
                boardDTO.setStartIndex((boardDTO.getPage() - 1 ) * 10);
            }
            int totalCnt = boardService.selectBoardCnt(boardDTO);

            //get 파라미터 url 셋팅
            String paramUrl ="/board/notice/list?searchType="+ CreatePagingTag.chkStringNull(boardDTO.getSearchType())+"&searchTxt="+CreatePagingTag.chkStringNull(boardDTO.getSearchTxt());

            //jsp > html(화면 맵핑)
            mv.setViewName("board/notice/list");
            //리스트 객체 셋팅
            mv.addObject("list", boardService.selectBoardList(boardDTO));
            //총 갯수 셋팅
            mv.addObject("totalCnt", totalCnt);
            //공통 페이징 태그 셋팅
            mv.addObject("pagingHtml", CreatePagingTag.getPagingElement(totalCnt, boardDTO.getPage(), boardDTO.getRow(), paramUrl));

            return mv;
        }

    }

package com.mypet.petmily.board.controller;


import com.mypet.petmily.board.CreatePagingTag;
import com.mypet.petmily.board.dto.BoardDTO;
import com.mypet.petmily.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    //게시판-공지사항 등록/수정 페이지 이동
    @GetMapping("/notice/write")
    public ModelAndView noticeWrite(BoardDTO boardDTO) throws Exception {
        // 데이터와 뷰를 동시에 설정이 가능
        ModelAndView mv = new ModelAndView();
        //html 화면 맵핑
        mv.setViewName("board/notice/write");
        //기등록된 게시물을 상세조회했을때 게시판번호 기준으로 상세내용을 DB에서 조회한다.
        if(boardDTO.getBoardNo() != null) {
            mv.addObject("boardDTO", boardService.selectBoard(boardDTO));
        }

        return mv;
    }

    //게시판-공지사항 신규 insert
    @PostMapping("/notice/insert")
    public String noticeInsert(BoardDTO boardDTO) throws Exception {
        log.info("Controller @GetMapping(/board/notice/insert) 게시판-공지사항 신규 insert >>>>>>>>>>>>>>> ");
        //로그인사용자번호 임시 셋팅
        boardDTO.setMemberNo(1);
		/*
		 * 	BOARD_SORT
			1 = 공지
			2 = 질문답
			3 = 자주하는질문
		 */
        //게시판 - 공지사항 저장이기 때문에 BOARD_SORT는 1로 셋팅
        boardDTO.setBoardSort("1");
        log.info("boardDTO >>>>>>>>>>>>>>>>>>>>>>>>>>> {} ",boardDTO.toString());

        int result = boardService.insertBoard(boardDTO);
        log.info("게시판-공지사항 신규 insert 결과 >>>>>>>>>>>>>>> {}", result);
        //redirect란? 컨트롤러의 RequestMapping URL을 호출한다.
        return "redirect:/board/notice/list";
    }

    //게시판-공지사항 수정 update
    @PostMapping("/notice/update")
    public String noticeUpdate(BoardDTO boardDTO) throws Exception {

        log.info("Controller @GetMapping(/board/notice/update) 게시판-공지사항 수정 update >>>>>>>>>>>>>>> ");
        //로그인사용자번호 임시 셋팅
        boardDTO.setMemberNo(1);
        int result = boardService.updateBoard(boardDTO);
        log.info("게시판-공지사항 수정 update 결과 >>>>>>>>>>>>>>> {}", result);
        //redirect란? 컨트롤러의 RequestMapping URL을 호출한다.
        return "redirect:/board/notice/list";

    }

    //게시판-공지사항 삭제 delete
    @PostMapping("/notice/delete")
    public String noticeDelete(BoardDTO boardDTO) throws Exception {
        log.info("Controller @GetMapping(/board/notice/delete) 게시판-공지사항 삭제 delete >>>>>>>>>>>>>>> ");
        int result = boardService.deleteBoard(boardDTO.getBoardNo());
        log.info("게시판-공지사항 삭제 delete 결과 >>>>>>>>>>>>>>> {}", result);
        //redirect란? 컨트롤러의 RequestMapping URL을 호출한다.
        return "redirect:/board/notice/list";
    }

    //게시판-공지사항 상세보기 페이지 이동
    @GetMapping("/notice/view")
    public ModelAndView noticeView(BoardDTO boardDTO) throws Exception {
        // 데이터와 뷰를 동시에 설정이 가능
        ModelAndView mv = new ModelAndView();
        //html 화면 맵핑
        mv.setViewName("board/notice/view");
        //기등록된 게시물을 상세조회했을때 게시판번호 기준으로 상세내용을 DB에서 조회한다.
        if(boardDTO.getBoardNo() != null) {
            mv.addObject("boardDTO", boardService.selectBoard(boardDTO));
            boardService.updateBoardViews(boardDTO.getBoardNo()); //현재 게시판번호 게시물 조회수 증가처리
        }
        return mv;
    }
}

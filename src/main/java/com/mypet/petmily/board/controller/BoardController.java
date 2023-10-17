package com.mypet.petmily.board.controller;

import com.mypet.petmily.member.dto.MemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mypet.petmily.board.CreatePagingTag;
import com.mypet.petmily.board.dto.BoardDTO;
import com.mypet.petmily.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    
    //게시판 목록 페이지 이동
    @GetMapping("/{boardType}/list")
    public ModelAndView boardList( @PathVariable("boardType") String boardType, BoardDTO boardDTO, @AuthenticationPrincipal MemberDTO memberDTO) throws Exception {

		log.info("Controller @GetMapping(/board/"+boardType+"/list) 게시판 목록 화면이동 >>>>>>>>>>>>>>> ");
    	
		// 데이터와 뷰를 동시에 설정이 가능 
		ModelAndView mv = new ModelAndView(); 
		/* 
		 * 	BOARD_SORT
			1 = 공지
			2 = 질문답
			3 = 자주하는질문
		 */
		//게시판 - 공지사항 조회 이기 때문에 BOARD_SORT는 1로 셋팅

		if( "notice".equals(boardType) ) {
			boardDTO.setBoardSort("1");
		}else if( "qna".equals(boardType) ){
			boardDTO.setBoardSort("2");
		}else if( "faq".equals(boardType) ){
			boardDTO.setBoardSort("3");
		}
   	
		if(boardDTO.getPage() > 1) {
			boardDTO.setStartIndex((boardDTO.getPage() - 1 ) * 10);
		}
		
		//html 화면 맵핑
		mv.setViewName("board/"+boardType+"/list"); 

		//게시판타입이 공지사항,질문과답변일때만 데이터베이스에서 리스트를 조회한다.
		//단순 컨텐츠성 페이지가  존재하기 때문. ex) 자주하는질문은 컨텐츠페이지.
		if(boardDTO.getBoardSort() == "1" || boardDTO.getBoardSort() == "2" || boardDTO.getBoardSort() == "3") {
			//페이징을위한 총개수 가져오기
			int totalCnt = boardService.selectBoardCnt(boardDTO);
			//get 파라미터 url 셋팅
			String paramUrl =	"/board/"+boardType+"/list?searchType="+CreatePagingTag.chkStringNull(boardDTO.getSearchType())+"&searchTxt="+CreatePagingTag.chkStringNull(boardDTO.getSearchTxt());
			//리스트 객체 셋팅

			log.info("목록조회 결과");
			log.info(boardService.selectBoardList(boardDTO).toString());

			mv.addObject("list", boardService.selectBoardList(boardDTO));
			//총 갯수 셋팅
			mv.addObject("totalCnt", totalCnt);
			//공통 페이징 태그 셋팅
			mv.addObject("pagingHtml", CreatePagingTag.getPagingElement(totalCnt, boardDTO.getPage(), boardDTO.getRow(), paramUrl));

			if(memberDTO != null){
				log.info("현재 로그인한 유저의 권한은 : {} 입니다. ",memberDTO.getAuthorities().toString());
				mv.addObject("memberRole", memberDTO.getAuthorities().toString());
			}

			log.info("pagingHtml >>>> {}",CreatePagingTag.getPagingElement(totalCnt, boardDTO.getPage(), boardDTO.getRow(), paramUrl));

		}
		
        return mv;
    }
    
    //게시판 등록/수정 페이지 이동
    @GetMapping("/{boardType}/write")
    public ModelAndView boardWrite(@PathVariable("boardType") String boardType, BoardDTO boardDTO, @AuthenticationPrincipal MemberDTO memberDTO) throws Exception {
    	log.info("Controller @GetMapping(/board/"+boardType+"/insert) 게시판 등록 페이지 이동 >>>>>>>>>>>>>>> ");

		//로그인을 한 사용자만 등록하는 페이지를 접근할 수 있다.

		if(memberDTO == null){
			throw new Exception("로그인 후 이용 해주세요.");
		}

		// 데이터와 뷰를 동시에 설정이 가능
		ModelAndView mv = new ModelAndView(); 
		
		//html 화면 맵핑
		mv.setViewName("board/"+boardType+"/write"); 
		
		//기등록된 게시물을 상세조회했을때 게시판번호 기준으로 상세내용을 DB에서 조회한다.
		if(boardDTO.getBoardNo() != null) {
			mv.addObject("boardDTO", boardService.selectBoard(boardDTO));			
		}
		
		return mv;
    }
    
    //게시판 신규 insert
    @PostMapping("/{boardType}/insert")
    public String boardInsert(@PathVariable("boardType") String boardType, BoardDTO boardDTO,  @AuthenticationPrincipal MemberDTO member) throws Exception {
    	log.info("Controller @GetMapping(/board/"+boardType+"/insert) 게시판 신규등록 insert >>>>>>>>>>>>>>> ");
		log.info("member >>>>>>>>>>>>>>>>>> {}",member.toString());
    	//로그인사용자번호 임시 셋팅
		//member_No가 long타입이니 int타입으로 변환
    	boardDTO.setMemberNo(Math.toIntExact(member.getMemberNo()));
		/* 
		 * 	BOARD_SORT
			1 = 공지
			2 = 질문답
			3 = 자주하는질문
		 */
		//게시판 - 공지사항 저장이기 때문에 BOARD_SORT는 1로 셋팅
		if( "notice".equals(boardType) ){
			boardDTO.setBoardSort("1");
		}else {
			boardDTO.setBoardSort("2");
		}
		
    	log.info("boardDTO >>>>>>>>>>>>>>>>>>>>>>>>>>> {} ",boardDTO.toString());
    	
		int result = boardService.insertBoard(boardDTO);
    	log.info("게시판 신규 insert 결과 >>>>>>>>>>>>>>> {}", result);
    	//redirect란? 컨트롤러의 RequestMapping URL을 호출한다.
        return "redirect:/board/"+boardType+"/list";
    }
    
    //게시판-공지사항 수정 update
    @PostMapping("/{boardType}/update")
    public String boardUpdate(@PathVariable("boardType") String boardType, BoardDTO boardDTO) throws Exception {
    	
    	log.info("Controller @GetMapping(/board/"+boardType+"/update) 게시판 수정 update >>>>>>>>>>>>>>> ");
    	
		/* 
		 * 	BOARD_SORT
			1 = 공지
			2 = 질문답
			3 = 자주하는질문
		 */
		//게시판 - 공지사항 저장이기 때문에 BOARD_SORT는 1로 셋팅
		if( "notice".equals(boardType) ){
			boardDTO.setBoardSort("1");
		}else {
			boardDTO.setBoardSort("2");
		}
    	
    	//로그인사용자번호 임시 셋팅
    	boardDTO.setMemberNo(1);
		int result = boardService.updateBoard(boardDTO);
    	log.info("게시판 수정 update 결과 >>>>>>>>>>>>>>> {}", result);
    	//redirect란? 컨트롤러의 RequestMapping URL을 호출한다.
    	return "redirect:/board/"+boardType+"/list";

    }

    //게시판-공지사항 삭제 delete
    @PostMapping("/{boardType}/delete")
    public String boardDelete(@PathVariable("boardType") String boardType, BoardDTO boardDTO) throws Exception {
    	log.info("Controller @GetMapping(/board/"+boardType+"/delete) 게시판 삭제 delete >>>>>>>>>>>>>>> ");
		/* 
		 * 	BOARD_SORT
			1 = 공지
			2 = 질문답
			3 = 자주하는질문
		 */
		//게시판 - 공지사항 저장이기 때문에 BOARD_SORT는 1로 셋팅
		if( "notice".equals(boardType) ){
			boardDTO.setBoardSort("1");
		}else {
			boardDTO.setBoardSort("2");
		}
    	
    	int result = boardService.deleteBoard(boardDTO.getBoardNo());
    	log.info("게시판 삭제 delete 결과 >>>>>>>>>>>>>>> {}", result);
    	//redirect란? 컨트롤러의 RequestMapping URL을 호출한다.
    	return "redirect:/board/"+boardType+"/list";
    }
    
    //게시판-공지사항 상세보기 페이지 이동
    @GetMapping("/{boardType}/view")
    public ModelAndView boardView(@PathVariable("boardType") String boardType, BoardDTO boardDTO, @AuthenticationPrincipal MemberDTO memberDTO) throws Exception {
    	log.info("Controller @GetMapping(/board/"+boardType+"/view) 게시판-상세페이지 이동 >>>>>>>>>>>>>>> ");
		/* 
		 * 	BOARD_SORT
			1 = 공지
			2 = 질문답
			3 = 자주하는질문
		 */
		//게시판 - 공지사항 저장이기 때문에 BOARD_SORT는 1로 셋팅
		if( "notice".equals(boardType) ){
			boardDTO.setBoardSort("1");
		}else {
			boardDTO.setBoardSort("2");
		}
    	// 데이터와 뷰를 동시에 설정이 가능 
		ModelAndView mv = new ModelAndView(); 
		//html 화면 맵핑
		mv.setViewName("board/"+boardType+"/view"); 
		//기등록된 게시물을 상세조회했을때 게시판번호 기준으로 상세내용을 DB에서 조회한다.
		if(boardDTO.getBoardNo() != null) {
			mv.addObject("boardDTO", boardService.selectBoard(boardDTO));
			//조회수 증가는 공지사항일때만
			if( "notice".equals(boardType) ){
				boardService.updateBoardViews(boardDTO.getBoardNo()); //현재 게시판번호 게시물 조회수 증가처리
			}
			//추가
			if(memberDTO != null){
				mv.addObject("memberRole", memberDTO.getAuthorities().toString());
			}
		}
		return mv;
    }


	@GetMapping("service")
	public String Service(){
		return "board/service";
	}

	@GetMapping("usingprocess")
	public String UsingProcess(){
		return "board/usingprocess";
	}
}

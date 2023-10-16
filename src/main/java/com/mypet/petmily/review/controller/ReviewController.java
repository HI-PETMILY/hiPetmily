package com.mypet.petmily.review.controller;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ReservationHistoryDTO;
import com.mypet.petmily.review.dto.ReservationInfoDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import com.mypet.petmily.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/member")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    /* 후기 작성 페이지 */
    @GetMapping("/review-write")
    public void reviewWritePage(@AuthenticationPrincipal MemberDTO loginMember,
                                @RequestParam Map<String, Object> requestMap,
                                Model model){

        requestMap.put("memberNo", loginMember.getMemberNo());

        ReservationHistoryDTO reservationInfo = reviewService.viewReservationInfo(requestMap);

        log.info("후기 작성 페이지 reservationInfo : {}" , reservationInfo);
        model.addAttribute("reservInfo", reservationInfo);
    }

    @PostMapping("/review-write")
    public String reviewWrite(ReviewDTO review, @AuthenticationPrincipal MemberDTO loginMember
                              ){

        review.setMember(loginMember);

        reviewService.registReview(review);

        log.info("후기 등록 시 정보 review : {}", review);

        return "redirect:/member/reservationList";
    }


    /* 후기 전체 조회 페이지 */
    @GetMapping("/review-list")
    public String reviewListPage(@RequestParam(defaultValue = "1") int page,
                                 Model model, @AuthenticationPrincipal MemberDTO loginMember) {

        log.info("reviewList page : {}", page);

        /*ReservationHistoryDTO reserveInfo = reviewService.selectReserveInfo(loginMember);
        model.addAttribute("reserveInfo", reserveInfo);*/

        Map<String, Object> reviewListAndPaging = reviewService.selectReviewList(page, loginMember);
        model.addAttribute("paging", reviewListAndPaging.get("paging"));
        model.addAttribute("reviewList", reviewListAndPaging.get("reviewList"));

        return "member/review-list";
    }

    /* 후기 상세 조회 페이지 */
    @GetMapping("/review-view")
    public void reviewViewPage(){}

    /* 후기 수정 페이지 */
    @GetMapping("/review-update")
    public void reviewUpdate(){}

    /* 후기 삭제 */
    @GetMapping("/review-delete")
    public String reviewDelete(){

        return "redirect:/member/review-list";
    }

}

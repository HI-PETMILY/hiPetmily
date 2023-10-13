package com.mypet.petmily.review.controller;

import com.mypet.petmily.member.dto.MemberDTO;
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

import java.util.HashMap;
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
    @GetMapping("/review_write")
    public void reviewWritePage(){}

    @PostMapping("/review_write")
    public String reviewWrite(ReviewDTO review, @AuthenticationPrincipal MemberDTO loginMember){

        review.setMember(loginMember);

        log.info("loginMember : {}", loginMember);

        reviewService.registReview(review);

        log.info("review : {}", review);

        return "redirect:/member/reservationList";
    }

    /* 후기 전체 조회 페이지 */
    @GetMapping("/review-list")
    public String reviewListPage(@RequestParam(defaultValue = "1") int page,
                                 Model model) {
        log.info("reviewList page : {}", page);

        Map<String, String> searchMap = new HashMap<>();

        Map<String, Object> reviewListAndPaging = reviewService.selectReviewList(searchMap, page);
        model.addAttribute("paging", reviewListAndPaging.get("paging"));
        model.addAttribute("reviewList", reviewListAndPaging.get("reserveList"));

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

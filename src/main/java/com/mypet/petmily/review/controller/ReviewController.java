package com.mypet.petmily.review.controller;

import com.mypet.petmily.fileUpload.dto.FileUploadDTO;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ProgressReserveDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import com.mypet.petmily.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

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

        ProgressReserveDTO reserve = reviewService.viewReservationInfo(requestMap);
        log.info("후기 작성 페이지 reserve Info : {}" , reserve);
        model.addAttribute("reserve", reserve);

    }

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @PostMapping("/review-write")
    public String reviewWrite(@ModelAttribute("review") ReviewDTO review,
                              @AuthenticationPrincipal MemberDTO loginMember,
                              MultipartFile reviewImg){

        /*String fileUploadDir = IMAGE_DIR + "original";
        String thumbnailDir = IMAGE_DIR + "thumbnail";

        File dir1 = new File(fileUploadDir);
        File dir2 = new File(thumbnailDir);

        if (!dir1.exists() || !dir2.exists()) {
            dir1.mkdirs();
            dir2.mkdirs();
        }

        FileUploadDTO file = new FileUploadDTO();

        try{
            if(reviewImg.getSize()>0){
                String originalFileName = reviewImg.getOriginalFilename();

                String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID() + ext;

                reviewImg.transferTo(new File(thumbnailDir + "/" + savedFileName));

                FileUploadDTO fileInfo = new FileUploadDTO();

                fileInfo.setFileOriName(originalFileName);
                fileInfo.setFileExtName(ext);
                fileInfo.setFileSaveName(savedFileName);
                fileInfo.setFileMemberNo(loginMember.getMemberNo());
                fileInfo.setFilePetNo(Math.toIntExact(review.getRevCode()));
                fileInfo.setFilePathName("/upload/original/");
                fileInfo.setFileType("MainImg");
                Thumbnails.of(fileUploadDir + "/" + savedFileName).size(780, 560)
                        .toFile(thumbnailDir + "/thumbnail_" + savedFileName);
                fileInfo.setFile_T_PathName("/upload/thumbnail/thumbnail_" + savedFileName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        review.setReviewImg(file);
        log.info("후기 첨부파일 이미지 : {}", file);*/

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

        Map<String, Object> reviewListAndPaging = reviewService.selectReviewList(page, loginMember);
        model.addAttribute("paging", reviewListAndPaging.get("paging"));
        model.addAttribute("reviewList", reviewListAndPaging.get("reviewList"));

        return "member/review-list";
    }

    /* 후기 상세 조회 페이지 */
    @GetMapping("/review-view")
    public void reviewViewPage(@AuthenticationPrincipal MemberDTO loginMember, Model model,
                               @RequestParam int revCode){

        ReviewDTO review = reviewService.viewReview(loginMember, revCode);
        model.addAttribute("review", review);
    }

    /* 후기 수정 페이지 */
    @GetMapping("/review-update")
    public void reviewUpdate(){}

    /* 후기 삭제 */
    @GetMapping("/review-delete")
    public String reviewDelete(){

        return "redirect:/member/review-list";
    }

}

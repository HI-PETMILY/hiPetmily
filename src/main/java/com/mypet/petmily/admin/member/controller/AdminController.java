package com.mypet.petmily.admin.member.controller;

import com.mypet.petmily.admin.member.service.AdminService;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.dto.MemberRoleDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //전체회원 조회
    @GetMapping("/main-member")
    public String getMemberList(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(required = false) String searchCondition,
                                @RequestParam(required = false) String searchValue,
                                @RequestParam(required = false) String rating, // 여기서 "rating" 파라미터를 받음
                                Model model) {

        if (rating == null) {
            rating = "전체";
        }

        log.info("검색버튼 클릭 시 들어오는 체크박스 선택 값 : {}", rating);                      //등급 체크박스 값 넘어오는지 확인
// 서비스 클래스에서 총 회원 수와 페이징 처리된 회원 목록을 가져오기

        /////////////////////////////////////////////////////////////////////////////////////
        // 서비스로 라디오체크 값 넘김 (ex : [일반회원, 펫시터회원], [일반회원], [펫시터회원], null)
        Map<String, Object> memberListAndPaging = adminService.getMemberList(page, searchCondition, searchValue, rating);
        // 밑에 기존코드
        // Map<String, Object> memberListAndPaging = adminService.getMemberList(page, searchCondition, searchValue);
        /////////////////////////////////////////////////////////////////////////////////////

        //탈퇴한지 180일 지난 회원 자동으로 삭제
        adminService.deleteMemebr();
        ////////////////////////////////////////////

        List<MemberDTO> memberList = (List<MemberDTO>) memberListAndPaging.get("memberList");
        int totalCount = adminService.getTotalMemberCount(searchCondition, searchValue, rating);
//      int searchResultCount = memberList.size(); // 검색 결과의 일치하는 전체 회원 수

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("memberList", memberList);
        model.addAttribute("paging", memberListAndPaging.get("paging"));

        return "admin/member/main-member";
    }

    @GetMapping("/secession")
    public String getSecessionPage() {

        return "admin/member/secession";
    }

    @GetMapping("/board")
    public String getBoardPage() {

        return "admin/member/board";
    }

    /* 팝업 =============================================================================*/
//
//    /* 1대1문의 팝업 @@@@@@@@@@@@@@@*/
//    @GetMapping("/popInquiry")
//    public String getPoP_inquiryPage() {
//
//        return "admin/member/popInquiry";
//    }

    /* 화원 정보 관리 조회 팝업 @@@@@@@@@@@@*/
    @GetMapping("/popManagement")
    public String getPoP_managementPage(@RequestParam("id") int id, Model model) {

        // 회원정보 보기 //
        //ID를 기반으로 멤버의 상세 정보를 검색하고 해당 정보를 뷰로 전달.
        List<MemberDTO> member = adminService.getPoP_managementPageById(id);
        model.addAttribute("member", member);
        log.info("member 잘 전달되냐? : {}", member);

        return "admin/member/popManagement";
    }

    /*회원수정===========*/
    @PostMapping("/popManagementResult")
    public String setPoP_managementResultPage(@RequestParam("no") int no,
                                              @RequestParam("memberType") int memberType,
                                              @RequestParam("stat") String stat,
                                              @RequestParam("point") int point,
                                              Model model) {

        log.info("1파라미터 잘 전달되냐? : {}, {}, {}, {}", no, memberType, stat, point);
        adminService.setPoP_managementResultPage(no, memberType, stat, point);

        model.addAttribute("id", no);
        log.info("2파라미터 잘 전달되냐? : {}, {}, {}, {}", no, memberType, stat, point);

        return "admin/member/popManagementResult";
    }

    /*여기까지 ==================================================================================*/

    @GetMapping("/comment")
    public String getCommentPage() {

        return "admin/member/comment";
    }

    @GetMapping("/applyform")
    public String getApplyFormPage() {

        return "admin/member/applyform";
    }


    //펫시터 신청관리 ===========
    @GetMapping("/management")
    public String getPetSitterList(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(required = false) String searchCondition,
                                   @RequestParam(required = false) String searchValue,
                                   @RequestParam(required = false) String apply, // 여기서 "apply" 파라미터를 받음
                                   Model model) {

        if (apply == null) {
            apply = "전체";
        }

        log.info("검색버튼 클릭 시 들어오는 체크박스 선택 값 : {}", apply); //대기 or 승인

        Map<String, Object> PetSitterListAndPaging = adminService.getPetSitterList(page, searchCondition, searchValue, apply);

//        //탈퇴한지 180일 지난 회원 자동으로 삭제
//        adminService.deleteMemebr();
//        ////////////////////////////////////////////

        List<PetSitterDTO> petSitterList = (List<PetSitterDTO>) PetSitterListAndPaging.get("petSitterList");
        int totalCount = adminService.getTotalPetSitterCount(searchCondition, searchValue, apply);
//      int searchResultCount = memberList.size(); // 검색 결과의 일치하는 전체 회원 수

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("petSitterList", petSitterList);
        model.addAttribute("paging", PetSitterListAndPaging.get("paging"));

        return "admin/member/management";
    }

    /* 펫시터 신청 폼 조회 팝업 @@@@@@@@@@@@*/
    @GetMapping("/popViewPetSitterApplicForm")
    public String getPoP_popViewPetSitterApplicFormPage(@RequestParam("id") int id, Model model) {

        // 펫시터 폼 보기 //
        //ID를 기반으로 멤버의 상세 정보를 검색하고 해당 정보를 뷰로 전달.
//        List<MemberDTO> member = adminService.getPoP_managementPageById(id);
//        model.addAttribute("member", member);
//        log.info("member 잘 전달되냐? : {}", member);

        return "admin/member/popViewPetSitterApplicForm";
    }

    @GetMapping("/reservation")
    public String getReservationPage() {

        return "admin/member/reservation";
    }

    @GetMapping("/automail")
    public String getAutoMailPage() {

        return "admin/MailManagement/automail";
    }

    @GetMapping("/atmosphere")
    public String getAtmospherePage() {

        return "admin/statistics/atmosphere";
    }

    @GetMapping("/complete")
    public String getCompletePage() {

        return "admin/statistics/complete";
    }

    @GetMapping("/sales")
    public String getSalesPage() {

        return "admin/statistics/sales";
    }


}

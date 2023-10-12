package com.mypet.petmily.member.controller;

import com.mypet.petmily.common.exception.member.*;
import com.mypet.petmily.common.paging.Pagenation;
import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.member.dto.PetDTO;
import com.mypet.petmily.member.service.AuthenticationService;
import com.mypet.petmily.member.service.MemberService;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;


@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final PasswordEncoder passwordEncoder;

    @Value("${image.image-dir}")
    private String IMAGE_DIT;

    public MemberController(MemberService memberService,
                            AuthenticationService authenticationService,
                            MessageSourceAccessor messageSourceAccessor,
                            PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.authenticationService = authenticationService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.passwordEncoder = passwordEncoder;
    }


    /* 회원 가입 메인 페이지 이동 */
    @GetMapping("/mainRegist")
    public void mainRegistPage(){}

    /* 회원 가입 페이지 이동 */
    @GetMapping("/regist")
    public void registPage(){}

    /* 회원 가입 완료 페이지 */
    @GetMapping("/completedRegist")
    public void comRegistPage(){}


    /* 회원 가입 */
    @PostMapping("/regist")
    public String registMember(MemberDTO member, Integer postNo, String address, String address2,
                               RedirectAttributes rttr) throws MemberRegistException {

        member.setPostNo(postNo);
        member.setAddress(address);
        member.setAddress2(address2);
        member.setMemberPwd(passwordEncoder.encode(member.getPassword()));

        log.info("Request regist member : {}", member);

        memberService.registMember(member);

        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTime().getTime());

        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        member.setMemberStatDate(currentTimestamp);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
        rttr.addFlashAttribute("nickname", member.getNickName());
        return "redirect:/member/completedRegist";
    }

    /* 닉네임 중복 확인 */
    @PostMapping("/nickNameDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody MemberDTO member) {

        log.info("Request Check nickName : {}", member.getNickName());

        String result = "사용 가능한 닉네임입니다.";

        if(memberService.selectMemberByNickName(member.getNickName())) {
            result = "중복 된 닉네임이 존재합니다.";
        }
        return ResponseEntity.ok(result);
    }

    /* 이메일 중복 확인 */
    @PostMapping("/idDupCheck")
    public ResponseEntity<String> checkDuplication2(@RequestBody MemberDTO member) {

        log.info("Request Check memberId : {}", member.getMemberId());

        String result = "사용 가능한 이메일입니다.";

        if(memberService.selectMemberByMemberId(member.getMemberId())) {
            result = "중복 된 이메일이 존재합니다.";
        }
        return ResponseEntity.ok(result);
    }


    /* 내 정보 확인 페이지로 이동 - 현재 로그인한 사용자의 정보를 받아온다. 객체는 MemberDTO.*/
    @GetMapping("/update")
    public void modifyPage(
            @AuthenticationPrincipal MemberDTO member){

    }


    /* 회원 정보 수정 */
    @PostMapping("/update")
    public String modifyMember(MemberDTO modifyMember, Integer postNo, String address, String address2,
                               @AuthenticationPrincipal MemberDTO loginMember, RedirectAttributes rttr) throws MemberModifyException {

        modifyMember.setPostNo(postNo);
        modifyMember.setAddress(address);
        modifyMember.setAddress2(address2);
        modifyMember.setMemberNo(loginMember.getMemberNo());

        log.info("modifyMember request Member : {}", modifyMember);

        memberService.modifyMember(modifyMember);

        /* 로그인 시 저장 된 Authentication 객체를 변경 된 정보로 교체한다. 아래 메소드 참조 */
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(loginMember.getMemberId()));

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));

        return "redirect:/member/mypage";
    }

    /* 임시 비밀번호 생성 */
    protected Authentication createNewAuthentication(String memberId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth
                = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;
    }


    /* 패스워드 변경 페이지로 이동 */
    @GetMapping("/updatePassword")
    public void updatePasswordPage(@AuthenticationPrincipal MemberDTO member){}


    /* 패스워드 변경하기 */
    @PostMapping("/updatePassword")
    public String modifyPassword(MemberDTO modifyPassword,
                                 @AuthenticationPrincipal MemberDTO loginMember, RedirectAttributes rttr,
                                  String updatePassword1,
                                  String updatePassword2) throws MemberPasswordUpdateException {

        if (updatePassword1.equals(updatePassword2)) {
            String hashedPassword = passwordEncoder.encode(updatePassword1);
            modifyPassword.setMemberId(loginMember.getMemberId());
            modifyPassword.setMemberPwd(hashedPassword);
            modifyPassword.setMemberNo(loginMember.getMemberNo());

            memberService.modifyPassword(modifyPassword);

            SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(modifyPassword.getMemberId()));

            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify.success"));

        } else {
            rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify.password.error"));
        }

        return "redirect:/";
    }

    /* 회원 탈퇴 */
    @GetMapping("/delete")
    public String deleteMember(@AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) throws MemberRemoveException {

        log.info("login member : {}", member); //

        memberService.removeMember(member);

        SecurityContextHolder.clearContext();

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.delete"));

        return "redirect:/";
    }


    /* 로그인 화면 */
    @GetMapping("/login")
    public void loginPage(){}

    /* 로그인 실패 시 */
    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr){
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/member/login";
    }

    /* 아이디 찾기 화면 */
    @GetMapping("/find_id")
    public void findIdPwdPage(){}

    /*아이디 찾기 결과 */
    @PostMapping("/find_id")
    public String findId(@RequestParam("memberName") String memberName,
                         @RequestParam("phone") String phone,
                         Model model) {
        System.out.println("name: " + memberName + ", phone: " + phone);
        String result = memberService.findId(memberName, phone);
        if(result != null){
            model.addAttribute("result", result);   // 검색 결과를 Model에 추가
            // model.addAttribute("showResult", true); // 결과를 보여주기 위한 플래그 추가
        }else {
            model.addAttribute("findIdError", "해당하는 사용자를 찾을 수 없습니다.");
        }
        return "/member/find_id_result";
    }

    /* 비밀번호 찾기 페이지 */
    @GetMapping("/find_pwd")
    public void findPwdPage(){}

    /* 비밀번호 찾기 결과 */
    @PostMapping("/find_pwd")
    public String findPwdCheck(HttpServletRequest request, Model model,
                               @RequestParam String memberName, @RequestParam String memberId,
                               MemberDTO dto){

        try{
            dto.setMemberId(memberId);
            dto.setMemberName(memberName);
            int search = memberService.pwdCheck(dto);

            if(search == 0){
                model.addAttribute("message", "입력 정보가 잘못되었습니다. 다시 입력해주세요.");
            }

            char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                    'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

            String tempPwd="";
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                idx = (int) (charSet.length * Math.random());
                tempPwd += charSet[idx];
            }

            dto.setMemberPwd(tempPwd);
            memberService.pwdUpdate(dto);
            model.addAttribute("tempPwd", tempPwd);

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message", "오류가 발생했습니다.");
        }
        return "member/find_pwd_result";
    }

    /* ============================ 마이페이지 ============================ */

    @GetMapping("/mypage")
    public String headerMemberMypage(@AuthenticationPrincipal MemberDTO member, Model model) {

        model.addAttribute("member", member.getMemberId());

        PetDTO petProfile = memberService.viewFirstPetProfile(member);

        model.addAttribute("petProfile", petProfile);

        return "member/mypage";
    }

    /* 반려동물 프로필 등록 페이지 */
    @GetMapping("/pet-profile-regist")
    public void petProfileRegist(@AuthenticationPrincipal MemberDTO loginMember, Model model){

        model.addAttribute("loginMember", loginMember.getMemberId());
    }

    /* 반려동물 프로필 등록 */
    @Value("/src/main/resources/upload")
    private String IMAGE_DIR;

    @PostMapping("/pet-profile-regist")
    public String registPetProfile(PetDTO pet, List<MultipartFile> petProfileImg,
                                   @AuthenticationPrincipal MemberDTO loginMember,
                                   RedirectAttributes rttr) throws PetProfileException {


        log.info("pet profile request : {}", pet);
        log.info("pet profile image request : {}", petProfileImg);

        String petImgDir = IMAGE_DIR + "petProfile";

        File dir = new File(petImgDir);

        /* 디렉토리가 없을 경우 생성 */
        if(!dir.exists()){
            dir.mkdirs();
        }

        // 업로드 파일에 대한 정보를 담을 리스트
        //List</* 첨부파일DTO*/> attachmentList = new ArrayList<>();

        /*try{
            for (int i = 0; i < petProfileImg.size(); i++) {

                // 첨부파일이 실제로 존재하는 경우에만 로직 수행
                if(petProfileImg.get(i).getSize() > 0){

                    String originalFileName = petProfileImg.get(i).getOriginalFilename();
                    log.info("originalFileName : {}", originalFileName);

                    String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID() + ext;
                    log.info("savedFileName : {}", savedFileName);

                    // 서버의 설정 디렉토리 파일 저장하기
                    petProfileImg.get(i).transferTo(new File(petImgDir + "/" + savedFileName));

                    // DB에 저장할 파일의 정보 처리
                    // 첨부파일DTO fileInfo = new 첨부파일DTO();
                    //fileInfo.setFileSaveName(savedFileName);
                    //fileInfo.setFilePathName("/upload/member/");
                }
                // attachmentList.add(fileInfo);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        // log.info("attachmentList : {}", attachmentList);

        // pet.setAttachmentList(attachmentList);
        pet.setMember(loginMember);

        memberService.registPetProfile(pet);

        //rttr.addFlashAttribute()

        return "redirect:/member/pet-profile-list";
    }

    /* 반려동물 프로필 리스트 조회 페이지 */
    @GetMapping("/pet-profile-list")
    public void petProfileListPage(@AuthenticationPrincipal MemberDTO loginMember, Model model){

        model.addAttribute("loginMember", loginMember.getMemberId());

        List<PetDTO> petProfileList = memberService.selectPetProfileList(loginMember);

        model.addAttribute("petProfileList", petProfileList);
    }

    /* 반려동물 프로필 상세 페이지 */
    @GetMapping("/pet-profile-view")
    public String petProfileView(@AuthenticationPrincipal MemberDTO loginMember,
                                 @RequestParam int petCode,Model model){

        log.info("loginMember : {}", loginMember);

        PetDTO petProfile = memberService.viewPetProfile(loginMember, petCode);
        log.info("pet profile : {}", petProfile);

        model.addAttribute("petProfile", petProfile);


        return "member/pet-profile-view";
    }

    /* 반려동물 프로필 업데이트 */
    @GetMapping("/pet-profile-update")
    public void petProfileUpdatePage(@AuthenticationPrincipal MemberDTO loginMember, Model model){

        PetDTO petProfile = memberService.petProfileUpdate(loginMember);

        model.addAttribute("petProfile", petProfile);
    }

    /* 반려동물 프로필 삭제 */

    /* 지난 예약 내역 조회 페이지 */
//    @GetMapping("/reservationList")
//    public String reservationHistoryPage(@RequestParam(defaultValue = "1") int page,
//                                         @RequestParam(required = false) String searchCondition,
//                                         @RequestParam(required = false) String searchValue,
//                                         Model model){
//        log.info("reserveList page : {}", page);
//        log.info("reserveList searchCondition : {}", searchCondition);
//        log.info("reserveList searchValue : {}", searchValue);
//
//        Map<String, String> searchMap = new HashMap<>();
//        searchMap.put("searchCondition", searchCondition);
//        searchMap.put("searchValue", searchValue);
//
//        Map<String, Object> reserveListAndPaging = memberService.selectReserveList(searchMap, page);
//        model.addAttribute("paging", reserveListAndPaging.get("paging"));
//        model.addAttribute("reserveList", reserveListAndPaging.get("reserveList"));
//
//        return "/member/reservationList";
//    }

    /* 예약 내역 조회 페이지 만들기 */
    @GetMapping("/reservationList")
    public String getReserveList(@RequestParam(defaultValue = "1") int page) {

        log.info("reservationList page : {}", page);

       Map<String, Object> reservationListAndPaging = memberService.selectReservationList(page);

        return "/member/reservationList";
    }


    /* 후기 작성 페이지 */
    @GetMapping("/review_write")
    public void reviewWritePage(){}

    /* 후기 전체 조회 페이지 */
    @GetMapping("/review-list")
    public String reviewListPage(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(required = false) String searchCondition,
                                         @RequestParam(required = false) String searchValue,
                                         Model model) {
        log.info("reviewList page : {}", page);
        log.info("reviewList searchCondition : {}", searchCondition);
        log.info("reviewList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        Map<String, Object> reviewListAndPaging = memberService.selectReviewList(searchMap, page);
        model.addAttribute("paging", reviewListAndPaging.get("paging"));
        model.addAttribute("reviewList", reviewListAndPaging.get("reserveList"));

        return "member/review-list";
    }

    /* 진행 중인 예약 페이지 */
    @GetMapping("/reservation-in-progress")
    public void progressReservationPage(){}



}


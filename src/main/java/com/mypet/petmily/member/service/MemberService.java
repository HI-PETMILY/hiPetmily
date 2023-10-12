package com.mypet.petmily.member.service;

import com.mypet.petmily.common.exception.member.*;
import com.mypet.petmily.common.paging.Pagenation;
import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dao.MemberMapper;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import com.mypet.petmily.member.dto.PetDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /* 회원 가입 */
    @Transactional
    public void registMember(MemberDTO member) throws MemberRegistException {

        member.setMemberStat("활동");
        member.setMemberStatDate(new Date());
        member.setRegistDate(new Date());
        member.setSignupPathCode(1);

        int result1 = memberMapper.insertMember(member);
        int result2 = memberMapper.insertMemberRole();

        if (!(result1 > 0 && result2 > 0)) throw new MemberRegistException("회원 가입에 실패하였습니다.");
    }

    /* 회원 정보 수정 */
    @Transactional
    public void modifyMember(MemberDTO modifyMember) throws MemberModifyException {

        int result = memberMapper.updateMember(modifyMember);

        if (!(result > 0)) throw new MemberModifyException("회원 정보 수정에 실패하였습니다.");
    }


    /* 비밀 번호 변경 */
    @Transactional
    public void modifyPassword(MemberDTO modifyPassword) throws MemberPasswordUpdateException {

        int result = memberMapper.updatePassword(modifyPassword);

        if (!(result > 0)) throw new MemberPasswordUpdateException("비밀번호 변경에 실패하였습니다.");
    }

    /* 회원 탈퇴 */
    @Transactional
    public void removeMember(MemberDTO member) throws MemberRemoveException {
        int result = memberMapper.deleteMember(member);

        if (!(result > 0)) {
            throw new MemberRemoveException("회원 탈퇴에 실패하였습니다.");
        }
    }

    /* 닉네임 중복 확인 */
    public boolean selectMemberByNickName(String nickName){

        String result = memberMapper.selectMemberByNickName(nickName);

        return result != null;
    }

    /* 이메일 중복 확인 */
    public boolean selectMemberByMemberId(String memberId) {

        String result = memberMapper.selectMemberByMemberId(memberId);

        return result != null;
    }

    /* 아이디 찾기 */
    public String findId(String memberName, String phone) {
        return memberMapper.findId(memberName, phone);
    }

    /* 비밀번호 찾기 */
    public int pwdCheck(MemberDTO dto) {
        return memberMapper.pwdCheck(dto);
    }

    /* 비밀번호 업데이트 */
    public void pwdUpdate(MemberDTO dto) {
        String newUpdatePwd = passwordEncoder.encode(dto.getPassword());
        dto.setMemberPwd(newUpdatePwd);
        memberMapper.pwdUpdate(dto);
    }



    /* 마이페이지 반려동물 프로필 조회 */
    public PetDTO viewFirstPetProfile(MemberDTO member) {
        return memberMapper.viewFirstPetProfile(member);
    }

    /* 반려동물 프로필 등록 */
    public void registPetProfile(PetDTO pet) throws PetProfileException {

        pet.setRegistStatus("Y");
        memberMapper.insertPetProfile(pet);      // 반려동물 테이블에 데이터 저장


        //memberMapper.insertAttachment(review.getAttachment());
    }

    /* 반려동물 프로필 리스트 조회 */
    public List<PetDTO> selectPetProfileList(MemberDTO loginMember) {
        return memberMapper.selectPetProfileList(loginMember);
    }

    /* 특정 반려동물 프로필 조회 */
    public PetDTO viewPetProfile(MemberDTO loginMember, int petCode) {

        return memberMapper.viewPetProfile(loginMember, petCode);
    }

    /* 반려동물 프로필 업데이트 */
    public PetDTO petProfileUpdate(PetDTO pet) {
        return memberMapper.petProfileUpdate(pet);
    }


    /* 예약 내역 조회 */
//    public Map<String, Object> selectReserveList(Map<String, String> searchMap, int page) {
//        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해
//        * 검색 조건을 고려한 예약 내역의 전체 수를 가져온다.
//        * 검색 조건이 있는 경우 해당 조건에 맞는 예약 내역 수가 반환된다.*/
//        int totalCount = memberMapper.selectTotalCount(searchMap);
//        log.info("reserveList totalCount : {}", totalCount);
//
//        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
//        int limit = 10;         // 한 페이지에 보여줄 게시물(컨텐츠)의 수
//        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
//        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
//        /* 페이지 번호(page), 총 예약 내역 수, 시작 페이지 번호, 끝 페이지 번호, 시작 항목 인덱스, 끝 항목 인덱스, 검색 조건 등이 저장 */
//        log.info("reserveList selectCriteria : {}", selectCriteria);
//
//        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다.  */
//         List<ReservationDTO> reserveList = memberMapper.selectReserveList(selectCriteria);
//         /* 조회된 예약 내역은 List<ReservationList> 형태로 반환된다. */
//        log.info("board : {}", reserveList);
//
//        /* reserveListAndPaging 맵을 생성하여 페이징 정보와 조회된 예약 내역을 맵에 저장한다. */
//        Map<String, Object> reserveListAndPaging = new HashMap<>();
//        reserveListAndPaging.put("paging", selectCriteria);
//        reserveListAndPaging.put("reserveList", reserveList);
//
//        return reserveListAndPaging;
//    }
//

    /* 예약 조회 */



    /* 작성한 리뷰 전체 조회 */
    public Map<String, Object> selectReviewList(Map<String, String> searchMap, int page) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = memberMapper.selectTotalCount(searchMap);
        log.info("boardList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물(컨텐츠)의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("boardList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<ReviewDTO> reviewList = memberMapper.selectReviewList(selectCriteria);
        log.info("board : {}", reviewList);

        Map<String, Object> reviewListAndPaging = new HashMap<>();
        reviewListAndPaging.put("paging", selectCriteria);
        reviewListAndPaging.put("reviewList", reviewList);

        return reviewListAndPaging;
    }


    public Map<String, Object> selectReservationList(int page) {

        /*  */

        return null;
    }


}
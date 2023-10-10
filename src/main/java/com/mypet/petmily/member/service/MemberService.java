package com.mypet.petmily.member.service;

import com.mypet.petmily.common.exception.member.*;
import com.mypet.petmily.common.paging.Pagenation;
import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dao.MemberMapper;
import com.mypet.petmily.member.dto.MemberDTO;
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

    @Transactional
    public void removeMember(MemberDTO member) throws MemberRemoveException {
        int result = memberMapper.deleteMember(member);

        if (!(result > 0)) {
            throw new MemberRemoveException("회원 탈퇴에 실패하였습니다.");
        }
    }


    public boolean selectMemberByNickName(String nickName){

        String result = memberMapper.selectMemberByNickName(nickName);

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

    /* 예약 내역 조회 */
    public Map<String, Object> selectReserveList(Map<String, String> searchMap, int page) {
        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = memberMapper.selectTotalCount(searchMap);
        log.info("reserveList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물(컨텐츠)의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("reserveList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        // List<ReservationDTO> reserveList = memberMapper.selectReserveList(selectCriteria);
        // 예약DTO 만들어지는 거로 변경해야함!!!
        //log.info("board : {}", reserveList);

        Map<String, Object> reserveListAndPaging = new HashMap<>();
        reserveListAndPaging.put("paging", selectCriteria);
        //reserveListAndPaging.put("reserveList", reserveList);

        return reserveListAndPaging;
    }

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

    /* 반려동물 프로필 등록 */
    public void registPetProfile(PetDTO pet) throws PetProfileException {

        memberMapper.insertPetProfile(pet);      // 반려동물 테이블에 데이터 저장

        //memberMapper.insertAttachment(review.getAttachment());
    }

    public PetDTO viewPetProfile(MemberDTO loginMember) {

        return memberMapper.viewPetProfile(loginMember);
    }

    /* 반려동물 프로필 조회 */

}
package com.mypet.petmily.admin.member.service;

import com.mypet.petmily.admin.member.dao.AdminMapper;
import com.mypet.petmily.admin.paging.PagenationAdmin;
import com.mypet.petmily.admin.paging.SelectCriteria;
import com.mypet.petmily.admin.paging.SelectPetSitterCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
public class AdminService {

    private final AdminMapper adminMapper;

    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public Map<String, Object> getMemberList(int page, String searchCondition, String searchValue, String rating) {

        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminMapper.selectTotalCount(searchCondition, searchValue, rating);
        log.info("memberList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 20;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = PagenationAdmin.getSelectCriteria(page, totalCount, limit, buttonAmount, searchCondition, searchValue, rating);
        log.info("memberList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<MemberDTO> memberList = adminMapper.selectMemberList(selectCriteria);
        log.info("memberList : {}", memberList);

        Map<String, Object> memberListAndPaging = new HashMap<>();
        memberListAndPaging.put("paging", selectCriteria);
        memberListAndPaging.put("memberList", memberList);
        memberListAndPaging.put("rating", rating); // rating 파라미터를 전달

        return memberListAndPaging;
    }

    // 회원 목록 조회 메서드
    @Transactional
    public int getTotalMemberCount(String searchCondition, String searchValue, String rating) {
        return adminMapper.selectTotalCount(searchCondition, searchValue, rating);
    }

    // 해당 회원 상세 조회
    @Transactional
    public List<MemberDTO> getPoP_managementPageById(int id) { //////////////////////////////////

        // 클라이언트로부터 받은 memberId를 사용하여 회원 정보를 데이터베이스에서 조회
        List<MemberDTO> member = adminMapper.selectPoP_MemberById(id);

        return member;
    }

    @Transactional
    public void setPoP_managementResultPage(int no, int memberType, String stat, int point) { //수정

        adminMapper.setPoP_Member(no, stat, point);
        adminMapper.setPoP_Authority(no, memberType);

        log.info("memberType 등급 누른대로 제발!!!!! : {}", memberType);
    }

    @Transactional
    public void deleteMemebr() {  //회원 삭제

        adminMapper.deleteMember();
    }


    //펫시터 신청 회원 조회 ===============================================
    public Map<String, Object> getPetSitterList(int page, String searchCondition, String searchValue, String apply) {

        /* 1. 전체 신청글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminMapper.selectPetSitterTotalCount(searchCondition, searchValue, apply);
        log.info("petSitterList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 20;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectPetSitterCriteria petSelectCriteria = PagenationAdmin.getPetSitterSelectCriteria(page, totalCount, limit, buttonAmount, searchCondition, searchValue, apply);
        log.info("petSitterList selectCriteria : {}", petSelectCriteria); //ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ

        /* 3. 요청 페이지와 검색 기준에 맞는 신청글을 조회해온다. */
        List<PetSitterDTO> petSitterList = adminMapper.selectPetSitterList(petSelectCriteria);
        log.info("petSitterList : {}", petSitterList); //ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ

        Map<String, Object> petSitterListAndPaging = new HashMap<>();
        petSitterListAndPaging.put("paging", petSelectCriteria);
        petSitterListAndPaging.put("petSitterList", petSitterList);

        return petSitterListAndPaging;
    }

    @Transactional
    public int getTotalPetSitterCount(String searchCondition, String searchValue, String apply) {
        return adminMapper.selectPetSitterTotalCount(searchCondition, searchValue, apply);
    }

    // 해당 회원의 펫시터 신청 폼 상세 조회!!!
    @Transactional
    public List<PetSitterDTO> getPoP_petSitterPageById(int id) {

        // 클라이언트로부터 받은 memberId를 사용하여 회원 정보를 데이터베이스에서 조회
        List<PetSitterDTO> petSitter = adminMapper.selectPoP_PetSitterById(id);

        return petSitter;

    }
    public void banMember(int id) { //수동 삭제

        adminMapper.banMember(id);
    }
}

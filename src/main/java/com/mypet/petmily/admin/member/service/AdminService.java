package com.mypet.petmily.admin.member.service;

import com.mypet.petmily.admin.member.dao.AdminMapper;
import com.mypet.petmily.admin.paging.Pagenation;
import com.mypet.petmily.admin.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
public class AdminService {

    private final AdminMapper adminMapper;

    public AdminService(AdminMapper adminMapper) {this.adminMapper = adminMapper;}

//    public List<MemberDTO> selectMemberList() {
//
//        log.info("memberList : {} " , membersMapper.selectMemberList());
//
////        System.out.println(membersMapper.selectMemberList());
//
//        return membersMapper.selectMemberList();
//
//    }

    public Map<String, Object> getMemberList(int page, String searchCondition, String searchValue, String rating) {
    // 밑에 기존코드
    // public Map<String, Object> getMemberList(int page, String searchCondition, String searchValue) {


        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminMapper.selectTotalCount(searchCondition, searchValue, rating);
        log.info("memberList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 20;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchCondition, searchValue, rating);
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
    public int getTotalMemberCount(String searchCondition, String searchValue, String rating) {
        return adminMapper.selectTotalCount(searchCondition, searchValue, rating);
    }




}

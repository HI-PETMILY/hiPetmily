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

    public Map<String, Object> getMemberList(int page, String searchCondition, String searchValue, List<String> rating) {
    // 밑에 기존코드
    // public Map<String, Object> getMemberList(int page, String searchCondition, String searchValue) {

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //      - 등급 체크박스 값을 mapper에 넘기기위한 테스트 -
        // list 형태로 들어와서 collection 클래스?의 메소드를 쓸 수있음
        // contains 라고 리스트에 값이 있는지 판단해주는건데,
        // 초기값을 0(어떤 등급도 없는 경우) 라고 하고 일반회원이 있으면 1을 더하고, 펫시터가 있으면 2를 더해
        // 그럼 겹치치 않는 0, 1, 2, 3 값이 생김 (소수의 원리 이용)
        // 아무 것도 없는 경우 : 0, 일반회원만 있는 경우 : 1, 펫시터 회원만 있는 경우 : 2, 일반회원도 있는 경우 : 3
        // contains 메소드 : Collection 객체(list 등)에서 지원하는 메소드로, 해당 리스트에 값을 포함하는지 확인한다.
        //                  형태 - 리스트변수명.contains("찾기원하는값") / 반환값 - boolean
        //                  예시 - 화면단에서 일반회원만 선택하여 검색한 경우
        //                        rating.contains("일반회원") = true
        //                        rating.contains("펫시터회원") = false
        int searchCheck = 0;
        if(rating != null) {
            if(rating.contains("일반회원")) {
                searchCheck += 1;
            }
            if(rating.contains("펫시터회원")) {
                searchCheck += 2;
            };
        };  /////////////////////////////////////////////////////////////////////////////////////////////

        log.info("admin service | 없음 0, 일반 1, 펫시터 2, 전체 3 : {}", searchCheck);   // 이걸로 회원등급 체크한 값 확인한거임
        // 이제 서비스까지 왔으니까, 쿼리의 where 절에 넣도록 메소드에 전달해줘야 겠지.... 제에에발 돼라돼라 ㅠㅠㅠ
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        /* 1. 전체 게시글 수 확인 (검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = adminMapper.selectTotalCount(searchCondition, searchValue);
        log.info("memberList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관 된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 20;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchCondition, searchValue);
        log.info("memberList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<MemberDTO> memberList = adminMapper.selectMemberList(selectCriteria);
        log.info("memberList : {}", memberList);

        Map<String, Object> memberListAndPaging = new HashMap<>();
        memberListAndPaging.put("paging", selectCriteria);
        memberListAndPaging.put("memberList", memberList);

        return memberListAndPaging;
    }
    // 회원 목록 조회 메서드
    public int getTotalMemberCount(String searchCondition, String searchValue) {
        return adminMapper.selectTotalCount(searchCondition, searchValue);
    }




}

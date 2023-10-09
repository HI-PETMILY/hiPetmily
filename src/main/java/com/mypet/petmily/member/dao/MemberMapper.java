package com.mypet.petmily.member.dao;

import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

//    String selectMemberById(String memberId);

    String findId(String memberName, String phone);

    int insertMember(MemberDTO member);

    int insertMemberRole();

    int updateMember(MemberDTO modifyMember);

    MemberDTO findMemberById(String memberId);

    int updatePassword(MemberDTO modifyPassword);

    int pwdCheck(MemberDTO dto);

    int deleteMember(MemberDTO member);

    String selectMemberByNickName(String nickName);

    void pwdUpdate(MemberDTO dto);

    int selectTotalCount(Map<String, String> searchMap);

    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria);
}

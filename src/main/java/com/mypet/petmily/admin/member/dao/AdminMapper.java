package com.mypet.petmily.admin.member.dao;

import com.mypet.petmily.admin.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
//    List<MemberDTO> selectMemberList(); ///////////
//    int selectTotalCount(Map<String, String> searchMap);????

    List<MemberDTO> selectMemberList(SelectCriteria selectCriteria);


    int selectTotalCount(String searchCondition, String searchValue, String rating);

    List<MemberDTO> selectPoP_MemberById(int id);


    void setPoP_Member(int no, String stat, int point);

    void setPoP_Authority(int no, int memberType);

    void deleteMember();

}

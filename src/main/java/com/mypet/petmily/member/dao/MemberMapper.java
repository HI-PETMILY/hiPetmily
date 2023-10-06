package com.mypet.petmily.member.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    String selectMemberByNickName(String nickName);

    int insertMember(MemberDTO member);

    int insertMemberRole();

    int updateMember(MemberDTO modifyMember);

    MemberDTO findMemberById(String memberId);



//    String selectMemberById(String memberId);

}

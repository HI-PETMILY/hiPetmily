package com.mypet.petmily.member.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDTO findMemberById(String memberId);

//    String selectMemberById(String memberId);

    String findId(String memberName, String phone);

    String selectMemberByNickName(String nickName);

    int insertMember(MemberDTO member);

    int insertMemberRole();

    int updateMember(MemberDTO modifyMember);

    int pwdCheck(MemberDTO dto);

    void pwdUpdate(MemberDTO dto);
}

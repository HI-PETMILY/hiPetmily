package com.mypet.petmily.member.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

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
}

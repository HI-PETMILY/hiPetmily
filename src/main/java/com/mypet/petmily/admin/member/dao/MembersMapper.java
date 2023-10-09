package com.mypet.petmily.admin.member.dao;

import com.mypet.petmily.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MembersMapper {

    List<MemberDTO> selectMemberList();
}

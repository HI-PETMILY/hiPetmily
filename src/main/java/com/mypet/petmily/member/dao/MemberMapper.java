package com.mypet.petmily.member.dao;

import com.mypet.petmily.common.paging.SelectCriteria;
import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.ReservationDTO;
import com.mypet.petmily.member.dto.PetDTO;
import com.mypet.petmily.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    MemberDTO findMemberById(String memberId);

//    String selectMemberById(String memberId);

    String findId(String memberName, String phone);

    int insertMember(MemberDTO member);

    int insertMemberRole();

    int updateMember(MemberDTO modifyMember);

    int updatePassword(MemberDTO modifyPassword);

    int deleteMember(MemberDTO member);

    String selectMemberByNickName(String nickName);

    int pwdCheck(MemberDTO dto);

    void pwdUpdate(MemberDTO dto);

    void insertPetProfile(PetDTO pet);

    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria);

    /* 예약 조회 */
    List<ReservationDTO> selectReserveList(SelectCriteria selectCriteria);

    int selectTotalCount(Map<String, String> searchMap);
    // xml 작성해야 함

    List<PetDTO> selectPetProfileList(MemberDTO loginMember);

    PetDTO viewPetProfile(MemberDTO loginMember, int petCode);
    // 파라미터를 두개 이상 넘길 때는 xml설정으로 넘어갈 때 변수명으로 찾아야 함

    PetDTO viewFirstPetProfile(MemberDTO member);

    PetDTO petProfileUpdate(PetDTO pet);
}

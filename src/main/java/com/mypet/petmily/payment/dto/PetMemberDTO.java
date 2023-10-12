package com.mypet.petmily.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PetMemberDTO {
    // 기존 member DTO에서 필요한것만 이용하려고 따로 만든 DTO

    private int memberNo;          // 회원코드
    private String memberName;      // 이름
    private String nickName;        // 닉네임
    private String phone;           // 연락처
    private Integer postNo;         // 우편번호
    private String address;         // 주소
    private String address2;        // 상세주소
    private int point;              // 적립금
    private String memberStat;      // 상태

}

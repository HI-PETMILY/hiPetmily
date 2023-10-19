package com.mypet.petmily.payment.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SitterInfoDTO {
    // 시터의 멤버DTO

    private MemberDTO memberInfo;
    private int sitterCode;


}

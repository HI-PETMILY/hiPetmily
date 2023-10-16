package com.mypet.petmily.review.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Member;

@Getter @Setter @ToString
public class ReviewSitterInfoDTO {
    // 시터의 멤버DTO

    private MemberDTO memberInfo;
    private int sitterCode;
    private String sitterName;
    private String nickName;


}

package com.mypet.petmily.review.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReviewSitterInfoDTO {
    // 시터의 멤버DTO

    private int sitterCode;
    private String sitterName;
    private String nickName;


}
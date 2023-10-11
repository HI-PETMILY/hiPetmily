package com.mypet.petmily.petSitter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReviewDTO {


    private int revCode;

    private int revScore;

    private String revContent;

    private  String revCreatedDt;

    private String revModifyDt;

    private String revDeleteDt;

    private int memberNo;

    private int petMemberNO;

    private int reservCode;



}

package com.mypet.petmily.review.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReservationInfoDTO {

    private int resCode;
    private String resStartDate;
    private String resEndDate;
    private int resTotalAmount;
    private String resAppDate;
}

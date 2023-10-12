package com.mypet.petmily.petSitter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SitterScheduleDTO {

    private int petMemberResNo;
    private String petMemberResDay;
    private String petMemberStatus;
    private int petMemberNo;

}

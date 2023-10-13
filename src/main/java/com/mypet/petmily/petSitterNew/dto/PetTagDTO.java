package com.mypet.petmily.petSitterNew.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PetTagDTO {

    private int tagNo;
    private String tagContent;
    private int petMemberNo;

}
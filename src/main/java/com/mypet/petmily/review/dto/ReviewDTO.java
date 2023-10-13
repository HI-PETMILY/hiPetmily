package com.mypet.petmily.review.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO;
import com.mypet.petmily.petSitterNew.dto.ReservationDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReviewDTO {

    private Long revCode;                   // 후기 코드
    private int revScore;                   // 별점(1~5)
    private String revContent;              // 후기 내용
    private Date revCreatedDate;             // 작성일
    private Date revModifyDate;             // 수정일
    private String revStatus;               // 상태(Y=등록, N=삭제)
    private Date revDeleteDate;             // 삭제일
    private MemberDTO member;              // 회원
    private PetSitterDTO petSitter;        // 펫시터
    private ReservationDTO reservation;     // 예약
    //private List<FileDTO> attachmentList; // 첨부파일(사진)
}

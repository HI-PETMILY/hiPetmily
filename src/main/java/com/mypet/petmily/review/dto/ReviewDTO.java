package com.mypet.petmily.review.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.petSitter.dto.PetSitterDTO2;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReviewDTO {

    private Long revCode;                   // 후기 코드
    private int revScore;                   // 별점(1~5)
    private String revContent;              // 후기 내용
    private Date revCreateDate;             // 작성일
    private Date revModifyDate;             // 수정일
    private String revStatus;               // 상태(Y=등록, N=삭제)
    private Date revDeleteDate;             // 삭제일
    private MemberDTO memberNo;             // 회원 코드
    private PetSitterDTO2 petMemberNo;       // 펫시터 코드
    // private ReservationDTO reserveCode;  // 예약 코드
    //private List<FileDTO> attachmentList; // 첨부파일(사진)
}

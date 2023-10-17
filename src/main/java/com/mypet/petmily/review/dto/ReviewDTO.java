package com.mypet.petmily.review.dto;

import com.mypet.petmily.member.dto.MemberDTO;
import com.mypet.petmily.payment.dto.ReservationHistoryDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReviewDTO {

    private Long revCode;                          // 후기 코드
    private int revScore;                          // 별점(1~5)
    private String revContent;                     // 후기 내용
    private Date revCreatedDate;                   // 작성일
    private Date revModifyDate;                    // 수정일
    private String revStatus;                      // 상태(Y=등록, N=삭제)
    private Date revDeleteDate;                    // 삭제일
    private MemberDTO member;                      // 회원
    private String petMemberNo;             // 펫시터
    private String resCode;     // 예약
//    private ReservationInfoDTO resCode;
    //private List<FileDTO> attachmentList; // 첨부파일(사진)
}

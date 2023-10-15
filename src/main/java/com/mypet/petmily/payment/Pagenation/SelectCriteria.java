package com.mypet.petmily.payment.Pagenation;

import com.mypet.petmily.member.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor // 매개변수 생성자
public class SelectCriteria { // 조회 조건이라는 뜻

    private int page;
    private int totalCount;
    private int limit;
    private int buttonAmount;
    private int maxPage;
    private int startPage;
    private int endPage;
    private int startRow;
    private int endRow;
    private MemberDTO memberNo;

    public SelectCriteria() {
    }



    public SelectCriteria(int page, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int startRow, int endRow) {
        this.page = page;
        this.totalCount = totalCount;
        this.limit = limit;
        this.buttonAmount = buttonAmount;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.startRow = startRow;
        this.endRow = endRow;
    }
}

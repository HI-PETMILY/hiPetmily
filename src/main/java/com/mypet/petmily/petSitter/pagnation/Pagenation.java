package com.mypet.petmily.petSitter.pagnation;

import com.mypet.petmily.petSitter.dto.SelectCriteria;

import java.util.Map;

public class Pagenation {
    public static SelectCriteria getSelectCriteria(int page, int totalCount, int limit, int buttonAmount, Map<String, String> searchMap) {

   /* 총 페이지수 계산*/

        int maxPage = (int)Math.ceil((double) totalCount / limit);//ceil-> 올림처리


      //  페이징바 시작 숫자

        int startPage = (int) (Math.ceil((double) page/ buttonAmount) -1) * buttonAmount + 1;

        /*페이징바 끝 숫자 */
        int endPage = startPage + buttonAmount -1;

        /*max페이지가 end페이지보다 더 작은 경우  end 페이지는 max 페이지다.*/

        if(maxPage < endPage) endPage = maxPage;

        /*마지막 페이지는 0이 될수 없으므로 게시물이 아무것도 존재하지 않으면 max, end는 0이 된다. */
        if(maxPage == 0 && endPage ==0){
            maxPage = startPage;
            endPage = startPage;
        }

        /*조회 시작 행과 마지막 행 계산*/

        int startRow = (page - 1) * limit + 1;
        int endRow = startRow + limit -1;

        return new SelectCriteria(page,totalCount,limit,buttonAmount,maxPage,startPage,endPage,startRow,endRow,searchMap.get("searchCondition"),searchMap.get("searchValue"));
    }
}

package com.mypet.petmily.petSitter.Pagenation;

import com.mypet.petmily.payment.Pagenation.SelectCriteria;

public class Pagenation {
    public static SelectCriteria getSelectCriteria(int page, int totalCount, int limit, int buttonAmount) {

   /* 총 페이지수 계산*/

        int maxPage = (int)Math.ceil((double) totalCount / limit);//ceil-> 올림처리


        /*페이징바 시작 숫자*/

       // int startPage = (int) (Math.ceil((double) page/ buttonAmount -1) * buttonAmount + 1;


return null;
    }
}

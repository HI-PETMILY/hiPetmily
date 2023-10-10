package com.mypet.petmily.petSitter.dto.Pagenation;

import com.mypet.petmily.petSitter.dto.SelectCriteria.SelectCriteria;

import java.util.Map;

public class Pagenation {
    public static SelectCriteria getSelectCriteria(int page, int totalCount, int limit, int buttonAmount, Map<String, String> searchMap) {

   /* 총 페이지수 계산*/

        int maxPage = (int)Math.ceil((double) totalCount / limit);//ceil-> 올림처리


        /*페이징바 시작 숫자*/

        int startPage = (int) (Math.ceil((double) page/ buttonAmount -1) * buttonAmount + 1;



    }
}

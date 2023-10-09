package com.mypet.petmily.board;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreatePagingTag {

    public static String getPagingElement(long total, int page, int row, String pageUrl){

        String pageData = "";

        int pages = (total == 0) ? 1 : (int) ((total - 1) / row) + 1;
        int blocks;
        int block;
        int firstPage;
        int lastPage;

        blocks = (int) Math.ceil(1.0 * pages / 10.0);
        block = (int) Math.ceil(1.0 * page / 10.0);
        firstPage = (block - 1) * 10 + 1;
        lastPage = block * 10;
        if (lastPage > pages)
            lastPage = pages;

        pageData = "<a href="+pageUrl+"&page=1 class=\"bt first\"><<</a>";

        if(block > 1){
            pageData += "<a href="+pageUrl+"&page="+(firstPage-1)+" class=\"bt prev\"><</a>";
        }

        for(int i=firstPage; i <= lastPage; i++){
            if(page == i) {
                pageData += "<a href="+pageUrl+"&page="+page+" class=\"num on\">"+page+"</a>";
            }else{
                pageData += "<a href="+pageUrl+"&page="+i+" class=\"num\">"+i+"</a>";
            }
        }

        if(block < blocks){
            pageData += "<a href="+pageUrl+"&page="+(lastPage+1)+" class=\"bt next\">></a>";
        }

        pageData += "<a href="+pageUrl+"&page="+pages+" class=\"bt last\">>></a>";

        return pageData;

    }

    /* 문자열 null체크 메소드*/
    public static String chkStringNull (String str) {
        String result = "";
        if(str == "null" || str == null) {
            result = "";
        }else {
            result = str;
        }
        return result;
    }
}
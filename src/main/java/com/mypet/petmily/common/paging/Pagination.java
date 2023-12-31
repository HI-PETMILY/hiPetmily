package com.mypet.petmily.common.paging;

public class Pagination {
    public static SelectCriteria getSelectCriteria(int page, int limit, int buttonAmount) {

        /* 총 페이지 수 계산 */
        int maxPage = (int) Math.ceil((double) limit);

        /* 페이징바 시작 숫자 */
        int startPage = (int) (Math.ceil((double) page / buttonAmount) - 1) * buttonAmount + 1;

        /* 페이징바 끝 숫자 */
        int endPage = startPage + buttonAmount - 1;

        /* max 페이지가 end 페이지보다 더 작은 경우 end 페이지는 max 페이지다. */
        if(maxPage < endPage) endPage = maxPage;

        /* 마지막 페이는 0이 될 수 없으므로 게시물이 아무것도 존재하지 않으면 max, end는 0이 된다. */
        if(maxPage == 0 && endPage == 0){
            maxPage = startPage;
            endPage = startPage;
        }   // 컨텐츠가 없는 경우

        /* 조회 시작 행과 마지막 행 계산 */
        int startRow = (page - 1) * limit + 1;
        int endRow = startRow + limit - 1;

        return new SelectCriteria(page, limit, buttonAmount, maxPage, startPage,
                                  endPage, startRow, endRow);
    }

    public static SelectCriteria getSelectCriteria(int page, int totalCount, int limit, int buttonAmount) {
        return getSelectCriteria(page, limit, buttonAmount);
    }
}

package com.mypet.petmily.petSitter.dto.SelectCriteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor//매개변수 생성자

public class SelectCriteria {


    //현재페이지
private int page;
//검색포함한 컨텐츠의 갯수
private int totalCount;
//한페이지에 보여주는 제한갯수
private  int limit;
//
private  int buttonAmount;
//최대페이징 수
private  int maxPage;
//페이징 시작페이지
private int startPage;
//페이징 끝 페이지
private int endPage;

private int startRow;

private  int endRow;

private String searchCondition;

private String searchValue;


}

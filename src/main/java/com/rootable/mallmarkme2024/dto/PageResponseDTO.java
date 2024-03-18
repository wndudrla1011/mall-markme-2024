package com.rootable.mallmarkme2024.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList; //데이터 리스트

    private List<Integer> pageNumberList; //페이징 처리용 (시작 ~ 끝 페이지)

    private PageRequestDTO pageRequestDTO; //검색 조건, 현재 페이지 등등

    private boolean prev, next; //이전, 다음 이동 화살표

    //총 데이터 개수, 이전 페이지, 다음 페이지, 전체 페이지, 현재 페이지
    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "innerBuilder")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, int totalCount) {

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = totalCount;

        /*
        * 1(startNum) 2 3 4 5 ... 10(endNum)
        * 시작 페이지 번호: startNum
        * 끝 페이지 번호: endNum
        * */
        int endNum = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;
        int startNum = endNum - 9;

        /*
        * 전체 페이지 중 마지막 페이지 번호
        * Size 보다 페이지 수가 적은 경우 처리하기 위함
        * */
        int lastNum = (int) (Math.ceil(totalCount / (double) pageRequestDTO.getSize()));

        endNum = Math.min(endNum, lastNum);

        this.prev = startNum > 1;
        this.next = totalCount > endNum * pageRequestDTO.getSize();

        this.pageNumberList = IntStream.rangeClosed(startNum, endNum).boxed().collect(Collectors.toList());

        this.prevPage = prev ? startNum - 1 : 0;
        this.nextPage = next ? endNum + 1 : 0;

        this.totalPage = this.pageNumberList.size();

        this.current = pageRequestDTO.getPage();

    }

}

package com.rootable.mallmarkme2024.repository.search;

import com.querydsl.jpa.JPQLQuery;
import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.domain.QItem;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

@Log4j2
public class ItemSearchImpl extends QuerydslRepositorySupport implements ItemSearch {

    public ItemSearchImpl() {
        super(Item.class);
    }

    @Override
    public Page<Item> searchList(PageRequestDTO pageRequestDTO) {

        log.info("----searchTest----");

        QItem item = QItem.item;

        JPQLQuery<Item> query = from(item);

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                             pageRequestDTO.getSize(),
                             Sort.by("id").descending());

        Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);

        List<Item> list = query.fetch(); //목록 데이터

        long total = query.fetchCount(); //행 개수

        return new PageImpl<>(list, pageable, total);

    }

}

package com.rootable.mallmarkme2024.repository.search;

import com.querydsl.jpa.JPQLQuery;
import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.domain.QItem;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class ItemSearchImpl extends QuerydslRepositorySupport implements ItemSearch {

    public ItemSearchImpl() {
        super(Item.class);
    }

    @Override
    public Page<Item> searchTest() {
        log.info("----searchTest----");

        QItem item = QItem.item;

        JPQLQuery<Item> query = from(item);

        query.where(item.name.contains("1"));

        query.fetch();

        return null;
    }

}

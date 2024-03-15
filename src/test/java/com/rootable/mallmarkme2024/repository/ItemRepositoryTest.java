package com.rootable.mallmarkme2024.repository;

import com.rootable.mallmarkme2024.domain.Item;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("샘플 데이터 생성용")
    public void sample() throws Exception {

        for (int i = 1; i <= 20; i++) {
            Item item = Item.builder()
                    .name("Item " + i)
                    .price(i * 1000)
                    .stock(i)
                    .isDeleted(false)
                    .build();

            Item savedItem = itemRepository.save(item);

            log.info(savedItem);
        }

    }

    @Test
    public void paging() throws Exception {

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Item> result = itemRepository.findAll(pageable);

        log.info(result.getTotalElements());

        log.info(result.getContent());

    }

}

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
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("샘플 데이터 생성용")
    public void sample() throws Exception {

        for (int i = 1; i <= 5; i++) {
            Item item = Item.builder()
                    .name("Item " + (i + 20))
                    .price(i * 1000)
                    .description("Description")
                    .stock(i)
                    .isDeleted(false)
                    .build();

            item.addImageString(UUID.randomUUID() + "_" + "IMAGE1.jpg");
            item.addImageString(UUID.randomUUID() + "_" + "IMAGE2.jpg");

            itemRepository.save(item);

        }

    }

    @Test
    public void paging() throws Exception {

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Item> result = itemRepository.findAll(pageable);

        log.info(result.getTotalElements());

        log.info(result.getContent());

    }

    @Test
    public void selectOne() throws Exception {

        Long id = 1L;

        Optional<Item> result = itemRepository.selectOne(id);

        Item item = result.orElseThrow();

        log.info(item.isDeleted());

        log.info(item.getImageList());

    }

    @Commit
    @Transactional
    @Test
    public void delete() throws Exception {

        Long id = 1L;

        itemRepository.delete(id, true);

    }

}

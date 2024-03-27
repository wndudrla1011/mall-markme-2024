package com.rootable.mallmarkme2024.service;

import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.dto.ItemDTO;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import com.rootable.mallmarkme2024.dto.PageResponseDTO;
import com.rootable.mallmarkme2024.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
public class ItemServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Test
    public void getList() throws Exception {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ItemDTO> responseDTO = itemService.getList(pageRequestDTO);

        log.info(responseDTO.getDtoList());

    }

    @Test
    public void get() throws Exception {

        Long id = 1L;

        ItemDTO itemDTO = itemService.get(id);

        log.info(itemDTO.getName());
        log.info(itemDTO.getWriter());
        log.info(itemDTO.getPrice());
        log.info(itemDTO.getDescription());
        log.info(itemDTO.getStock());

    }

    @Test
    public void register() throws Exception {

        ItemDTO itemDTO = ItemDTO.builder()
                .name("Test Item")
                .writer("Test Writer")
                .price(10000)
                .description("Testing")
                .stock(3)
                .build();

        Long savedId = itemService.register(itemDTO);

        Item item = itemRepository.findById(savedId).orElseThrow();
        assertThat(item).isNotNull();

    }

    @Test
    @DisplayName("DB 비우는 용도")
    public void clear() throws Exception {
        itemService.clear();
    }

}

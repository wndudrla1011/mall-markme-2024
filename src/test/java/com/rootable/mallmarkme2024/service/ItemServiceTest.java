package com.rootable.mallmarkme2024.service;

import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void getList() throws Exception {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        log.info(itemService.getList(pageRequestDTO));

    }

}

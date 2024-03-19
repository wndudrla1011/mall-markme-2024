package com.rootable.mallmarkme2024.controller;

import com.rootable.mallmarkme2024.dto.ItemDTO;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import com.rootable.mallmarkme2024.dto.PageResponseDTO;
import com.rootable.mallmarkme2024.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public ItemDTO get(@PathVariable("itemId") Long itemId) {

        log.info("상품 정보");

        return itemService.get(itemId);

    }

    @GetMapping("/list")
    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO) {

        log.info("상품 목록");
        log.info("list : " + pageRequestDTO);

        return itemService.getList(pageRequestDTO);

    }

}

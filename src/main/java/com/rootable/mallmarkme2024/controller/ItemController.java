package com.rootable.mallmarkme2024.controller;

import com.rootable.mallmarkme2024.dto.ItemDTO;
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
        return itemService.get(itemId);
    }

}

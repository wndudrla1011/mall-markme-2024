package com.rootable.mallmarkme2024.service;

import com.rootable.mallmarkme2024.dto.ItemDTO;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import com.rootable.mallmarkme2024.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ItemService {

    PageResponseDTO<ItemDTO> getList(PageRequestDTO pageRequestDTO);

}

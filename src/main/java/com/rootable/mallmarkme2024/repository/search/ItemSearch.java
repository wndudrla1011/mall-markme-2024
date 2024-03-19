package com.rootable.mallmarkme2024.repository.search;

import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface ItemSearch {

    Page<Item> searchList(PageRequestDTO pageRequestDTO);

}

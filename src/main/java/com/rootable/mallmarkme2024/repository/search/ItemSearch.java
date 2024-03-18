package com.rootable.mallmarkme2024.repository.search;

import com.rootable.mallmarkme2024.domain.Item;
import org.springframework.data.domain.Page;

public interface ItemSearch {

    Page<Item> searchTest();

}

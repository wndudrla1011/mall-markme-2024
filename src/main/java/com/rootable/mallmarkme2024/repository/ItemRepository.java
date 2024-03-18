package com.rootable.mallmarkme2024.repository;

import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.repository.search.ItemSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemSearch {
}

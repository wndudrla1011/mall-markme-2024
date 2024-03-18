package com.rootable.mallmarkme2024.repository;

import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.repository.search.ItemSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemSearch {

    @Query("select i, img from Item i left join i.imageList img where image.ord = 0 and i.delFlag = false")
    Page<Object[]> selectList(Pageable pageable);

}

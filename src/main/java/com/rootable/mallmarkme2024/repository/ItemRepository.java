package com.rootable.mallmarkme2024.repository;

import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.repository.search.ItemSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemSearch {

    @EntityGraph(attributePaths = "imageList")
    @Query("select i from Item i where i.id = :id")
    Optional<Item> selectOne(@Param("id") Long id);

    @Query("select i, img from Item i left join i.imageList img where img.ord = 0 and i.isDeleted = false")
    Page<Object[]> selectList(Pageable pageable);

}

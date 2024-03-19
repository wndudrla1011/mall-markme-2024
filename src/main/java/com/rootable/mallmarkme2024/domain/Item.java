package com.rootable.mallmarkme2024.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer stock;

    private boolean isDeleted;

    @ElementCollection
    @ToString.Exclude
    private List<ItemImage> imageList = new ArrayList<>();

    @Builder
    public Item(Long id, String name, Integer price, String description, Integer stock, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.isDeleted = isDeleted;
    }

    public void update(String name, Integer price, String description, Integer stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    //객체로 이미지 추가
    public void addImage(ItemImage image) {
        image.setOrd(imageList.size());
        imageList.add(image);
    }

    //파일명으로 이미지 추가
    public void addImageString(String fileName) {

        ItemImage itemImage = ItemImage.builder()
                .fileName(fileName)
                .build();

        addImage(itemImage);

    }

}

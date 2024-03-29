package com.rootable.mallmarkme2024.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemImage {

    private String fileName;

    private int ord; //대표 이미지 설정용

    public void setOrd(int ord) {
        this.ord = ord;
    }

}

package com.rootable.mallmarkme2024.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemSaveDTO {

    private Long id;

    @NotBlank(message = "상품명을 입력해주세요")
    private String name;

    @NotBlank(message = "저자를 입력해주세요")
    private String writer;

    @NotNull(message = "가격을 입력해주세요")
    @Range(min = 1000, max = 100000)
    private Integer price;

    private String description;

    @NotNull(message = "재고를 입력해주세요")
    @PositiveOrZero
    @Max(999)
    private Integer stock;

    private boolean isDeleted;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>(); //업로드하는 파일

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>(); //업로드된 파일

}

package com.rootable.mallmarkme2024.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    private String name;

    private Integer price;

    private String description;

    private Integer stock;

    private boolean isDeleted;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>(); //업로드하는 파일

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>(); //업로드된 파일

}

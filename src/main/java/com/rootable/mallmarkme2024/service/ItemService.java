package com.rootable.mallmarkme2024.service;

import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.domain.ItemImage;
import com.rootable.mallmarkme2024.dto.ItemDTO;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import com.rootable.mallmarkme2024.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ItemService {

    PageResponseDTO<ItemDTO> getList(PageRequestDTO pageRequestDTO);

    ItemDTO get(Long id);

    Long register(ItemDTO dto);

    void update(ItemDTO dto);

    void delete(Long id);

    void clear();

    default ItemDTO entityToDTO(Item item) {

        ItemDTO dto = ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .writer(item.getWriter())
                .price(item.getPrice())
                .description(item.getDescription())
                .stock(item.getStock())
                .isDeleted(item.isDeleted())
                .build();

        List<ItemImage> imageList = item.getImageList();

        if (imageList == null || imageList.isEmpty()) {
            return dto;
        }

        List<String> fileNameList = imageList.stream().map(ItemImage::getFileName).toList();

        dto.setUploadFileNames(fileNameList);

        return dto;

    }

    default Item dtoToEntity(ItemDTO dto) {

        Item item = Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .writer(dto.getWriter())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .stock(dto.getStock())
                .build();

        List<String> uploadFileNames = dto.getUploadFileNames();

        if (uploadFileNames == null || uploadFileNames.size() == 0) {
            return item;
        }

        uploadFileNames.forEach(item::addImageString);

        return item;

    }

}

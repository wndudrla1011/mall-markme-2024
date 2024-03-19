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

    default ItemDTO entityToDTO(Item item) {

        ItemDTO itemDTO = ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .stock(item.getStock())
                .isDeleted(item.isDeleted())
                .build();

        List<ItemImage> imageList = item.getImageList();

        if (imageList == null || imageList.isEmpty()) {
            return itemDTO;
        }

        List<String> fileNameList = imageList.stream().map(ItemImage::getFileName).toList();

        itemDTO.setUploadFileNames(fileNameList);

        return itemDTO;

    }

}

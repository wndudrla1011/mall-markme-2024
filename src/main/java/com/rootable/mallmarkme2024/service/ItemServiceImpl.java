package com.rootable.mallmarkme2024.service;

import com.rootable.mallmarkme2024.domain.Item;
import com.rootable.mallmarkme2024.domain.ItemImage;
import com.rootable.mallmarkme2024.dto.ItemDTO;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import com.rootable.mallmarkme2024.dto.PageResponseDTO;
import com.rootable.mallmarkme2024.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    /*
    * 목록 조회
    * */
    @Override
    public PageResponseDTO<ItemDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("id").descending());

        Page<Object[]> pageData = itemRepository.selectList(pageable);

        List<ItemDTO> dtoList = pageData.get().map(arr -> {

            Item item = (Item) arr[0];
            ItemImage itemImage = (ItemImage) arr[1];

            String imageStr = itemImage.getFileName(); //업로드 파일 이름(ord = 0)

            return ItemDTO.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .price(item.getPrice())
                    .description(item.getDescription())
                    .stock(item.getStock())
                    .uploadFileNames(List.of(imageStr)) //0번째 파일 하나만
                    .build();

        }).toList();

        long totalCount = pageData.getTotalElements();

        return PageResponseDTO.<ItemDTO>innerBuilder()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount((int) totalCount)
                .build();

    }

}

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
import java.util.Optional;

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
                    .writer(item.getWriter())
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

    /*
    * 단건 조회
    * */
    @Override
    public ItemDTO get(Long id) {

        log.info("Item 조회 id = " + id);

        Optional<Item> result = itemRepository.selectOne(id);

        Item item = result.orElseThrow();

        return entityToDTO(item);

    }

    /*
    * 등록
    * */
    @Override
    public Long register(ItemDTO dto) {

        Item item = dtoToEntity(dto);

        log.info("--------------------------------");
        log.info(item);
        log.info(item.getImageList());

        return itemRepository.save(item).getId();

    }

    /*
    * 수정
    * */
    @Override
    public void update(ItemDTO dto) {

        Item item = itemRepository.findById(dto.getId()).orElseThrow();

        item.update(dto.getPrice(), dto.getDescription(), dto.getStock());

        List<String> uploadFileNames = dto.getUploadFileNames();

        item.clearList();

        //재업로드를 했다면
        if (uploadFileNames != null && !uploadFileNames.isEmpty()) {
            uploadFileNames.forEach(item::addImageString);
        }

        itemRepository.save(item);

    }

    /*
    * 삭제
    * */
    @Override
    public void delete(Long id) {
        itemRepository.delete(id, true);
    }

    /*
    * Clear
    * */
    @Override
    public void clear() {
        itemRepository.deleteAll();
    }

}

package com.rootable.mallmarkme2024.controller;

import com.rootable.mallmarkme2024.dto.ItemDTO;
import com.rootable.mallmarkme2024.dto.PageRequestDTO;
import com.rootable.mallmarkme2024.dto.PageResponseDTO;
import com.rootable.mallmarkme2024.service.ItemService;
import com.rootable.mallmarkme2024.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;
    private final CustomFileUtil fileUtil;

    @GetMapping("/{itemId}")
    public ItemDTO get(@PathVariable("itemId") Long itemId) {

        log.info("상품 정보");

        return itemService.get(itemId);

    }

    @GetMapping("/list")
    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO) {

        log.info("상품 목록");
        log.info("list : " + pageRequestDTO);

        return itemService.getList(pageRequestDTO);

    }

    @PostMapping("/")
    public Map<String, Long> register(ItemDTO dto) {

        log.info("상품 등록");
        log.info("register : " + dto);

        List<MultipartFile> files = dto.getFiles(); //업로드할 파일 목록

        List<String> uploadedFileNames = fileUtil.saveFiles(files); //업로드한 파일 목록

        dto.setUploadFileNames(uploadedFileNames); //DTO 바인딩

        log.info("uploadedFileNames : " + uploadedFileNames);

        Long itemId = itemService.register(dto); //상품 등록

        try {
            Thread.sleep(1000); //등록 대기 1s
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        return Map.of("RESULT", itemId);

    }

    @GetMapping("/read/{fileName}")
    public ResponseEntity<Resource> readFile(@PathVariable("fileName") String fileName) {

        log.info("파일 조회");

        return fileUtil.getFile(fileName);

    }



}

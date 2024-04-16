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
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private final CustomFileUtil fileUtil;

    @GetMapping("/{itemId}")
    public ItemDTO get(@PathVariable("itemId") Long itemId) {

        log.info("=======상품 정보 조회=======");

        return itemService.get(itemId);

    }

    @GetMapping("/list")
    public PageResponseDTO<ItemDTO> list(PageRequestDTO pageRequestDTO) {

        log.info("=======상품 목록 조회=======");
        log.info("list : " + pageRequestDTO);

        return itemService.getList(pageRequestDTO);

    }

    @PostMapping("/add")
    public Map<String, Long> register(ItemDTO dto) {

        log.info("=======상품 등록=======");
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

        log.info("=======파일 조회=======");

        return fileUtil.getFile(fileName);

    }

    @PutMapping("/{itemId}")
    public Map<String, String> update(@PathVariable("itemId") Long itemId, ItemDTO dto) {

        log.info("=======상품 수정=======");

        dto.setId(itemId);

        /*
         * Existing Files
         * 제거해야 할 파일 필터링 목적
         * */
        ItemDTO oldItemDTO = itemService.get(itemId);
        List<String> oldFileNames = oldItemDTO.getUploadFileNames();

        //New Files
        List<MultipartFile> newFiles = dto.getFiles();
        List<String> willUploadedFiles = fileUtil.saveFiles(newFiles);

        //Existing Files
        List<String> uploadedFileNames = dto.getUploadFileNames();

        //Final File List = (Existing + New) Files
        if (willUploadedFiles != null && !willUploadedFiles.isEmpty()) {
            uploadedFileNames.addAll(willUploadedFiles);
        }

        itemService.update(dto); //수정

        /*
         * Final File List -> willUploadedFiles
         * */
        if (oldFileNames != null && !oldFileNames.isEmpty()) {

            List<String> willRemoveList = oldFileNames
                    .stream()
                    .filter(oldFileName -> !uploadedFileNames.contains(oldFileName))
                    .toList();

            fileUtil.deleteFiles(willRemoveList);

        }

        return Map.of("RESULT", "SUCCESS");

    }

    @DeleteMapping("/{itemId}")
    public Map<String, String> delete(@PathVariable("itemId") Long itemId) {

        log.info("=======상품 삭제=======");

        List<String> oldFileNames = itemService.get(itemId).getUploadFileNames();

        itemService.delete(itemId);

        fileUtil.deleteFiles(oldFileNames);

        return Map.of("RESULT", "SUCCESS");

    }

}

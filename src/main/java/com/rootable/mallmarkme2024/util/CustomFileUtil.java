package com.rootable.mallmarkme2024.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${com.rootable.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {

        File tempFolder = new File(uploadPath);

        if (!tempFolder.exists()) {
            tempFolder.mkdir(); //폴더 생성
        }

        uploadPath = tempFolder.getAbsolutePath();

        log.info("---------------------------");
        log.info(uploadPath);

    }

    /*
    * 업로드
    * */
    public List<String> saveFiles(List<MultipartFile> files) {

        if (files == null || files.size() == 0) {
            return null;
        }

        List<String> uploadNames = new ArrayList<>(); //업로드된 파일 이름들

        for (MultipartFile file : files) {

            String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); //저장할 파일 이름

            Path savePath = Paths.get(uploadPath, savedName); //uploadPath: 저장할 경로

            try {
                Files.copy(file.getInputStream(), savePath); //원본 파일 업로드

                String contentType = file.getContentType(); //MIME 타입

                //이미지 파일
                if (contentType != null && contentType.startsWith("image")) {

                    //설정: 썸네일 이름 + 업로드 경로
                    Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);

                    //설정: 썸네일 크기, 업로드
                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile(thumbnailPath.toFile());

                }

                uploadNames.add(savedName); //파일 이름 추가
            } catch (IOException e) {
                throw new RuntimeException();
            }

        }

        return uploadNames;

    }

    /*
    * 업로드 파일 조회
    * */
    public ResponseEntity<Resource> getFile(String fileName) {

        //File 데이터에 대한 액세스
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        //예외 처리
        if (!resource.isReadable()) {
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpg");
        }

        HttpHeaders headers = new HttpHeaders(); //Http 헤더 액세스

        try {
            //MIME 타입 설정
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().headers(headers).body(resource);

    }

    /*
    * 여러 파일 삭제
    * */
    public void deleteFiles(List<String> fileNames) {

        if (fileNames == null || fileNames.size() == 0) {
            return;
        }

        fileNames.forEach(fileName -> {

            String thumbnailFileName = "s_" + fileName;

            //원본/썸네일 파일 경로
            Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
            Path filePath = Paths.get(uploadPath, fileName);

            try {
                Files.deleteIfExists(filePath); //파일 삭제
                Files.deleteIfExists(thumbnailPath); //썸네일 삭제
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

        });

    }

}

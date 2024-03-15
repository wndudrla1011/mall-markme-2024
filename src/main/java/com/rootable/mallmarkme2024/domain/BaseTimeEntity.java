package com.rootable.mallmarkme2024.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    //생성 시간
    @CreatedDate
    private LocalDateTime createdDate;

    //수정 시간
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}

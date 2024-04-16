package com.rootable.mallmarkme2024.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private boolean isDeleted;

    @ElementCollection(fetch = LAZY)
    @ToString.Exclude
    private List<Role> roleList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @OrderBy("id desc") //댓글 정렬
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname, boolean isDeleted, List<Role> roleList) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.isDeleted = isDeleted;
        this.roleList = roleList;
    }

}

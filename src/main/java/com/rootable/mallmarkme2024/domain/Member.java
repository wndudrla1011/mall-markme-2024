package com.rootable.mallmarkme2024.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @ElementCollection(fetch = LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Role> roleList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @OrderBy("id desc") //댓글 정렬
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();

    public void addRole(Role role) {
        roleList.add(role);
    }

}

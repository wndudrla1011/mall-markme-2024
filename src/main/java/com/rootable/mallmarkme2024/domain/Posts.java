package com.rootable.mallmarkme2024.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @ToString.Exclude
    private Member member;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    @OrderBy("id desc") //댓글 정렬
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();

    private boolean isDeleted;

    @ElementCollection
    @ToString.Exclude
    private List<PostsImage> imageList = new ArrayList<>();

    @Builder
    public Posts(Long id, String title, String content, Item item, Member member, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.item = item;
        this.member = member;
        this.isDeleted = isDeleted;
    }

}

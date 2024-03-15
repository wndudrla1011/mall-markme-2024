package com.rootable.mallmarkme2024.repository;

import com.rootable.mallmarkme2024.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}

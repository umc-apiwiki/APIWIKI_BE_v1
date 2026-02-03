package com.umc.apiwiki.domain.user.repository;

import com.umc.apiwiki.domain.user.entity.UserFavoriteApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoriteApiRepository extends JpaRepository<UserFavoriteApi, Long> {
    boolean existsByUserIdAndApiId(Long userId, Long apiId);
}

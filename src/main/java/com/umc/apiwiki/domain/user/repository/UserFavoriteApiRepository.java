package com.umc.apiwiki.domain.user.repository;

import com.umc.apiwiki.domain.user.entity.UserFavoriteApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserFavoriteApiRepository extends JpaRepository<UserFavoriteApi, Long> {
    boolean existsByUserIdAndApiId(Long userId, Long apiId);

    Optional<UserFavoriteApi> findByUserIdAndApiId(Long userId, Long apiId);

    // 활동 내역용
    List<UserFavoriteApi> findAllByUserId(Long userId);

    @Query("""
        select f.api.id
        from UserFavoriteApi f
        where f.user.id = :userId
          and f.api.id in :apiIds
    """)
    List<Long> findFavoriteApiIds(Long userId, List<Long> apiIds);
}

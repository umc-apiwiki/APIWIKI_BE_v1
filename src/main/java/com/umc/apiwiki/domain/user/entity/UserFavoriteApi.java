package com.umc.apiwiki.domain.user.entity;

import com.umc.apiwiki.domain.api.entity.Api;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "user_favorite_apis",
        indexes = @Index(name = "idx_user_api", columnList = "user_id, api_id")

)
public class UserFavoriteApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_apis_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id")
    private Api api;
}
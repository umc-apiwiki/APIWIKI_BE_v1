package com.umc.apiwiki.domain.wiki.entity;

import com.umc.apiwiki.domain.api.entity.Api;
import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "wiki")
public class Wiki extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wiki_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id", nullable = false)
    private Api api;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // creator_id
    private User user;

    @Lob
    @Column(name = "content_md", columnDefinition = "LONGTEXT")
    private String contentMd;
}
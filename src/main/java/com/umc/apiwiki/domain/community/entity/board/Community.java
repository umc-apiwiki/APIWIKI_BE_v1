package com.umc.apiwiki.domain.community.entity.board;

import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "community")
public class Community extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 20)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
}
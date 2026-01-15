package com.umc.apiwiki.domain.wiki.entity;

import com.umc.apiwiki.domain.user.entity.User;
import com.umc.apiwiki.domain.wiki.enums.RequestState;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "wiki_edit_requests")
@EntityListeners(AuditingEntityListener.class) // createdAt을 위함
public class WikiEditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wiki_id", nullable = false)
    private Wiki wiki;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RequestState state;

    @Lob
    @Column(name = "new_content_md", columnDefinition = "LONGTEXT")
    private String newContentMd;

    @CreatedDate // 생성 시 자동 주입
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
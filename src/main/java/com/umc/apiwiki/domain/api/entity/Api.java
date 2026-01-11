package com.umc.apiwiki.domain.api.entity;

import com.umc.apiwiki.domain.wiki.entity.Wiki;
import com.umc.apiwiki.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "apis")
public class Api extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 120)
    private String summary;

    @Lob // Text 타입 매핑
    @Column(columnDefinition = "TEXT")
    private String longDescription;

    private String officialUrl;

    @Column(precision = 3, scale = 1) // decimal(3,1)
    private BigDecimal avgRating;

    private Long viewCounts;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String pricingInfo;

    @Column(length = 20)
    private String exampleLanguage;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String exampleCode;

    // Wiki와 1:1 관계 (Api가 주인이 아닌 경우 mappedBy 설정 가능하나, ERD상 Wiki가 Api를 FK로 가짐)
    @OneToOne(mappedBy = "api", fetch = FetchType.LAZY)
    private Wiki wiki;
}
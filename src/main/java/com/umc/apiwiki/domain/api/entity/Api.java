package com.umc.apiwiki.domain.api.entity;

import com.umc.apiwiki.domain.api.enums.AuthType;
import com.umc.apiwiki.domain.api.enums.PricingType;
import com.umc.apiwiki.domain.api.enums.ProviderCompany;
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

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 120)
    private String summary;

    private String logo;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String longDescription;

    private String officialUrl;

    @Column(precision = 3, scale = 1)
    private BigDecimal avgRating;

    @Builder.Default
    @Column(columnDefinition = "bigint default 0")
    private Long viewCounts = 0L;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String pricingInfo;

    @Enumerated(EnumType.STRING)
    private PricingType pricingType;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Enumerated(EnumType.STRING)
    private ProviderCompany providerCompany;

    // 조회수 증가 로직
    public void increaseViewCount() {
        if (this.viewCounts == null) {
            this.viewCounts = 0L;
        }
        this.viewCounts++;
    }

    // 별점 업데이트 로직
    public void updateAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }
}
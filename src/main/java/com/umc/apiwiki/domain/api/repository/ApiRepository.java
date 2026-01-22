package com.umc.apiwiki.domain.api.repository;

import com.umc.apiwiki.domain.api.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long>{
}

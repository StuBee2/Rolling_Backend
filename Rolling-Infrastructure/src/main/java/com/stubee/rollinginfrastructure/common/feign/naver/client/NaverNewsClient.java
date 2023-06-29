package com.stubee.rollinginfrastructure.common.feign.naver.client;

import com.stubee.rollinginfrastructure.common.feign.naver.config.NaverFeignClientConfig;
import com.stubee.rollinginfrastructure.common.feign.naver.dto.NaverNewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feign", url = "https://openapi.naver.com/v1/search/news.json",
        configuration = NaverFeignClientConfig.class)
public interface NaverNewsClient {

    @GetMapping("?query={name}&display={size}&start={page}&sort=date")
    NaverNewsResponse findByName(@PathVariable("name") String name, @PathVariable("size") int size, @PathVariable("page") int page);

}
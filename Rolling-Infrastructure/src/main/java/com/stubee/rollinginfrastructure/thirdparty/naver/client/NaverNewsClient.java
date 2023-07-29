package com.stubee.rollinginfrastructure.thirdparty.naver.client;

import com.stubee.rollinginfrastructure.thirdparty.naver.config.NaverFeignClientConfig;
import com.stubee.rollinginfrastructure.thirdparty.naver.dto.NaverNewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feign", url = NaverNewsClient.NaverNewsUrl.baseUrl,
        configuration = NaverFeignClientConfig.class)
public interface NaverNewsClient {

    @GetMapping(NaverNewsUrl.queryUrl)
    NaverNewsResponse findByName(@PathVariable("name") String name, @PathVariable("size") int size, @PathVariable("page") int page);

    class NaverNewsUrl {

        protected static final String baseUrl = "https://openapi.naver.com/v1/search/news.json";
        private static final String queryUrl = "?query={name}&display={size}&start={page}&sort=date";

    }

}
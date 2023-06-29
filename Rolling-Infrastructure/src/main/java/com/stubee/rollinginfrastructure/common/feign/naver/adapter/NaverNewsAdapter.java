package com.stubee.rollinginfrastructure.common.feign.naver.adapter;

import com.stubee.rollingapplication.domain.news.port.spi.NewsPort;
import com.stubee.rollingcore.domain.news.dto.response.NewsResponse;
import com.stubee.rollinginfrastructure.common.feign.naver.client.NaverNewsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverNewsAdapter implements NewsPort {

    private final NaverNewsClient naverNewsClient;

    public NewsResponse getByCompanyName(final String companyName, final int size, final int page) {
        final String encodedName = URLEncoder.encode(companyName, StandardCharsets.UTF_8);

        return NewsResponse.create(naverNewsClient.findByName(encodedName, size, (page-1)*size+1));
    }

}
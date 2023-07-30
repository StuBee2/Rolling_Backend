package com.stubee.rollinginfrastructure.thirdparty.naver.adapter;

import com.stubee.rollinginfrastructure.global.annotation.Adapter;
import com.stubee.rollingapplication.domain.news.port.spi.NewsPort;
import com.stubee.rollingcore.common.dto.response.PageDataResponse;
import com.stubee.rollinginfrastructure.thirdparty.naver.client.NaverNewsClient;
import com.stubee.rollinginfrastructure.thirdparty.naver.dto.NaverNewsResponse;
import lombok.RequiredArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Adapter
@RequiredArgsConstructor
public class NaverNewsAdapter implements NewsPort {

    private final NaverNewsClient naverNewsClient;

    public PageDataResponse<NaverNewsResponse> getByCompanyName(final String companyName, final int size, final int page) {
        final String encodedName = URLEncoder.encode(companyName, StandardCharsets.UTF_8);

        return PageDataResponse.create(naverNewsClient.findByName(encodedName, size, (page-1)*size+1));
    }

}
package com.stubee.rollingbatch.company.reader;

import com.stubee.rollingapplication.domain.company.port.api.query.QueryAllCompanyListUseCase;
import com.stubee.rollingbatch.common.annotation.Reader;
import com.stubee.rollingcore.common.dto.request.PageRequest;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

import java.util.List;

@Reader
@Slf4j
@RequiredArgsConstructor
public class CompanyListReader implements ItemReader<List<Company>> {

    private static final long SIZE = 100;

    private final QueryAllCompanyListUseCase queryCompanyUseCase;

    private long currentPage = 1;

    @Override
    public List<Company> read() {
        log.info("<<<<<Reader Read>>>>>");
        log.info("CurrentPage : {}", currentPage);

        final List<Company> companyList = queryCompanyUseCase.get(pageRequest(currentPage));

        log.info("CompanyList : {}", companyList.size());

        if (companyList.isEmpty()) {
            return null;
        }

        currentPage++;

        log.info("<<<<<Reader End>>>>>");

        return companyList;
    }

    private PageRequest pageRequest(final long page) {
        return new PageRequest(page, SIZE);
    }

}
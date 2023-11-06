package com.stubee.companyaveragebatch.reader;

import com.stubee.companyapplication.usecases.query.CompanyResponse;
import com.stubee.rollingdomains.common.model.dtos.request.PageRequest;
import com.stubee.batchcommons.annotations.Reader;
import com.stubee.companyapplication.usecases.query.QueryAllCompanyListUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import com.stubee.rollingdomains.domain.company.model.CompanyId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

import java.util.List;

import static com.stubee.companyaveragebatch.job.consts.BatchConstants.*;

@Reader
@Slf4j
@RequiredArgsConstructor
public class CompanyListReader implements ItemReader<List<Company>> {

    private final QueryAllCompanyListUseCase queryCompanyUseCase;

    private long currentPage = 1;

    @Override
    public List<Company> read() {
        log.info("Reader Start");
        log.info("CurrentPage : {}", currentPage);

        return determineItems(queryCompanyUseCase.get(PageRequest.of(currentPage, PAGE_SIZE)).data().stream()
                .map(this::toDomain)
                .toList());
    }

    private List<Company> determineItems(final List<Company> companyList) {
        log.info("CompanyList Size : {}", companyList.size());

        if (companyList.isEmpty()) {
            return null;
        }

        currentPage++;

        log.info("Reader End");

        return companyList;
    }

    private Company toDomain(CompanyResponse response) {
        return Company.WithIdBuilder()
                .companyId(CompanyId.of(Long.parseLong(response.companyId().id())))
                .companyDetails(response.companyDetails())
                .companyGrades(response.companyGrades())
                .build();
    }

}
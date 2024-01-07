package rolling.rollingbatch.reader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import rolling.application.company.interactor.query.CompanyResponse;
import rolling.application.company.interactor.query.QueryAllCompanyUseCase;
import rolling.domain.common.model.PageRequest;
import rolling.domain.company.model.Company;
import rolling.domain.company.model.CompanyId;

import java.util.List;

import static rolling.rollingbatch.job.consts.BatchConstants.PAGE_SIZE;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyListReader implements ItemReader<List<Company>> {

    private final QueryAllCompanyUseCase queryAllCompanyUseCase;

    private long currentPage = 1;

    @Override
    public List<Company> read() {
        log.info("-----Reader Start-----");
        log.info("CurrentPage : {}", currentPage);

        return determineItems(queryAllCompanyUseCase.query(PageRequest.of(currentPage, PAGE_SIZE)).data().stream()
                .map(this::toDomain)
                .toList());
    }

    private List<Company> determineItems(final List<Company> companyList) {
        log.info("CompanyList Size : {}", companyList.size());

        if (companyList.isEmpty()) {
            return null;
        }

        currentPage++;

        log.info("-----Reader End-----");

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
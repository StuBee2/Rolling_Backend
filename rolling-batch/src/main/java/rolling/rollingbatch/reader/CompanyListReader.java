package rolling.rollingbatch.reader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.domain.common.model.PageRequest;
import rolling.domain.company.model.Company;

import java.util.List;

import static rolling.rollingbatch.job.consts.BatchConstants.PAGE_SIZE;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyListReader implements ItemReader<List<Company>> {

    private final QueryCompanyPort queryCompanyPort;

    private long currentPage = 1;

    @BeforeRead
    public void beforeRead() {
        log.info("-----Reader Start-----");
    }

    @AfterRead
    public void afterRead(final List<Company> companyList) {
        log.info("CompanyList Size : {}", companyList.size());

        currentPage++;

        log.info("-----Reader End-----");
    }

    @Override
    public List<Company> read() {
        final List<Company> companyList = queryCompanyPort.getAll(PageRequest.of(currentPage, PAGE_SIZE));

        if(companyList.isEmpty()) {
            return null;
        }

        return companyList;
    }

}
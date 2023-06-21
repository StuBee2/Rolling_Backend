package com.stubee.rollingbatch.company.writer;

import com.stubee.rollingapplication.domain.company.port.api.CommandCompanyUseCase;
import com.stubee.rollingcore.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CompanyItemWriter implements ItemWriter<List<Company>> {

    private final CommandCompanyUseCase commandCompanyUseCase;

    @Override
    public void write(Chunk<? extends List<Company>> chunk) {
        log.info("<<<<<Writer Write>>>>>");
        log.info("chunk size : {}", chunk.size());

        for (List<Company> companyList : chunk.getItems()) {
            for (Company company : companyList) {
                commandCompanyUseCase.update(company);
            }
        }

        log.info("<<<<<Writer End>>>>>");
    }

}
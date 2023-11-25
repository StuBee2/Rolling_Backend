package com.stubee.companyaveragebatch.writer;

import com.stubee.batchcommons.annotations.Writer;
import com.stubee.companyapplication.usecases.command.UpdateCompanyUseCase;
import com.stubee.rollingdomains.domain.company.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Writer
@Slf4j
@RequiredArgsConstructor
public class CompanyListWriter implements ItemWriter<List<Company>> {

    private final UpdateCompanyUseCase commandCompanyUseCase;

    @Override
    public void write(final Chunk<? extends List<Company>> chunk) {
        log.info("-----Writer Start-----");
        log.info("chunk size : {}", chunk.size());

        chunk.getItems().forEach(commandCompanyUseCase::updateAll);

        log.info("-----Writer End-----");
    }

}
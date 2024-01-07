package rolling.rollingbatch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import rolling.application.company.interactor.command.UpdateCompanyUseCase;
import rolling.domain.company.model.Company;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyListWriter implements ItemWriter<List<Company>> {

    private final UpdateCompanyUseCase updateCompanyUseCase;

    @Override
    public void write(final Chunk<? extends List<Company>> chunk) {
        log.info("-----Writer Start-----");
        log.info("chunk size : {}", chunk.size());

        chunk.getItems().forEach(updateCompanyUseCase::update);

        log.info("-----Writer End-----");
    }

}
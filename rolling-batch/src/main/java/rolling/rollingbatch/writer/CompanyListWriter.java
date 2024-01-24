package rolling.rollingbatch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import rolling.application.company.outport.CommandCompanyPort;
import rolling.domain.company.model.Company;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompanyListWriter implements ItemWriter<List<Company>> {

    private final CommandCompanyPort commandCompanyPort;

    @BeforeWrite
    public void beforeWrite(final Chunk<? extends List<Company>> chunk) {
        log.info("-----Writer Start-----");
        log.info("chunk size : {}", chunk.size());
    }

    @AfterWrite
    public void afterWrite() {
        log.info("-----Writer End-----");
    }

    @Override
    public void write(final Chunk<? extends List<Company>> chunk) {
        chunk.getItems().forEach(commandCompanyPort::updateAll);
    }

}
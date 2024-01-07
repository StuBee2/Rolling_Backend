package rolling.application.news.outport;

import reactor.core.publisher.Mono;
import rolling.domain.common.model.PageRequest;

public interface NewsPort {

    Mono<?> getByCompanyName(String companyName, PageRequest pageRequest);

}
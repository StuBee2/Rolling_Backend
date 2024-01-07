package rolling.application.company.outport;

import rolling.application.company.interactor.query.CompanyQueryResponse;
import rolling.domain.common.model.PageRequest;
import rolling.domain.company.exception.CompanyNotFoundException;
import rolling.domain.company.model.Company;

import java.util.List;
import java.util.Optional;

public interface QueryCompanyPort {

    boolean existsBy(Long id);
    boolean existsBy(String name);

    Optional<Company> findBy(Long id);

    default Company getBy(final Long id) {
        return findBy(id)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    Optional<CompanyQueryResponse> findInfoBy(Long id);

    default CompanyQueryResponse getInfoBy(final Long id) {
        return findInfoBy(id)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    List<Company> getOrderBy(String gradeType);

    List<Company> searchByName(String name, PageRequest pageRequest);

    List<Company> getByRegistrant(Long registrantId, PageRequest pageRequest);

    List<Company> getAll(PageRequest pageRequest);

}
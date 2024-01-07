package rolling.jpamysql.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rolling.application.company.interactor.query.CompanyQueryResponse;
import rolling.application.company.outport.QueryCompanyPort;
import rolling.domain.common.model.PageRequest;
import rolling.domain.company.model.Company;

import java.util.List;
import java.util.Optional;

import static rolling.jpamysql.company.CompanyMapper.toDomain;
import static rolling.jpamysql.company.CompanyMapper.toDomainList;

@Component
@RequiredArgsConstructor
class CompanyQueryDSLAdapter implements QueryCompanyPort {

    private final CompanyQueryDSLRepository repository;

    @Override
    public boolean existsBy(final Long id) {
        return repository.existsByCompanyId(id);
    }

    @Override
    public boolean existsBy(final String name) {
        return repository.existsByCompanyName(name);
    }

    @Override
    public Optional<Company> findBy(final Long id) {
        return Optional.ofNullable(toDomain(repository.findById(id)));
    }

    @Override
    public Optional<CompanyQueryResponse> findInfoBy(final Long id) {
        return Optional.ofNullable(repository.findInfoById(id));
    }

    @Override
    public List<Company> getOrderBy(final String gradeType) {
        return toDomainList(repository.findOrderBy(gradeType));
    }

    @Override
    public List<Company> searchByName(final String name, final PageRequest pageRequest) {
        return toDomainList(repository.findByNameContaining(name, pageRequest));
    }

    @Override
    public List<Company> getByRegistrant(final Long registrantId, final PageRequest pageRequest) {
        return toDomainList(repository.findByRegistrantId(registrantId, pageRequest));
    }

    @Override
    public List<Company> getAll(final PageRequest pageRequest) {
        return toDomainList(repository.findAll(pageRequest));
    }

}
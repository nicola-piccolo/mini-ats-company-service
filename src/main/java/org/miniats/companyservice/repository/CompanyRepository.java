package org.miniats.companyservice.repository;

import java.sql.SQLException;
import java.util.Optional;

import org.miniats.companyservice.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
	@Retryable(value = SQLException.class, maxAttempts = 2, backoff = @Backoff(delay = 200))
	Optional<Company> findByUid(String uid);

	@Retryable(value = SQLException.class, maxAttempts = 2, backoff = @Backoff(delay = 200))
	Iterable<Company> findAll();
}

package se.rodion.taskboard.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.rodion.taskboard.model.Issue;

public interface IssueRepository extends IssueRepositoryCustom, PagingAndSortingRepository<Issue, Long>
{
	Collection<Issue> findByWorkItemIsNotNull();

	Page<Issue> findAll(Pageable pageable);
}

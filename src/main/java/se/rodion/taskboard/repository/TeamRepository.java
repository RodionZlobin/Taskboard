package se.rodion.taskboard.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.rodion.taskboard.model.Team;

public interface TeamRepository extends TeamRepositoryCustom, PagingAndSortingRepository<Team, Long>
{
	Collection<Team> findAll();

	Page<Team> findAll(Pageable pageable);
}

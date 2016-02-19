package se.rodion.taskboard.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.rodion.taskboard.model.User;

public interface UserRepository extends UserRepositoryCustom, PagingAndSortingRepository<User, Long>
{
	User findByUserNumber(String userNumber);

	User findByFirstNameAndLastNameAndUserNumber(String firstName, String lastName, String userNumber);

	Collection<User> findByFirstNameOrLastNameOrUserNumber(String firstName, String lastName, String userNumber);

	Collection<User> findByTeamId(Long id);

	Page<User> findAll(Pageable pageable);
}

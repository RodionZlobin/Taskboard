package se.rodion.taskboard.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.rodion.taskboard.model.User;
import se.rodion.taskboard.model.WorkItem;
import se.rodion.taskboard.status.WorkItemStatus;

public interface WorkItemRepository extends WorkItemRepositoryCustom, PagingAndSortingRepository<WorkItem, Long>
{
	Collection<WorkItem> findAllByStatus(WorkItemStatus status);

	@Query("select e from #{#entityName} e join e.user u join u.team t where t.name = ?1")
	Collection<WorkItem> findByTeam(String name);

	Collection<WorkItem> findAllByUser(User user);

	@Query("select e from #{#entityName} e where e.description like %?1%")
	Collection<WorkItem> findByDescription(String description);

	WorkItem findOne(Long id);

	Page<WorkItem> findAll(Pageable pageable);

	Collection<WorkItem> findByCreatingDateBetween(Date start, Date finish);

	Collection<WorkItem> findByCreatingDateBetweenAndStatus(Date start, Date finish, WorkItemStatus status);
}

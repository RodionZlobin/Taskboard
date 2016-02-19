package se.rodion.taskboard.service;

import java.util.Collection;
import java.util.Date;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import se.rodion.taskboard.model.User;
import se.rodion.taskboard.model.WorkItem;
import se.rodion.taskboard.repository.WorkItemRepository;
import se.rodion.taskboard.status.UserStatus;
import se.rodion.taskboard.status.WorkItemStatus;

@Service
public class WorkItemService
{
	WorkItemRepository workItemRepository;

	@Autowired
	public WorkItemService(WorkItemRepository workItemRepository)
	{
		this.workItemRepository = workItemRepository;
	}

	public WorkItem save(WorkItem workItem)
	{
		return workItemRepository.save(workItem);
	}

	// @Transactional //varför fungerar det bara utan @Transactional
	public void deleteWorkItem(Long id)
	{
		WorkItem workItemToDelete = findById(id);
		workItemToDelete.setUser(null);
		workItemRepository.save(workItemToDelete);
		workItemRepository.delete(id);
	}

	public WorkItem changeStatus(WorkItem workItem, WorkItemStatus status)
	{
		return workItemRepository.save(workItemRepository.changeStatus(workItem, status));
	}

	public WorkItem addWorkItemToUser(WorkItem workItem, User user)
	{
		if (user.getUserStatus().equals(UserStatus.VALID))
		{
			if (user.getWorkItems().size() < 5)
			{

				WorkItem workItemNew = workItemRepository.addWorkItemToUser(workItem, user);
				return workItemRepository.save(workItemNew);
			}
			else
			{
				throw new ServiceException("This user have more Items than accepted");
			}
		}
		else
		{
			throw new ServiceException("User could have 'VALID' status");
		}
	}

	public Collection<WorkItem> fetchAllWorkItemsByStatus(WorkItemStatus status)
	{
		return workItemRepository.findAllByStatus(status);
	}

	public Collection<WorkItem> fetchWorkItemsByTeam(String name)
	{
		return workItemRepository.findByTeam(name);
	}

	public Collection<WorkItem> fetchWorkItemsByUser(User user)
	{
		return workItemRepository.findAllByUser(user);
	}

	public Collection<WorkItem> fetchWorkItemsByDescription(String description)
	{
		return workItemRepository.findByDescription(description);
	}

	public WorkItem findById(Long id)
	{
		return workItemRepository.findOne(id);
	}

	public Page<WorkItem> findAll(int page, int size)
	{
		return workItemRepository.findAll(new PageRequest(page, size));
	}

	public Collection<WorkItem> fetchAllWorkItemInSpecifiedPeriod(Date fromDate, Date toDate)
	{
		return workItemRepository.findByCreatingDateBetween(fromDate, toDate);
	}

	public Collection<WorkItem> fetchAllWorkItemWithSpecifiedPeriodAndStatus(Date fromDate, Date toDate, WorkItemStatus status)
	{
		return workItemRepository.findByCreatingDateBetweenAndStatus(fromDate, toDate, status);
	}
}

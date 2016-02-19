package se.rodion.taskboard.repository;

import org.springframework.transaction.annotation.Transactional;

import se.rodion.taskboard.model.User;
import se.rodion.taskboard.model.WorkItem;
import se.rodion.taskboard.status.WorkItemStatus;

public class WorkItemRepositoryImpl implements WorkItemRepositoryCustom
{

	@Override
	public WorkItem changeStatus(WorkItem workItem, WorkItemStatus status)
	{
		workItem.setStatus(status);
		return workItem;
	}

	@Override
	@Transactional
	public WorkItem addWorkItemToUser(WorkItem workItem, User user)
	{
		user.addWorkItem(workItem);
		workItem.setUser(user);
		return workItem;
	}

}

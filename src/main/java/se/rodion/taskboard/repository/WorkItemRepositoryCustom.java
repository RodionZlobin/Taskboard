package se.rodion.taskboard.repository;

import se.rodion.taskboard.model.User;
import se.rodion.taskboard.model.WorkItem;
import se.rodion.taskboard.status.WorkItemStatus;

public interface WorkItemRepositoryCustom
{
	WorkItem changeStatus(WorkItem workItem, WorkItemStatus status);

	WorkItem addWorkItemToUser(WorkItem workItem, User user);
}

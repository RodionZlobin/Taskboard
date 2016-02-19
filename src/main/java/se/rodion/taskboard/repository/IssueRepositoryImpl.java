package se.rodion.taskboard.repository;

import org.springframework.transaction.annotation.Transactional;

import se.rodion.taskboard.model.Issue;
import se.rodion.taskboard.model.WorkItem;
import se.rodion.taskboard.status.WorkItemStatus;

public class IssueRepositoryImpl implements IssueRepositoryCustom
{

	@Override
	@Transactional
	public Issue addIssueToWorkItem(Issue issue, WorkItem workItem)
	{
		/*
		 * det kommer att aktivera med bidirectional relationen.
		 * workItem.addIssue(issue);
		 */
		workItem.setStatus(WorkItemStatus.UNSTARTED);
		issue.setWorkItem(workItem);
		return issue;
	}

}

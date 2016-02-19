package se.rodion.taskboard.repository;

import se.rodion.taskboard.model.Issue;
import se.rodion.taskboard.model.WorkItem;

public interface IssueRepositoryCustom
{
	Issue addIssueToWorkItem(Issue issue, WorkItem workItem);
}

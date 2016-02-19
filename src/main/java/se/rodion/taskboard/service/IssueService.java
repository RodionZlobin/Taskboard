package se.rodion.taskboard.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.rodion.taskboard.exception.ServiceException;
import se.rodion.taskboard.model.Issue;
import se.rodion.taskboard.model.WorkItem;
import se.rodion.taskboard.repository.IssueRepository;
import se.rodion.taskboard.status.WorkItemStatus;

@Service
public class IssueService
{
	IssueRepository issueRepository;

	@Autowired
	public IssueService(IssueRepository issueRepository)
	{
		this.issueRepository = issueRepository;
	}

	public Issue saveIssue(Issue issue)
	{
		return issueRepository.save(issue);
	}

	@Transactional
	public Issue addIssueToWorkItem(Issue issue, WorkItem workItem) throws ServiceException
	{
		if (workItem.getStatus().equals(WorkItemStatus.DONE))
		{
			Issue issueNew = issueRepository.addIssueToWorkItem(issue, workItem);
			return issueRepository.save(issueNew);
		}
		else
		{
			throw new ServiceException("WorkItems could be 'DONE'");
		}
	}

	public Issue findIssueById(Long id)
	{
		return issueRepository.findOne(id);
	}

	public Collection<Issue> findWorkItemsWithIssue()
	{
		return issueRepository.findByWorkItemIsNotNull();
	}

	public Page<Issue> findAll(int page, int size)
	{
		return issueRepository.findAll(new PageRequest(page, size));
	}
}

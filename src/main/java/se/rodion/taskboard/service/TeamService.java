package se.rodion.taskboard.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.rodion.taskboard.exception.ServiceException;
import se.rodion.taskboard.model.Team;
import se.rodion.taskboard.model.User;
import se.rodion.taskboard.repository.TeamRepository;

@Service
public class TeamService
{
	TeamRepository teamRepository;

	@Autowired
	public TeamService(TeamRepository teamRepository)
	{
		this.teamRepository = teamRepository;
	}

	public Team save(Team team)
	{
		return teamRepository.save(team);
	}

	@Transactional
	public void deleteTeam(Long id)
	{
		teamRepository.delete(id);
	}

	@Transactional
	public void inactivateTeam(Team team)
	{
		teamRepository.inactivateTeam(team);
		teamRepository.save(team);
	}

	public Collection<Team> findAll()
	{
		return teamRepository.findAll();
	}

	@Transactional
	public void addUser(Team team, User user) throws ServiceException
	{

		if (team.getUsers().size() < 10)
		{
			if (user.getTeam() == null)
			{
				teamRepository.addUser(team, user);
				teamRepository.save(team);
			}
			else
			{
				throw new ServiceException("User could enter to 1 Team at once only");
			}
		}
		else
		{
			throw new ServiceException("This team have alrwady 10 members");
		}
	}

	public Team findTeam(Long id)
	{
		return teamRepository.findOne(id);
	}

	public Team updateTeam(Team team)
	{
		return teamRepository.save(team);
	}

	public Page<Team> findAll(int page, int size)
	{
		return teamRepository.findAll(new PageRequest(page, size));
	}
}

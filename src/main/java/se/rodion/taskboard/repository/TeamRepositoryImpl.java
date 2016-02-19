package se.rodion.taskboard.repository;

import org.springframework.transaction.annotation.Transactional;

import se.rodion.taskboard.model.Team;
import se.rodion.taskboard.model.User;
import se.rodion.taskboard.status.TeamStatus;

public class TeamRepositoryImpl implements TeamRepositoryCustom
{

	@Override
	@Transactional
	public void addUser(Team team, User user)
	{
		team.addUser(user);
		user.setTeam(team);
	}

	@Override
	public void inactivateTeam(Team team)
	{
		team.setStatus(TeamStatus.NON_ACTIVE);
	}

}

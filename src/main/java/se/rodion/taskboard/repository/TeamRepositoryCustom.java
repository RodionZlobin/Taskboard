package se.rodion.taskboard.repository;

import se.rodion.taskboard.model.Team;
import se.rodion.taskboard.model.User;

public interface TeamRepositoryCustom
{
	void addUser(Team team, User user);

	void inactivateTeam(Team team);
}

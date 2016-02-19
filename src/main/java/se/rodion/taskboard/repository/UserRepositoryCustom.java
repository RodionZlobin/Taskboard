package se.rodion.taskboard.repository;

import se.rodion.taskboard.model.User;

public interface UserRepositoryCustom
{
	void inactivateUser(User user);
}

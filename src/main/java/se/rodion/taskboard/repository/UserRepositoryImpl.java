package se.rodion.taskboard.repository;

import org.springframework.transaction.annotation.Transactional;

import se.rodion.taskboard.model.User;
import se.rodion.taskboard.status.UserStatus;
import se.rodion.taskboard.status.WorkItemStatus;

public class UserRepositoryImpl implements UserRepositoryCustom
{

	@Override
	@Transactional
	public void inactivateUser(User user)
	{
		user.setUserStatus(UserStatus.NON_VALID);
		user.getWorkItems().forEach(e -> e.setStatus(WorkItemStatus.UNSTARTED));
	}
}

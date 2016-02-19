package se.rodion.taskboard.service;

import java.util.Collection;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.rodion.taskboard.model.User;
import se.rodion.taskboard.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	public User save(User user)
	{
		if (user.getUsername().length() < 10)
		{
			throw new ServiceException("Username could have at least 10 symbols");
		}
		return userRepository.save(user);
	}

	public void removeUser(User user)
	{
		userRepository.delete(user.getId());
	}

	@Transactional
	public void inactivateUser(User user)
	{
		userRepository.inactivateUser(user);
		userRepository.save(user);
	}

	public User findByUserNumber(String userNumber)
	{
		return userRepository.findByUserNumber(userNumber);
	}

	public User findByFirstNameAndLastNameAndUserNumber(String firstName, String lastName, String userNumber)
	{
		return userRepository.findByFirstNameAndLastNameAndUserNumber(firstName, lastName, userNumber);
	}

	public Collection<User> findByFirstNameOrLastNameOrUserNumber(String firstName, String lastName, String userNumber)
	{
		return userRepository.findByFirstNameOrLastNameOrUserNumber(firstName, lastName, userNumber);
	}

	public Collection<User> findByTeam(Long id)
	{
		return userRepository.findByTeamId(id);
	}

	public Page<User> findAll(int page, int size)
	{
		return userRepository.findAll(new PageRequest(page, size));
	}
}

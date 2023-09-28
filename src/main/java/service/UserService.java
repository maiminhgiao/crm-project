package service;

import java.util.List;

import entity.Job;
import entity.User;
import repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();

	public List<User> getListUsers() {
		return userRepository.getListUsers();
	}

	public User getUser(int id) {
		return userRepository.getUsers(id);
	}

	public boolean addUser(User user) {
		int n = user.getFullName().lastIndexOf(" ");
		String fulfName = user.getFullName();
		String email = user.getEmail();
		String lastName = (n > 0) ? fulfName.substring(0, fulfName.indexOf(" ")) : fulfName;
		String firstName = (n > 0) ? fulfName.substring(fulfName.lastIndexOf(" ")) : "";

		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setUserName(email.substring(0, email.indexOf("@")));
		return userRepository.addUser(user) > 0;
	}

	public boolean deleteUser(int id) {
		return userRepository.deleteUser(id) > 0;
	}

	public boolean updateUser(User user) {
		int n = user.getFullName().lastIndexOf(" ");
		String fulfName = user.getFullName();
		String email = user.getEmail();
		String lastName = (n > 0) ? fulfName.substring(0, fulfName.indexOf(" ")) : fulfName;
		String firstName = (n > 0) ? fulfName.substring(fulfName.lastIndexOf(" ")) : "";

		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setUserName(email.substring(0, email.indexOf("@")));
		return userRepository.updateUser(user) > 0;
	}
}

package service;

import repository.LoginRepository;

public class LoginService {
	private LoginRepository loginRepository =  new LoginRepository();
	public boolean getLogin(String email, String password) {
		return loginRepository.getLogin(email, password);
	}
}

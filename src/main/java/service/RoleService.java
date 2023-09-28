package service;

import java.util.List;

import entity.Role;
import repository.RoleRepository;

public class RoleService {
	private RoleRepository repository = new RoleRepository();
	public List<Role> getListRole() {
		return repository.getListRole();
	}
	public Role getRole(int id) {
		return repository.getRole(id);
	}
	public boolean addRole(Role role) {
		return repository.addRole(role) > 0 ;
	}
	public boolean deleteRole(int id) {
		return repository.deleteRole(id) > 0;
	}
	public boolean updateRole(Role role) {
		return repository.updateRole(role) > 0;
	}
}

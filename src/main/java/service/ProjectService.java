package service;

import java.util.List;

import entity.Project;
import repository.ProjectRepository;

public class ProjectService {
	private ProjectRepository projectRepository = new ProjectRepository();

	public List<Project> getListProject() {
		return projectRepository.getListProject();
	}
	public Project getProject(int id) {
		return projectRepository.getProject(id);
	}
	public boolean updateProject(Project project) {
		return projectRepository.updateProject(project) > 0;
	}

	public boolean addProject(Project project) {
		return projectRepository.addProject(project) > 0;
	}

	public boolean deleteProject(int id) {
		return projectRepository.deleteProject(id) > 0;
	}
	

}

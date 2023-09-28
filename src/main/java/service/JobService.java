package service;

import java.util.List;

import entity.Job;
import repository.JobRepository;

public class JobService {
	private JobRepository jobRepository = new JobRepository();

	public List<Job> getListJob() {
		return jobRepository.getListJob();
	}

	public Job getJob(int id) {
		return jobRepository.getJob(id);
	}

	public boolean addJob(Job job) {
		return jobRepository.addJob(job) != -1;
	}

	public boolean deleteJob(int id) {
		return jobRepository.deleteJob(id) > 0;
	}

	public boolean updateJob(Job job) {
		return jobRepository.updateJob(job) != -1;
	}
}

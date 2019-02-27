package com.ptc.cloud.jobmanagermicroservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class JobMicroServiceController {
	@Autowired
	private JobInfoRepository service;
	
	@Autowired 
	private JmsTemplate jmsTemplate;

	/* API to get all persistable jobs */
	@GetMapping("/jobs/getalljobs")
	public List<JobInfo> getAllJobs() {
		return service.findAll();
	}

	/* API to get persistable job based on job ID*/
	@GetMapping("/jobs/getjobs/{id}")
	public Resource<JobInfo> retrieveJob(@PathVariable int id) {
		
		Optional<JobInfo> jobInfo = service.findById(id);
		
		if (!jobInfo.isPresent())
			throw new JobNotFoundException("id-" + id);
		
		//HATEOAS to all jobs.
		Resource<JobInfo> resource = new Resource<JobInfo>(jobInfo.get());
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getAllJobs());
		
		resource.add(linkTo.withRel("all-jobs"));
		
		return resource;
	}
	
	/* API to create persistable job*/
	@PostMapping("/jobs/createjob")
	public ResponseEntity<Object> createJob(@RequestBody JobInfo job) {
		job.setStatus("NEW");
		JobInfo savedJob = service.save(job);
		//Create new resource URI.
		URI location = ServletUriComponentsBuilder.
				fromCurrentRequest().
				replacePath("/jobs/getjobs//{id}").
				buildAndExpand(savedJob.getJobId()).
				toUri();
		
		System.out.println("#### Sending a Job to the Queue...");
		// Post message to the message queue named "JobTransactionQueue"
		jmsTemplate.convertAndSend("JobTransactionQueue", savedJob/*savedJob.getJobId()*/);
		
		
		//Return correct HTTP response with code and URI.
		return ResponseEntity.created(location).build();

	}
	
	/* API to delete persistable job based on job ID*/
	@DeleteMapping("/jobs/removejob/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);

	}

}

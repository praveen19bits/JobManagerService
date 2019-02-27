package com.ptc.cloud.jobmanagermicroservice;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JobReceiver {
	
	@JmsListener(destination = "JobTransactionQueue"/*, containerFactory = "myFactory"*/)
	public void receiveMessage(JobInfo job) {
		System.out.println("Received Job " + job);
	}

}

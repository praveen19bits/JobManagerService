package com.ptc.cloud.jobmanagermicroservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JobInfo {

	@Id
	@GeneratedValue
	private Integer	jobId;
	private String	ShapeType;
	private String	configVersion;
	private String	adapterVersion;
	private String	jobPriority;
	private String	jobSize;
	private String	inputStorageLocation;
	private String	outputStorageLocation;
	private String	additionaJobInputs;
	private String	status;
	
	public JobInfo(){
		
	}

	public JobInfo(Integer jobId, String shapeType, String configVersion, String adapterVersion, String jobPriority,
			String jobSize, String inputStorageLocation, String outputStorageLocation, String additionaJobInputs) {
		super();
		this.jobId = jobId;
		this.ShapeType = shapeType;
		this.configVersion = configVersion;
		this.adapterVersion = adapterVersion;
		this.jobPriority = jobPriority;
		this.jobSize = jobSize;
		this.inputStorageLocation = inputStorageLocation;
		this.outputStorageLocation = outputStorageLocation;
		this.additionaJobInputs = additionaJobInputs;
		this.status = status;
	}
	
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
	public String getShapeType() {
		return ShapeType;
	}
	public void setShapeType(String shapeType) {
		ShapeType = shapeType;
	}
	
	public String getConfigVersion() {
		return configVersion;
	}
	public void setConfigVersion(String configVersion) {
		this.configVersion = configVersion;
	}
	
	public String getAdapterVersion() {
		return adapterVersion;
	}
	public void setAdapterVersion(String adapterVersion) {
		this.adapterVersion = adapterVersion;
	}
	
	public String getJobPriority() {
		return jobPriority;
	}
	public void setJobPriority(String jobPriority) {
		this.jobPriority = jobPriority;
	}
	
	public String getJobSize() {
		return jobSize;
	}
	public void setJobSize(String jobSize) {
		this.jobSize = jobSize;
	}
	
	public String getInputStorageLocation() {
		return inputStorageLocation;
	}
	public void setInputStorageLocation(String inputStorageLocation) {
		this.inputStorageLocation = inputStorageLocation;
	}
	
	public String getOutputStorageLocation() {
		return outputStorageLocation;
	}
	public void setOutputStorageLocation(String outputStorageLocation) {
		this.outputStorageLocation = outputStorageLocation;
	}
	
	public String getAdditionaJobInputs() {
		return additionaJobInputs;
	}
	public void setAdditionaJobInputs(String additionaJobInputs) {
		this.additionaJobInputs = additionaJobInputs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "JobInfo [jobId=" + jobId + ", ShapeType=" + ShapeType + ", configVersion=" + configVersion
				+ ", adapterVersion=" + adapterVersion + ", jobPriority=" + jobPriority + ", jobSize=" + jobSize
				+ ", inputStorageLocation=" + inputStorageLocation + ", outputStorageLocation=" + outputStorageLocation
				+ ", additionaJobInputs=" + additionaJobInputs + ", status=" + status + "]";
	}
	
	
}

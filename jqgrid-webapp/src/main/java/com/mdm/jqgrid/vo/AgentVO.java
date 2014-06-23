package com.mdm.jqgrid.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * Agent정보를 담는 Value Object
 * @author developer
 *
 */
public class AgentVO {

	/**
	 * Agent 고유ID
	 */
	private String agentId;
	/**
	 * 해당 Agent가 심겨진 기관의 조직코드
	 */
	private String orgCode;
	/**
	 * 해당 Agent가 동작되는 환경의 OS
	 * Windows, Unix(Linux포함)
	 */
	private String operatingSystem;
	/**
	 * 해당 Agent가 동작되는 환경의 Charset
	 */
	private String charset;
	/**
	 * 해당 Agent에 할당된 webservice 유저ID
	 */
	private String websvcUser;
	/**
	 * 해당 Agent에 할당된 webservice 패스워드
	 */
	private String websvcPass;
	/**
	 * 담당자명
	 */
	private String officerName;
	/**
	 * 담당자 연락처
	 */
	private String officerContact;
	
	/**
	 * SMS 사용시 전화번호
	 */
	private String smsCellNo;
	/**
	 * SMS 사용여부
	 */
	private String smsUseYn;
	
	/**
	 * Schedule Type : Local, OS
	 */
	private String scheduleType;
	
	/**
	 * 데이터 생성일자
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
	/**
	 * 데이터 수정일자
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifiedDate;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String clientId) {
		this.agentId = clientId;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getWebsvcUser() {
		return websvcUser;
	}
	public void setWebsvcUser(String websvcUser) {
		this.websvcUser = websvcUser;
	}
	public String getWebsvcPass() {
		return websvcPass;
	}
	public void setWebsvcPass(String websvcPass) {
		this.websvcPass = websvcPass;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getOfficerContact() {
		return officerContact;
	}
	public void setOfficerContact(String officerContact) {
		this.officerContact = officerContact;
	}
	public String getSmsCellNo() {
		return smsCellNo;
	}
	public void setSmsCellNo(String smsCellNo) {
		this.smsCellNo = smsCellNo;
	}
	public String getSmsUseYn() {
		return smsUseYn;
	}
	public void setSmsUseYn(String smsUseYn) {
		this.smsUseYn = smsUseYn;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}

package com.mdm.jqgrid.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * <pre>
 * <p>agent가 처리하는 작업을 설정한 정보</p>
 * 
 * ※하나의 Agent는 복수개의 Job을 가진다.
 * </pre>
 * @author developer
 *
 */
public class JobVO {

	/**
	 * Job 고유 ID
	 */
	private String jobId;
	/**
	 * Job을 소유한 Agent ID
	 */
	private String agentId;
	/**
	 * Job명. 관리자에게 Job의 구분을 위한 가독성을 제공함.
	 */
	private String jobName;
	/**
	 * 작업구분(서버/에이전트). 사용안함
	 * 에이전트로부터 수신받은 데이터를 처리하는 작업이 세부화될 경우 사용할 목적.
	 */
	private String jobType;
	/**
	 * 스케줄러에 의해 Agent가 본 작업을 실행할 시각
	 */
	private String execTime;

	/**
	 * <pre>
	 * Agent의 업무처리에서의 메인 SQL
	 * 
	 * Select 구문만 가능함.
	 * </pre>
	 */
	private String sqlMain;
	/**
	 * <pre>
	 * Agent의 업무처리에서 메인SQL 실행 이전에 실행될 SQL
	 * </pre>
	 */
	private String sqlPre;
	/**
	 * Agent의 업무처리에서 메인SQL 실행 이후에 실행될 SQL
	 */
	private String sqlPost;

	/**
	 * 작업시 Agent가 접속할 DB의 jdbc driver class명
	 */
	private String jdbcDriverClassName;
	/**
	 * 작업시 Agent가 접속할 DB의 URL
	 */
	private String jdbcUrl;
	/**
	 * 작업시 Agent가 접속할 DB의 User명
	 */
	private String jdbcUsername;
	/**
	 * 작업시 Agent가 접속할 DB의 패스워드
	 */
	private String jdbcPassword;
	
	/**
	 * 작업시 MAIN SQL로 한번에 취득할 레코드 갯수
	 */
	private int batchSelectCount;

	/**
	 * Agent로부터 받은 업무데이터를 서버에서 처리하는 SQL
	 */
	private String serverSql;
	
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
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String clientId) {
		this.agentId = clientId;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getExecTime() {
		return execTime;
	}
	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getJdbcUsername() {
		return jdbcUsername;
	}
	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}
	public String getJdbcPassword() {
		return jdbcPassword;
	}
	public void setJdbcPassword(String jdbcPass) {
		this.jdbcPassword = jdbcPass;
	}
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}
	public void setJdbcDriverClassName(String jdbcDriverClass) {
		this.jdbcDriverClassName = jdbcDriverClass;
	}
	public String getSqlMain() {
		return sqlMain;
	}
	public void setSqlMain(String sqlMain) {
		this.sqlMain = sqlMain;
	}
	public String getSqlPre() {
		return sqlPre;
	}
	public void setSqlPre(String sqlPre) {
		this.sqlPre = sqlPre;
	}
	public String getSqlPost() {
		return sqlPost;
	}
	public void setSqlPost(String sqlPost) {
		this.sqlPost = sqlPost;
	}
	public String getServerSql() {
		return serverSql;
	}
	public void setServerSql(String serverSql) {
		this.serverSql = serverSql;
	}
	public int getBatchSelectCount() {
		return batchSelectCount;
	}
	public void setBatchSelectCount(int batchSelectCount) {
		this.batchSelectCount = batchSelectCount;
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

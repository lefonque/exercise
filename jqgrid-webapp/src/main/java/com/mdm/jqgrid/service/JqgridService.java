package com.mdm.jqgrid.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mdm.jqgrid.dao.JqgridDAO;
import com.mdm.jqgrid.vo.AgentVO;
import com.mdm.jqgrid.vo.JQGridVO;
import com.mdm.jqgrid.vo.JobVO;

@Service
public class JqgridService {

	private final Logger logger = LoggerFactory.getLogger(JqgridService.class);
	
	@Autowired
	private JqgridDAO dao;
	
	/**
	 * agent id 에 해당하는 Agent 정보를 가져온다.
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	public AgentVO getAgentInfo(String agentId) throws Exception{
		
		AgentVO result = dao.selectAgentInfo(agentId);
		logger.debug("Get {}'s client info",agentId);
		
		return result;
	}
	
	/**
	 * 전체 Agent 갯수를 가져온다.
	 * @return
	 */
	public int getAgentCount(){
		int result = dao.selectAgentCount();
		return result;
	}
	
	/**
	 * 페이징정보에 맞게 Agent 목록을 가져온다.
	 * @param paging
	 * @return
	 */
	public List<AgentVO> getAgentList(JQGridVO paging){
		
		List<AgentVO> result = dao.selectAgentList(paging);
		return result;
	}
	
	/**
	 * 화면에서 추가한 Agent 정보를 반영한다.
	 * @param agent
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public int addAgent(AgentVO agent){
		int result = dao.insertAgent(agent);
		return result;
	}
	
	/**
	 * 화면에서 수정된 Agent 정보를 반영한다.
	 * @param agent
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public int modifyAgent(AgentVO agent){
		int result = dao.updateAgent(agent);
		return result;
	}
	
	/**
	 * client id 목록에 대항하는 Agent 정보를 삭제한다.
	 * @param agentIDs
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public int removeAgent(String[] agentIDs){
		int result = 0;
		for(String agentID : agentIDs){
			result += dao.deleteAgent(agentID);
			result += dao.deleteJobByClientId(agentID);
		}
		return result;
	}

	/**
	 * agent id에 해당하는 Job목록 갯수를 가져온다.
	 * @param agentId
	 * @return
	 */
	public int getJobCount(String agentId){
		int result = dao.selectJobCount(agentId);
		return result;
	}
	
	/**
	 * agent id에 해당하는 Job목록을 가져온다.
	 * @param agentId
	 * @return
	 */
	public List<JobVO> getJobList(JQGridVO searchParam, String agentId){
		List<JobVO> result = dao.selectJobList(searchParam, agentId);
		return result;
	}
	
	/**
	 * <pre>
	 * <p>Job ID에 해당하는 Schedule 정보를 가져온다.</p>
	 * 
	 * Agent에서 webservice로 보내온 데이터를 처리할 SQL을 취득할 때 사용함
	 * </pre>
	 * @param agentId
	 * @param jobId
	 * @return
	 */
	public JobVO getJobInfo(String agentId, String jobId){
		JobVO result = dao.selectJobInfo(agentId, jobId);
		return result;
	}
	
	/**
	 * 화면에서 추가한 Schedule정보를 반영한다.
	 * @param job
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public int addJob(JobVO job){
		int result = dao.insertJob(job);
		return result;
	}
	
	/**
	 * 화면에서 수정된 Schedule정보를 반영한다.
	 * @param job
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public int modifyJob(JobVO job){
		int result = dao.updateSchedule(job);
		return result;
	}
	
	/**
	 * 지정된 Job Id목록에 해당하는 Job정보를 삭제한다.
	 * @param jobIds
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public int removeJobById(String[] jobIds){
		int result = 0;
		for(String jobId : jobIds){
			result += dao.deleteJob(jobId);
		}
		return result;
	}
}

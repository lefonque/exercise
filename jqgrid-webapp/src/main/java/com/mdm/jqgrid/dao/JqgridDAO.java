package com.mdm.jqgrid.dao;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.mdm.jqgrid.vo.AgentVO;
import com.mdm.jqgrid.vo.JQGridVO;
import com.mdm.jqgrid.vo.JobVO;

@Repository
public class JqgridDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(JqgridDAO.class);
	
	/**
	 * Select된 결과를 AgentVO에 담기위한 RowMapper
	 */
	private final RowMapper<AgentVO> agentRowMapper;
	/**
	 * Select된 결과를 JobVO에 담기위한 RowMapper
	 */
	private final RowMapper<JobVO> jobRowMapper;
	
	public JqgridDAO(){
		jobRowMapper = new BeanPropertyRowMapper<JobVO>(JobVO.class);
		agentRowMapper = new BeanPropertyRowMapper<AgentVO>(AgentVO.class);
	}
	
	private NamedParameterJdbcTemplate paramJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.paramJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}
	
	@Autowired
	@Qualifier("sqlProp")
	private Properties sqlRepo;

	/**
	 * Agent정보를 취득하는 메서드
	 * @param agentId
	 * @return
	 * @throws Exception
	 */
	public AgentVO selectAgentInfo(String agentId) throws Exception {
		String sql = sqlRepo.getProperty("select.config.agent.one");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("agentId", agentId);
		
		logger.trace("SQL : [{}]",sql);
		logger.trace("PARAMETERS : [{}]",agentId);
		
		AgentVO result = null;
		try {
			result = paramJdbcTemplate.queryForObject(
					sql, paramSource, agentRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.info("No Data Found in TBL_CONFIG by Agent ID : [{}]",agentId);
		}
		
		return result;
	}
	
	/**
	 * <pre>
	 * <p>총 Agent 갯수를 구하는 메서드</p>
	 * 
	 * Agent 목록표시 Grid 부분의 Paging을 위하여 총 record수를 구하기 위해 사용됨.
	 * Agent 목록표시시 조건 검색은 없음
	 * </pre>
	 * @return
	 */
	public int selectAgentCount(){
		String sql = sqlRepo.getProperty("select.config.agent.count");
		
		logger.trace("SQL : {}",sql);
		
		int result = paramJdbcTemplate.queryForObject(sql,(SqlParameterSource)null,Integer.class);
		return result;
	}
	
	public List<AgentVO> selectAgentList(JQGridVO paging){
		
		String sql = sqlRepo.getProperty("select.config.agent.list");
		sql = String.format(sql,paging.getSidx(),paging.getSord());
		
//		long startIndex = ((searchParam.getPage()-1) * searchParam.getRows()) + 1;
		long startIndex = ((paging.getPage() * paging.getRows()) - paging.getRows()) + 1;
		long endIndex = (paging.getPage() * paging.getRows());

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("startIndex", startIndex);
		paramSource.addValue("endIndex", endIndex);
		
		logger.trace("SQL : {}",sql);
		logger.trace("PARAMETERS : {},{}",new Object[]{startIndex,endIndex});
		
		List<AgentVO> result = paramJdbcTemplate.query(
				sql, paramSource, agentRowMapper);
		return result;
	}
	

	/**
	 * Agent정보를 테이블에 insert한다.
	 * @param config
	 * @return
	 */
	public int insertAgent(AgentVO config){
		
		String sql = sqlRepo.getProperty("insert.config.agent");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(config);
		
		int result = paramJdbcTemplate.update(sql, paramSource);
		return result;
	}
	
	/**
	 * Agent정보를 테이블에 update한다.
	 * @param config
	 * @return
	 */
	public int updateAgent(AgentVO config){

		String sql = sqlRepo.getProperty("update.config.agent");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(config);
		
		int result = paramJdbcTemplate.update(sql, paramSource);
		return result;
	}
	
	/**
	 * Agent정보를 테이블에서 삭제한다.
	 * @param agentId
	 * @return
	 */
	public int deleteAgent(String agentId){
		
		String sql = sqlRepo.getProperty("delete.config.agent");
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("agentId",agentId);
		
		int result = paramJdbcTemplate.update(sql, paramSource);
		return result;
	}

	
	/**
	 * 작업설정정보를 조회한다.
	 * @param agentId
	 * @param jobId
	 * @return
	 */
	public JobVO selectJobInfo(String agentId, String jobId){
		String sql = sqlRepo.getProperty("select.config.job.one");
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("agentId", agentId);
		paramSource.addValue("jobId", jobId);

		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",paramSource);
		
		JobVO result = paramJdbcTemplate.queryForObject(sql, paramSource, jobRowMapper);

		return result;
	}
	
	/**
	 * <pre>
	 * <p>총 Job 갯수를 구하는 메서드</p>
	 * 
	 * Job 목록표시 Grid 부분의 Paging을 위하여 총 record수를 구하기 위해 사용됨.
	 * Job 목록표시시 조건 검색은 없음
	 * </pre>
	 * @param agentId
	 * @return
	 */
	public int selectJobCount(String agentId){
		String sql = sqlRepo.getProperty("select.config.job.count");
		MapSqlParameterSource paramSource = new MapSqlParameterSource("agentId",agentId);
		
		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",agentId);
		
		int result = paramJdbcTemplate.queryForObject(sql, paramSource,Integer.class);
		
		return result;
	}
	
	/**
	 * <pre>
	 * <p>지정된 Agent ID에 소속된 모든 Job의 레코드를 구하는 메서드</p>
	 * 
	 * 현재 사용안함
	 * </pre>
	 * @param agentId
	 * @return
	 */
	public List<JobVO> selectJobList(String agentId){
		MapSqlParameterSource paramSource = new MapSqlParameterSource("agentId",agentId);
		
		String sql = sqlRepo.getProperty("select.config.job.list");
		
		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",paramSource.getValues());
		
		List<JobVO> result = paramJdbcTemplate.query(
				sql, paramSource, jobRowMapper);
		return result;
	}
	
	/**
	 * <pre>
	 * <p>페이징된 양 만큼 Job의 레코드를 구하는 메서드</p>
	 * 
	 * JQGridVO 에 담긴 page번호와 페이지당 표시될 row갯수를 이용하여 페이징을 한다.
	 * </pre>
	 * @param searchParam
	 * @param agentId
	 * @return
	 */
	public List<JobVO> selectJobList(JQGridVO searchParam, String agentId){
		
		long startIndex = ((searchParam.getPage() * searchParam.getRows()) - searchParam.getRows()) + 1;
		long endIndex = (searchParam.getPage() * searchParam.getRows());

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("startIndex", startIndex);
		paramSource.addValue("endIndex", endIndex);
		paramSource.addValue("agentId",agentId);
		
		String sql = sqlRepo.getProperty("select.config.job.list.paging");
		sql = String.format(sql,searchParam.getSidx(),searchParam.getSord());
		
		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",paramSource.getValues());
		
		List<JobVO> result = paramJdbcTemplate.query(
				sql, paramSource, jobRowMapper);
		return result;
	}
	
	/**
	 * Job정보를 테이블에 insert한다.
	 * @param job
	 * @return
	 */
	public int insertJob(JobVO job){
		String sql = sqlRepo.getProperty("insert.config.job");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(job);

		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",job);
		
		int result = paramJdbcTemplate.update(sql, paramSource);
		return result;
	}
	
	/**
	 * Job정보를 테이블에 update한다.
	 * @param job
	 * @return
	 */
	public int updateSchedule(JobVO job){
		String sql = sqlRepo.getProperty("update.config.job");
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(job);

		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",job);
		
		int result = paramJdbcTemplate.update(sql, paramSource);
		return result;
	}
	
	/**
	 * Job정보를 테이블에서 삭제한다.
	 * @param jobId
	 * @return
	 */
	public int deleteJob(String jobId){
		String sql = sqlRepo.getProperty("delete.config.job");
		MapSqlParameterSource paramSource = new MapSqlParameterSource("jobId",jobId);

		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",jobId);
		
		int result = paramJdbcTemplate.update(sql, paramSource);
		return result;
	}
	
	/**
	 * 지정된 Agent에 소속된 모든 Job정보를 테이블에서 삭제한다.
	 * @param agentId
	 * @return
	 */
	public int deleteJobByClientId(String agentId){
		String sql = sqlRepo.getProperty("delete.config.job.byclientid");
		MapSqlParameterSource paramSource = new MapSqlParameterSource("agentId",agentId);

		logger.debug("SQL : {}",sql);
		logger.debug("PARAMETERS : {}",agentId);
		
		int result = paramJdbcTemplate.update(sql, paramSource);
		return result;
	}
}

package com.jpl.garage.example.springmvc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpl.garage.example.springmvc.dao.example.BbsDataDao;
import com.jpl.garage.example.springmvc.vo.table.BbsDataBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/spring/ex-*.xml")
public class TestRun {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BbsDataDao dataDao;
	
	@Test
	public void testCase(){
		
		BbsDataBean param = new BbsDataBean();
		param.setBbsId("1");
//		BbsDataBean result = dataDao.getBbsDataItem(param);
		BbsDataBean result = dataDao.getBbsDataItem2(param);
		logger.debug("data : {}",result.bbsInfoBean.getBbsName());
	}
}

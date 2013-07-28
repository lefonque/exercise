package com.jpl.garage.example.springmvc.service.example;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpl.garage.example.springmvc.dao.example.BbsDataDao;
import com.jpl.garage.example.springmvc.vo.table.BbsDataBean;

@Service
public class BulletinService {
	
	@Autowired
	private BbsDataDao dao;
	
	public BbsDataBean getBulletinInfo(BbsDataBean param){
		BbsDataBean result = dao.getBbsDataItem2(param);
		return result;
	}
	
	public List<BbsDataBean> getBulletinList(){
		List<BbsDataBean> result = dao.getBbsDataList();
		return result;
	}
	
	public int addBbsData(BbsDataBean bulletin){
		Object obj = dao.insertBbsData(bulletin);
		int result = Integer.class.cast(obj).intValue();
		return result;
	}
}

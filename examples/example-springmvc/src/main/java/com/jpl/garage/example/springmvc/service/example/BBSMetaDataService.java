package com.jpl.garage.example.springmvc.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpl.garage.example.springmvc.dao.example.BbsInfoDao;

@Service
public class BBSMetaDataService {
	
	@Autowired
	private BbsInfoDao metaDao;
}

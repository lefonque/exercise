package com.jpl.garage.cxf.obsp;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.sql.DataSource;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@org.springframework.stereotype.Service
public class ObspInfoImpl implements ObspInfo {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	@WebResult(name = "return", targetNamespace = "")
	@RequestWrapper(localName = "service", targetNamespace = "http://www.cxf.garage.jpl.com/Obsp/", className = "com.jpl.garage.cxf.obsp.Service")
	@WebMethod(action = "http://www.cxf.garage.jpl.com/Obsp/service")
	@ResponseWrapper(localName = "serviceResponse", targetNamespace = "http://www.cxf.garage.jpl.com/Obsp/", className = "com.jpl.garage.cxf.obsp.ServiceResponse")
	public String service(
			@WebParam(name = "arg0", targetNamespace = "") String arg0,
			@WebParam(name = "arg1", targetNamespace = "") String arg1) {
		System.out.println("########################################");
		System.out.println("arg0 : [" + arg0 + "]");
		System.out.println("arg1 : [" + arg1 + "]");
		
		List<?> resultList = jdbcTemplate.queryForList("select sysdate as DT from dual");
		String result = null;
		if(resultList.size() > 0){
			result = "레코드 ::::" + resultList.get(0).toString();
		}
		else{
			result = "레코드 갯수는 0";
		}
		System.out.println("result : [" + result + "]");
		System.out.println("########################################");
		
		return result;
	}

}

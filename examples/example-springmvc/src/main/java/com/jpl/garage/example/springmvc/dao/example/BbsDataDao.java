/*
 * Entity Generator
 *
 * Make By Jin Young Sug 
 */
package com.jpl.garage.example.springmvc.dao.example;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpl.garage.example.springmvc.vo.table.BbsDataBean;

/**
 *  테이블 처리 DAO 
 *
 * @author 홍길동
 * @create 2011.11.01 
 */
@Repository
public interface BbsDataDao {

        /**
     * bbs_data 의 목록 조회
     *
     * @return BbsDataBean 의 list 
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
	public List<BbsDataBean> getBbsDataList ();

    /**
     * bbs_data 의 상세 내역 조회
     *
     * @return BbsDataBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public BbsDataBean getBbsDataItem (BbsDataBean bean);

    public BbsDataBean getBbsDataItem2 (BbsDataBean bean);
    
	/**
     * bbs_data 에 신규 내용 저장
     *
     * @param BbsDataBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */    
    public Object insertBbsData (BbsDataBean bean);
    
    /**
     * bbs_data 목록 중 입력된 BbsDataBean 에 해당하는 내용을 수정 
     *
     * @param BbsDataBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int updateBbsData (BbsDataBean bean);
    
    /**
     * bbs_data 목록 중 입력된 BbsDataBean 에 해당하는 내용을 삭제
     *
     * @param BbsDataBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int deleteBbsData (BbsDataBean bean);
	}
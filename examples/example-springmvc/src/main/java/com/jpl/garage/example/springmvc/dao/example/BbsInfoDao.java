/*
 * Entity Generator
 *
 * Make By Jin Young Sug 
 */
package com.jpl.garage.example.springmvc.dao.example;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpl.garage.example.springmvc.vo.table.BbsInfoBean;

/**
 *  테이블 처리 DAO 
 *
 * @author 홍길동
 * @create 2011.11.01 
 */
@Repository
public interface BbsInfoDao {

        /**
     * bbs_info 의 목록 조회
     *
     * @return BbsInfoBean 의 list 
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
	public List<BbsInfoBean> getBbsInfoList ();

    /**
     * bbs_info 의 상세 내역 조회
     *
     * @return BbsInfoBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public BbsInfoBean getBbsInfoItem (BbsInfoBean bean);

	/**
     * bbs_info 에 신규 내용 저장
     *
     * @param BbsInfoBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */    
    public Object insertBbsInfo (BbsInfoBean bean);
    
    /**
     * bbs_info 목록 중 입력된 BbsInfoBean 에 해당하는 내용을 수정 
     *
     * @param BbsInfoBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int updateBbsInfo (BbsInfoBean bean);
    
    /**
     * bbs_info 목록 중 입력된 BbsInfoBean 에 해당하는 내용을 삭제
     *
     * @param BbsInfoBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int deleteBbsInfo (BbsInfoBean bean);
	}
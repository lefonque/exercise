/*
 * Entity Generator
 *
 * Make By Jin Young Sug 
 */
package com.jpl.garage.example.springmvc.dao.example;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpl.garage.example.springmvc.vo.table.BbsFileBean;

/**
 *  테이블 처리 DAO 
 *
 * @author 홍길동
 * @create 2011.11.01 
 */
@Repository
public interface BbsFileDao {

        /**
     * bbs_file 의 목록 조회
     *
     * @return BbsFileBean 의 list 
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
	public List<BbsFileBean> getBbsFileList ();

    /**
     * bbs_file 의 상세 내역 조회
     *
     * @return BbsFileBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public BbsFileBean getBbsFileItem (BbsFileBean bean);

	/**
     * bbs_file 에 신규 내용 저장
     *
     * @param BbsFileBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */    
    public Object insertBbsFile (BbsFileBean bean);
    
    /**
     * bbs_file 목록 중 입력된 BbsFileBean 에 해당하는 내용을 수정 
     *
     * @param BbsFileBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int updateBbsFile (BbsFileBean bean);
    
    /**
     * bbs_file 목록 중 입력된 BbsFileBean 에 해당하는 내용을 삭제
     *
     * @param BbsFileBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int deleteBbsFile (BbsFileBean bean);
	}
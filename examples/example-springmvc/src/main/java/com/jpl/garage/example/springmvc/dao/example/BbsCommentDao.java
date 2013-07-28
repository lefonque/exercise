package com.jpl.garage.example.springmvc.dao.example;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpl.garage.example.springmvc.vo.table.BbsCommentBean;

@Repository
public interface BbsCommentDao {
    /**
     * bbs_comment 의 목록 조회
     *
     * @return BbsCommentBean 의 list 
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
	public List<BbsCommentBean> getBbsCommentList ();

    /**
     * bbs_comment 의 상세 내역 조회
     *
     * @return BbsCommentBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public BbsCommentBean getBbsCommentItem (BbsCommentBean bean);

	/**
     * bbs_comment 에 신규 내용 저장
     *
     * @param BbsCommentBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */    
    public Object insertBbsComment (BbsCommentBean bean);
    
    /**
     * bbs_comment 목록 중 입력된 BbsCommentBean 에 해당하는 내용을 수정 
     *
     * @param BbsCommentBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int updateBbsComment (BbsCommentBean bean);
    
    /**
     * bbs_comment 목록 중 입력된 BbsCommentBean 에 해당하는 내용을 삭제
     *
     * @param BbsCommentBean
     *
     * @author 홍길동
     * @create 2011.11.01 
     */
    public int deleteBbsComment (BbsCommentBean bean);
}

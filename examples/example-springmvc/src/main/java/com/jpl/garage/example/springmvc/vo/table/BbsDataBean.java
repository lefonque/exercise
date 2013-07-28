/*
 * Entity Generator
 *
 * Copyright 2004~2009 OpenSourcePartner All rights reserved 
 */
package com.jpl.garage.example.springmvc.vo.table;

import java.util.List;


/**
 *  게시물 정보
 *
 * @author 진영석 진영석
 * @create 2011.10.30
 */
public class BbsDataBean {
	

	/**
	 * 게시판 정보
	 */
	public BbsInfoBean bbsInfoBean;
	
	/**
	 * 게시물 첨부파일 목록
	 */
	public List<BbsFileBean> bbsFileBeans;
	
	/**
	 * 게시물 덧글 목록
	 */
	public List<BbsCommentBean> bbsCommentBeans;
	

    /**
     * bbs_data.S_BBS_ID
     * defaultValue = ''
     * 
     * 게시판 코드
     */
    private String bbsId = "";
    
    /**
     * bbs_data.I_BBS_IDX
     *
     * 
     * 게시물 번호
     */
    private int bbsIdx;
    
    /**
     * bbs_data.I_PARENT_IDX
     *
     * 
     * 부모 게시물 번호
     */
    private int parentIdx;
    
    /**
     * bbs_data.I_IDX_LEFT
     *
     * 
     * 
     */
    private int idxLeft;
    
    /**
     * bbs_data.I_IDX_RIGHT
     *
     * 
     * 
     */
    private int idxRight;
    
    /**
     * bbs_data.S_BBS_TITLE
     *
     * 
     * 게시물 제목
     */
    private String bbsTitle;
    
    /**
     * bbs_data.S_BBS_DESC
     *
     * 
     * 게시물 내용
     */
    private String bbsDesc;
    
    /**
     * bbs_data.S_BBS_ID
     * 
     * @return    게시판 코드 
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public String getBbsId() {
        return this.bbsId == null ? "" : this.bbsId;
    }
    
    /**
     * bbs_data.S_BBS_ID
     * 
     * @param bbsId    게시판 코드
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }    
    /**
     * bbs_data.I_BBS_IDX
     * 
     * @return    게시물 번호 
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public int getBbsIdx() {
        return this.bbsIdx;
    }
    
    /**
     * bbs_data.I_BBS_IDX
     * 
     * @param bbsIdx    게시물 번호
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public void setBbsIdx(int bbsIdx) {
        this.bbsIdx = bbsIdx;
    }    
    /**
     * bbs_data.I_PARENT_IDX
     * 
     * @return    부모 게시물 번호 
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public int getParentIdx() {
        return this.parentIdx;
    }
    
    /**
     * bbs_data.I_PARENT_IDX
     * 
     * @param parentIdx    부모 게시물 번호
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public void setParentIdx(int parentIdx) {
        this.parentIdx = parentIdx;
    }    
    /**
     * bbs_data.I_IDX_LEFT
     * 
     * @return     
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public int getIdxLeft() {
        return this.idxLeft;
    }
    
    /**
     * bbs_data.I_IDX_LEFT
     * 
     * @param idxLeft    
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public void setIdxLeft(int idxLeft) {
        this.idxLeft = idxLeft;
    }    
    /**
     * bbs_data.I_IDX_RIGHT
     * 
     * @return     
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public int getIdxRight() {
        return this.idxRight;
    }
    
    /**
     * bbs_data.I_IDX_RIGHT
     * 
     * @param idxRight    
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public void setIdxRight(int idxRight) {
        this.idxRight = idxRight;
    }    
    /**
     * bbs_data.S_BBS_TITLE
     * 
     * @return    게시물 제목 
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public String getBbsTitle() {
        return this.bbsTitle == null ? "" : this.bbsTitle;
    }
    
    /**
     * bbs_data.S_BBS_TITLE
     * 
     * @param bbsTitle    게시물 제목
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public void setBbsTitle(String bbsTitle) {
        this.bbsTitle = bbsTitle;
    }    
    /**
     * bbs_data.S_BBS_DESC
     * 
     * @return    게시물 내용 
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public String getBbsDesc() {
        return this.bbsDesc == null ? "" : this.bbsDesc;
    }
    
    /**
     * bbs_data.S_BBS_DESC
     * 
     * @param bbsDesc    게시물 내용
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public void setBbsDesc(String bbsDesc) {
        this.bbsDesc = bbsDesc;
    }    

    /**
     * BbsDataBean 의 변수 값을 String 으로 반환함.
     * 
     * @return BbsDataBean 의 변수 값
     *
     * @author 진영석 
     * @create 2011.10.30 
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("BbsDataBean [");
        buffer.append(" bbsId = ").append(this.bbsId);
        buffer.append(" bbsIdx = ").append(this.bbsIdx);
        buffer.append(" parentIdx = ").append(this.parentIdx);
        buffer.append(" idxLeft = ").append(this.idxLeft);
        buffer.append(" idxRight = ").append(this.idxRight);
        buffer.append(" bbsTitle = ").append(this.bbsTitle);
        buffer.append(" bbsDesc = ").append(this.bbsDesc);
        buffer.append("] = ");
        buffer.append(super.toString());
        return buffer.toString();
    }
}
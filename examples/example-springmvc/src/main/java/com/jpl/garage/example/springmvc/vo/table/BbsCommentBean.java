/*
 * Entity Generator
 *
 * Copyright 2004~2009 OpenSourcePartner All rights reserved 
 */
package com.jpl.garage.example.springmvc.vo.table;


/**
 *  테이블 javaBean class
 *
 * @author 홍길동 홍길동
 * @create 2011.11.01
 */
public class BbsCommentBean {

    /**
     * bbs_comment.S_BBS_ID
     *
     * 
     * 
     */
    private String bbsId;
    
    /**
     * bbs_comment.I_BBS_IDX
     *
     * 
     * 
     */
    private int bbsIdx;
    
    /**
     * bbs_comment.I_COMMENT_IDX
     *
     * 
     * 
     */
    private int commentIdx;
    
    /**
     * bbs_comment.S_COMMENT
     *
     * 
     * 
     */
    private String comment;
    
    /**
     * bbs_comment.S_BBS_ID
     * 
     * @return     
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getBbsId() {
        return this.bbsId == null ? "" : this.bbsId;
    }
    
    /**
     * bbs_comment.S_BBS_ID
     * 
     * @param bbsId    
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }    
    /**
     * bbs_comment.I_BBS_IDX
     * 
     * @return     
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getBbsIdx() {
        return this.bbsIdx;
    }
    
    /**
     * bbs_comment.I_BBS_IDX
     * 
     * @param bbsIdx    
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setBbsIdx(int bbsIdx) {
        this.bbsIdx = bbsIdx;
    }    
    /**
     * bbs_comment.I_COMMENT_IDX
     * 
     * @return     
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getCommentIdx() {
        return this.commentIdx;
    }
    
    /**
     * bbs_comment.I_COMMENT_IDX
     * 
     * @param commentIdx    
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setCommentIdx(int commentIdx) {
        this.commentIdx = commentIdx;
    }    
    /**
     * bbs_comment.S_COMMENT
     * 
     * @return     
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getComment() {
        return this.comment == null ? "" : this.comment;
    }
    
    /**
     * bbs_comment.S_COMMENT
     * 
     * @param comment    
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setComment(String comment) {
        this.comment = comment;
    }    

    /**
     * BbsCommentBean 의 변수 값을 String 으로 반환함.
     * 
     * @return BbsCommentBean 의 변수 값
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("BbsCommentBean [");
        buffer.append(" bbsId = ").append(this.bbsId);
        buffer.append(" bbsIdx = ").append(this.bbsIdx);
        buffer.append(" commentIdx = ").append(this.commentIdx);
        buffer.append(" comment = ").append(this.comment);
        buffer.append("] = ");
        buffer.append(super.toString());
        return buffer.toString();
    }
}
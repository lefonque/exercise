/*
 * Entity Generator
 *
 * Copyright 2004~2009 OpenSourcePartner All rights reserved 
 */
package com.jpl.garage.example.springmvc.vo.table;

import java.util.Date;

/**
 *  테이블 javaBean class
 *
 * @author 홍길동 홍길동
 * @create 2011.11.01
 */
public class BbsInfoBean {

    /**
     * bbs_info.S_BBS_ID
     *
     * 
     * 게시판 ID
     */
    private String bbsId;
    
    /**
     * bbs_info.S_BBS_NAME
     *
     * 
     * 게시판 명
     */
    private String bbsName;
    
    /**
     * bbs_info.C_BBS_TYPE
     * defaultValue = 'N'
     * 
     * 게시판 유형, N : 일반, P : 사진, M : 동영상
     */
    private String bbsType = "N";
    
    /**
     * bbs_info.C_USE_YN
     * defaultValue = 'Y'
     * 
     * 사용 여부
     */
    private String useYn = "Y";
    
    /**
     * bbs_info.C_EMAIL_YN
     * defaultValue = 'N'
     * 
     * 이메일 수신 여부
     */
    private String emailYn = "N";
    
    /**
     * bbs_info.C_NOTICE_YN
     * defaultValue = 'N'
     * 
     * 공지글 사용 여부
     */
    private String noticeYn = "N";
    
    /**
     * bbs_info.I_HOT_ICON_COUNT
     * defaultValue = '0'
     * 
     * HOT icon 표시 조회수
     */
    private int hotIconCount = 0;
    
    /**
     * bbs_info.I_NEW_ICON_DAY
     * defaultValue = '0'
     * 
     * NEW ICON 표시 일자
     */
    private int newIconDay = 0;
    
    /**
     * bbs_info.C_POPUP_YN
     * defaultValue = 'N'
     * 
     * 팝업 가능 여부
     */
    private String popupYn = "N";
    
    /**
     * bbs_info.C_OPEN_YN
     * defaultValue = 'N'
     * 
     * 공개 비공개 설정 가능 여부
     */
    private String openYn = "N";
    
    /**
     * bbs_info.C_VOTE_YN
     * defaultValue = 'N'
     * 
     * 만족도 조사 가능 여부
     */
    private String voteYn = "N";
    
    /**
     * bbs_info.C_REPLY_YN
     * defaultValue = 'Y'
     * 
     * 답변 가능 여부
     */
    private String replyYn = "Y";
    
    /**
     * bbs_info.C_COMMENT_YN
     * defaultValue = 'N'
     * 
     * 덧글 가능 여부
     */
    private String commentYn = "N";
    
    /**
     * bbs_info.C_HTML_YN
     * defaultValue = 'Y'
     * 
     * HTML editor 사용 여부
     */
    private String htmlYn = "Y";
    
    /**
     * bbs_info.C_WASTE_YN
     * defaultValue = 'N'
     * 
     * 휴지통 기능 사용 여부
     */
    private String wasteYn = "N";
    
    /**
     * bbs_info.S_DATE_FORMAT
     * defaultValue = 'yyyy/MM/dd'
     * 
     * 날짜 표시 유형 년:yyyy 월:MM 일:dd 시간:hh 분:mm 초:ss
     */
    private String dateFormat = "yyyy/MM/dd";
    
    /**
     * bbs_info.I_LIST_COUNT
     * defaultValue = '10'
     * 
     * 페이지당 목록 수
     */
    private int listCount = 10;
    
    /**
     * bbs_info.I_FILE_COUNT
     * defaultValue = '10'
     * 
     * 파일 업로드 허용 갯수
     */
    private int fileCount = 10;
    
    /**
     * bbs_info.I_FILE_SIZE
     * defaultValue = '100'
     * 
     * 파일 업로드 가능 총용량
     */
    private int fileSize = 100;
    
    /**
     * bbs_info.S_HEADER
     *
     * 
     * 말머리 정보 ';' 구분 하여 여러개 등록 가능
     */
    private String header;
    
    /**
     * bbs_info.S_HEAD_DESC
     *
     * 
     * 게시판 상단 안내 문구
     */
    private String headDesc;
    
    /**
     * bbs_info.S_TAIL_DESC
     *
     * 
     * 게시판 하단 문구
     */
    private String tailDesc;
    
    /**
     * bbs_info.D_CREATE
     * defaultValue = 'CURRENT_TIMESTAMP'
     * 
     * 작성일
     */
    private Date create;
    
    /**
     * bbs_info.S_BBS_ID
     * 
     * @return    게시판 ID 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getBbsId() {
        return this.bbsId == null ? "" : this.bbsId;
    }
    
    /**
     * bbs_info.S_BBS_ID
     * 
     * @param bbsId    게시판 ID
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }    
    /**
     * bbs_info.S_BBS_NAME
     * 
     * @return    게시판 명 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getBbsName() {
        return this.bbsName == null ? "" : this.bbsName;
    }
    
    /**
     * bbs_info.S_BBS_NAME
     * 
     * @param bbsName    게시판 명
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setBbsName(String bbsName) {
        this.bbsName = bbsName;
    }    
    /**
     * bbs_info.C_BBS_TYPE
     * 
     * @return    게시판 유형, N : 일반, P : 사진, M : 동영상 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getBbsType() {
        return this.bbsType == null ? "" : this.bbsType;
    }
    
    /**
     * bbs_info.C_BBS_TYPE
     * 
     * @param bbsType    게시판 유형, N : 일반, P : 사진, M : 동영상
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setBbsType(String bbsType) {
        this.bbsType = bbsType;
    }    
    /**
     * bbs_info.C_USE_YN
     * 
     * @return    사용 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getUseYn() {
        return this.useYn == null ? "" : this.useYn;
    }
    
    /**
     * bbs_info.C_USE_YN
     * 
     * @param useYn    사용 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }    
    /**
     * bbs_info.C_EMAIL_YN
     * 
     * @return    이메일 수신 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getEmailYn() {
        return this.emailYn == null ? "" : this.emailYn;
    }
    
    /**
     * bbs_info.C_EMAIL_YN
     * 
     * @param emailYn    이메일 수신 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setEmailYn(String emailYn) {
        this.emailYn = emailYn;
    }    
    /**
     * bbs_info.C_NOTICE_YN
     * 
     * @return    공지글 사용 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getNoticeYn() {
        return this.noticeYn == null ? "" : this.noticeYn;
    }
    
    /**
     * bbs_info.C_NOTICE_YN
     * 
     * @param noticeYn    공지글 사용 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setNoticeYn(String noticeYn) {
        this.noticeYn = noticeYn;
    }    
    /**
     * bbs_info.I_HOT_ICON_COUNT
     * 
     * @return    HOT icon 표시 조회수 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getHotIconCount() {
        return this.hotIconCount;
    }
    
    /**
     * bbs_info.I_HOT_ICON_COUNT
     * 
     * @param hotIconCount    HOT icon 표시 조회수
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setHotIconCount(int hotIconCount) {
        this.hotIconCount = hotIconCount;
    }    
    /**
     * bbs_info.I_NEW_ICON_DAY
     * 
     * @return    NEW ICON 표시 일자 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getNewIconDay() {
        return this.newIconDay;
    }
    
    /**
     * bbs_info.I_NEW_ICON_DAY
     * 
     * @param newIconDay    NEW ICON 표시 일자
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setNewIconDay(int newIconDay) {
        this.newIconDay = newIconDay;
    }    
    /**
     * bbs_info.C_POPUP_YN
     * 
     * @return    팝업 가능 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getPopupYn() {
        return this.popupYn == null ? "" : this.popupYn;
    }
    
    /**
     * bbs_info.C_POPUP_YN
     * 
     * @param popupYn    팝업 가능 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setPopupYn(String popupYn) {
        this.popupYn = popupYn;
    }    
    /**
     * bbs_info.C_OPEN_YN
     * 
     * @return    공개 비공개 설정 가능 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getOpenYn() {
        return this.openYn == null ? "" : this.openYn;
    }
    
    /**
     * bbs_info.C_OPEN_YN
     * 
     * @param openYn    공개 비공개 설정 가능 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setOpenYn(String openYn) {
        this.openYn = openYn;
    }    
    /**
     * bbs_info.C_VOTE_YN
     * 
     * @return    만족도 조사 가능 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getVoteYn() {
        return this.voteYn == null ? "" : this.voteYn;
    }
    
    /**
     * bbs_info.C_VOTE_YN
     * 
     * @param voteYn    만족도 조사 가능 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setVoteYn(String voteYn) {
        this.voteYn = voteYn;
    }    
    /**
     * bbs_info.C_REPLY_YN
     * 
     * @return    답변 가능 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getReplyYn() {
        return this.replyYn == null ? "" : this.replyYn;
    }
    
    /**
     * bbs_info.C_REPLY_YN
     * 
     * @param replyYn    답변 가능 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setReplyYn(String replyYn) {
        this.replyYn = replyYn;
    }    
    /**
     * bbs_info.C_COMMENT_YN
     * 
     * @return    덧글 가능 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getCommentYn() {
        return this.commentYn == null ? "" : this.commentYn;
    }
    
    /**
     * bbs_info.C_COMMENT_YN
     * 
     * @param commentYn    덧글 가능 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setCommentYn(String commentYn) {
        this.commentYn = commentYn;
    }    
    /**
     * bbs_info.C_HTML_YN
     * 
     * @return    HTML editor 사용 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getHtmlYn() {
        return this.htmlYn == null ? "" : this.htmlYn;
    }
    
    /**
     * bbs_info.C_HTML_YN
     * 
     * @param htmlYn    HTML editor 사용 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setHtmlYn(String htmlYn) {
        this.htmlYn = htmlYn;
    }    
    /**
     * bbs_info.C_WASTE_YN
     * 
     * @return    휴지통 기능 사용 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getWasteYn() {
        return this.wasteYn == null ? "" : this.wasteYn;
    }
    
    /**
     * bbs_info.C_WASTE_YN
     * 
     * @param wasteYn    휴지통 기능 사용 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setWasteYn(String wasteYn) {
        this.wasteYn = wasteYn;
    }    
    /**
     * bbs_info.S_DATE_FORMAT
     * 
     * @return    날짜 표시 유형 년:yyyy 월:MM 일:dd 시간:hh 분:mm 초:ss 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getDateFormat() {
        return this.dateFormat == null ? "" : this.dateFormat;
    }
    
    /**
     * bbs_info.S_DATE_FORMAT
     * 
     * @param dateFormat    날짜 표시 유형 년:yyyy 월:MM 일:dd 시간:hh 분:mm 초:ss
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }    
    /**
     * bbs_info.I_LIST_COUNT
     * 
     * @return    페이지당 목록 수 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getListCount() {
        return this.listCount;
    }
    
    /**
     * bbs_info.I_LIST_COUNT
     * 
     * @param listCount    페이지당 목록 수
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setListCount(int listCount) {
        this.listCount = listCount;
    }    
    /**
     * bbs_info.I_FILE_COUNT
     * 
     * @return    파일 업로드 허용 갯수 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getFileCount() {
        return this.fileCount;
    }
    
    /**
     * bbs_info.I_FILE_COUNT
     * 
     * @param fileCount    파일 업로드 허용 갯수
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }    
    /**
     * bbs_info.I_FILE_SIZE
     * 
     * @return    파일 업로드 가능 총용량 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getFileSize() {
        return this.fileSize;
    }
    
    /**
     * bbs_info.I_FILE_SIZE
     * 
     * @param fileSize    파일 업로드 가능 총용량
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }    
    /**
     * bbs_info.S_HEADER
     * 
     * @return    말머리 정보 ';' 구분 하여 여러개 등록 가능 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getHeader() {
        return this.header == null ? "" : this.header;
    }
    
    /**
     * bbs_info.S_HEADER
     * 
     * @param header    말머리 정보 ';' 구분 하여 여러개 등록 가능
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setHeader(String header) {
        this.header = header;
    }    
    /**
     * bbs_info.S_HEAD_DESC
     * 
     * @return    게시판 상단 안내 문구 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getHeadDesc() {
        return this.headDesc == null ? "" : this.headDesc;
    }
    
    /**
     * bbs_info.S_HEAD_DESC
     * 
     * @param headDesc    게시판 상단 안내 문구
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setHeadDesc(String headDesc) {
        this.headDesc = headDesc;
    }    
    /**
     * bbs_info.S_TAIL_DESC
     * 
     * @return    게시판 하단 문구 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getTailDesc() {
        return this.tailDesc == null ? "" : this.tailDesc;
    }
    
    /**
     * bbs_info.S_TAIL_DESC
     * 
     * @param tailDesc    게시판 하단 문구
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setTailDesc(String tailDesc) {
        this.tailDesc = tailDesc;
    }    
    /**
     * bbs_info.D_CREATE
     * 
     * @return    작성일 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public Date getCreate() {
        return this.create;
    }
    
    /**
     * bbs_info.D_CREATE
     * 
     * @param create    작성일
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setCreate(Date create) {
        this.create = create;
    }    

    /**
     * BbsInfoBean 의 변수 값을 String 으로 반환함.
     * 
     * @return BbsInfoBean 의 변수 값
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("BbsInfoBean [");
        buffer.append(" bbsId = ").append(this.bbsId);
        buffer.append(" bbsName = ").append(this.bbsName);
        buffer.append(" bbsType = ").append(this.bbsType);
        buffer.append(" useYn = ").append(this.useYn);
        buffer.append(" emailYn = ").append(this.emailYn);
        buffer.append(" noticeYn = ").append(this.noticeYn);
        buffer.append(" hotIconCount = ").append(this.hotIconCount);
        buffer.append(" newIconDay = ").append(this.newIconDay);
        buffer.append(" popupYn = ").append(this.popupYn);
        buffer.append(" openYn = ").append(this.openYn);
        buffer.append(" voteYn = ").append(this.voteYn);
        buffer.append(" replyYn = ").append(this.replyYn);
        buffer.append(" commentYn = ").append(this.commentYn);
        buffer.append(" htmlYn = ").append(this.htmlYn);
        buffer.append(" wasteYn = ").append(this.wasteYn);
        buffer.append(" dateFormat = ").append(this.dateFormat);
        buffer.append(" listCount = ").append(this.listCount);
        buffer.append(" fileCount = ").append(this.fileCount);
        buffer.append(" fileSize = ").append(this.fileSize);
        buffer.append(" header = ").append(this.header);
        buffer.append(" headDesc = ").append(this.headDesc);
        buffer.append(" tailDesc = ").append(this.tailDesc);
        buffer.append(" create = ").append(this.create);
        buffer.append("] = ");
        buffer.append(super.toString());
        return buffer.toString();
    }
}
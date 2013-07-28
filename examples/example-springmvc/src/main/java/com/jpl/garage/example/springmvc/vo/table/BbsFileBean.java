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
public class BbsFileBean {

    /**
     * bbs_file.S_BBS_ID
     *
     * 
     * 게시판 번호
     */
    private String bbsId;
    
    /**
     * bbs_file.I_BBS_IDX
     *
     * 
     * 게시물 번호
     */
    private int bbsIdx;
    
    /**
     * bbs_file.I_FILE_IDX
     *
     * 
     * 파일 번호
     */
    private int fileIdx;
    
    /**
     * bbs_file.S_LOCAL_FILEPAHT
     *
     * 
     * 로컬 파일 이름
     */
    private String localFilepaht;
    
    /**
     * bbs_file.S_SERVER_FILENAME
     *
     * 
     * 서버 파일명
     */
    private String serverFilename;
    
    /**
     * bbs_file.S_SERVER_PATH
     *
     * 
     * 서버 파일 경로
     */
    private String serverPath;
    
    /**
     * bbs_file.S_FILE_TYPE
     *
     * 
     * 파일 유형
     */
    private String fileType;
    
    /**
     * bbs_file.C_THUMB_YN
     * defaultValue = 'N'
     * 
     * 썸네일 여부
     */
    private String thumbYn = "N";
    
    /**
     * bbs_file.C_MAJOR_YN
     * defaultValue = 'N'
     * 
     * 대표 이미지 / 파일 여부
     */
    private String majorYn = "N";
    
    /**
     * bbs_file.S_BBS_ID
     * 
     * @return    게시판 번호 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getBbsId() {
        return this.bbsId == null ? "" : this.bbsId;
    }
    
    /**
     * bbs_file.S_BBS_ID
     * 
     * @param bbsId    게시판 번호
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }    
    /**
     * bbs_file.I_BBS_IDX
     * 
     * @return    게시물 번호 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getBbsIdx() {
        return this.bbsIdx;
    }
    
    /**
     * bbs_file.I_BBS_IDX
     * 
     * @param bbsIdx    게시물 번호
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setBbsIdx(int bbsIdx) {
        this.bbsIdx = bbsIdx;
    }    
    /**
     * bbs_file.I_FILE_IDX
     * 
     * @return    파일 번호 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public int getFileIdx() {
        return this.fileIdx;
    }
    
    /**
     * bbs_file.I_FILE_IDX
     * 
     * @param fileIdx    파일 번호
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setFileIdx(int fileIdx) {
        this.fileIdx = fileIdx;
    }    
    /**
     * bbs_file.S_LOCAL_FILEPAHT
     * 
     * @return    로컬 파일 이름 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getLocalFilepaht() {
        return this.localFilepaht == null ? "" : this.localFilepaht;
    }
    
    /**
     * bbs_file.S_LOCAL_FILEPAHT
     * 
     * @param localFilepaht    로컬 파일 이름
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setLocalFilepaht(String localFilepaht) {
        this.localFilepaht = localFilepaht;
    }    
    /**
     * bbs_file.S_SERVER_FILENAME
     * 
     * @return    서버 파일명 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getServerFilename() {
        return this.serverFilename == null ? "" : this.serverFilename;
    }
    
    /**
     * bbs_file.S_SERVER_FILENAME
     * 
     * @param serverFilename    서버 파일명
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setServerFilename(String serverFilename) {
        this.serverFilename = serverFilename;
    }    
    /**
     * bbs_file.S_SERVER_PATH
     * 
     * @return    서버 파일 경로 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getServerPath() {
        return this.serverPath == null ? "" : this.serverPath;
    }
    
    /**
     * bbs_file.S_SERVER_PATH
     * 
     * @param serverPath    서버 파일 경로
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }    
    /**
     * bbs_file.S_FILE_TYPE
     * 
     * @return    파일 유형 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getFileType() {
        return this.fileType == null ? "" : this.fileType;
    }
    
    /**
     * bbs_file.S_FILE_TYPE
     * 
     * @param fileType    파일 유형
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }    
    /**
     * bbs_file.C_THUMB_YN
     * 
     * @return    썸네일 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getThumbYn() {
        return this.thumbYn == null ? "" : this.thumbYn;
    }
    
    /**
     * bbs_file.C_THUMB_YN
     * 
     * @param thumbYn    썸네일 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setThumbYn(String thumbYn) {
        this.thumbYn = thumbYn;
    }    
    /**
     * bbs_file.C_MAJOR_YN
     * 
     * @return    대표 이미지 / 파일 여부 
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String getMajorYn() {
        return this.majorYn == null ? "" : this.majorYn;
    }
    
    /**
     * bbs_file.C_MAJOR_YN
     * 
     * @param majorYn    대표 이미지 / 파일 여부
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public void setMajorYn(String majorYn) {
        this.majorYn = majorYn;
    }    

    /**
     * BbsFileBean 의 변수 값을 String 으로 반환함.
     * 
     * @return BbsFileBean 의 변수 값
     *
     * @author 홍길동 
     * @create 2011.11.01 
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("BbsFileBean [");
        buffer.append(" bbsId = ").append(this.bbsId);
        buffer.append(" bbsIdx = ").append(this.bbsIdx);
        buffer.append(" fileIdx = ").append(this.fileIdx);
        buffer.append(" localFilepaht = ").append(this.localFilepaht);
        buffer.append(" serverFilename = ").append(this.serverFilename);
        buffer.append(" serverPath = ").append(this.serverPath);
        buffer.append(" fileType = ").append(this.fileType);
        buffer.append(" thumbYn = ").append(this.thumbYn);
        buffer.append(" majorYn = ").append(this.majorYn);
        buffer.append("] = ");
        buffer.append(super.toString());
        return buffer.toString();
    }
}
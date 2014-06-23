package com.mdm.jqgrid.vo;



/**
 * <pre>
 * <p>Ajax요청에 대한 응답내용을 wrapping하는 VO클래스</p>
 * 
 * 성공-실패 여부, 결과 메세지, 적용 레코드 수를 담고 있다.
 * </pre>
 * @author developer
 *
 */
public class AjaxResponseVO {

	private boolean success;
	private String message;
	private int count;
	
	public AjaxResponseVO(){
		this(false,null,0);
	}
	
	public AjaxResponseVO(boolean success, String message, int count){
		this.success = success;
		this.message = message;
		this.count = count;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public String getMessage() {
		return message;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
		
}

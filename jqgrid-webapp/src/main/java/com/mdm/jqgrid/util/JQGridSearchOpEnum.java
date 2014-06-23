package com.mdm.jqgrid.util;

/**
 * <pre>
 * <p>JQGrid의 조건검색시에 사용될 연산자를 리터럴상수로 나열한 Enum</p>
 * SQL의 Where구문 작성시에 사용됨.
 * </pre>
 * @author developer
 *
 */
public enum JQGridSearchOpEnum {

	EQUAL("eq","=%1$s")
	,NOT_EQUAL("ne","!=%1$s")
	,BEGIN_WITH("bw"," LIKE %1$s||'%%'")
	,NOT_BEGIN_WITH("bn"," NOT LIKE %1$s||'%%'")
	,END_WITH("ew"," LIKE '%%'||%1$s")
	,NOT_END_WITH("en"," NOT LIKE '%%'||%1$s")
	,CONTAINS("cn"," LIKE '%%'||%1$s||'%%'")
	,NOT_CONTAINS("nc"," NOT LIKE '%%'||%1$s||'%%'")
	,IN("in"," IN (%1$s)")
	,NOT_IN("ni"," NOT IN (%1$s)")
	;
	
	private String operKey;
	private String operVal;
	private JQGridSearchOpEnum(String operKey,String operVal) {
		this.operKey = operKey;
		this.operVal = operVal;
	}
	
	public String getKey(){
		return operKey;
	}
	
	public String getValue(){
		return operVal;
	}
	
	
	public static JQGridSearchOpEnum get(String operKey){
		JQGridSearchOpEnum result = null;
		for(JQGridSearchOpEnum en : JQGridSearchOpEnum.values()){
			if(en.operKey.equals(operKey)){
				result = en;
				break;
			}
		}
		return result;
	}
}

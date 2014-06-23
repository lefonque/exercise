<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/ui-lightness/jquery-ui-1.10.3.custom.css"/>'>
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/ui.jqgrid.css"/>' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/sample/screen.css"/>' />
<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/epis-menu.css"/>' />


<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.10.2.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-ui-1.10.3.custom.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery.ui.menubar.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/i18n/grid.locale-en.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery.jqGrid.src.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery.validate.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/additional-methods.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/date.format.js"/>'></script>
<!-- 
<script type="text/javascript" src='<c:url value="/resources/js/i18n/message_ko.js"/>'></script>
 -->
<script type="text/javascript">

var agentValidator = null, agentJobValidator = null;
$(document).ready(function() {
	initMenu();
	initFormDialog();
	initValidation();
	initGrid();
	initEvent();
	
	$('#orgCode').parent().hide();
	
	// rows="4" cols="37"  size="38"
	$('textarea').attr('rows','6').attr('cols','117');
	$('#dialogAgentJob').find('input:text, input:password').attr('size','38');
	$('#dialogAgent').find('input:text, input:password').attr('size','30');
});


function initMenu(){
	$('#menu').menubar();
}

/**
 * 입력 Form Dialog(Configuration, Job) 및 Alert Dialog를 설정함
 */
function initFormDialog() {
	//=================================================================
	//	Configuration 입력 Form Dialog
	//=================================================================
	$('#dialogAgent').dialog({
		autoOpen: false
		,height: 465
		,width: 410
		,modal: true
		,show:{
			effect:"blind"
			,duration:500
		}
		,hide:{
			effect:"explode"
			,duration:500
		}
		,open: handleOpen_DialogAgent
		,buttons: {
			//추가 or 수정 버튼
			"Apply": handleApplyBtnClick_DialogAgent
			,Cancel: function(){
				$(this).dialog('close');
			}
		}
		,close: function(){
			resetForm('Agent', agentValidator);
		}
	});
	
	
	//=================================================================
	//	Job 입력 Form Dialog
	//=================================================================
	$('#dialogAgentJob').dialog({
		autoOpen: false
		,height: 600
		,width: 900
		,modal: true
		,show:{ effect:"blind" ,duration:500 }
		,hide:{ effect:"explode" ,duration:500 }
		,open: handleOpen_DialogAgentJob
		,buttons: {
			//추가or수정 버튼
			"Apply": handleApplyBtnClick_DialogAgentJob
			,Cancel: function(){
				$(this).dialog('close');
			}
		}
		,close: function(){
			var agentId = $('#jobAgentId').val();
			resetForm('AgentJob', agentJobValidator);
			$('#jobAgentId').val(agentId);
			$('#jobType').val('A');
		}
	});
	
	
	//=================================================================
	//	메세지 Alert용 Dialog : open...Dialog 펑션을 이용할 것
	//=================================================================
	$( "#dialogAlert" ).dialog({
		autoOpen: false
		,modal: true
		,buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
				
			}
		}
		,close: function(){
			var parentDialogID = $(this).find('input:hidden').val();
			if(parentDialogID!=""){
				$('#'+parentDialogID).dialog('close');
			}
			$(this).find('input:hidden').val('');
			$(this).dialog("option", 'title', '');
		}
	});
}

/**
 * 	Agent측 입력 Form Dialog가 열릴 때 선행처리될 Callback Method
 *	처리내용 :
 *		- 수정모드일 경우, 상단 문구표시 (ex. 유통공사)
 */
function handleOpen_DialogAgent(event, ui){
	//=================================================================
	//	Configuration 입력창 오픈시 선행처리 기술
	//	-> 상단 문구표시 (ex. Client ID : CLIENT_A01)
	//	-> 수정모드일 경우, agentId는 표시하지 않도록 함(primary key임)
	//=================================================================
	$('.cmxform label').css({"width": "130px"});
	var applyType = $(this).find('input[name$="applyType"]').val();
	if(applyType=="U"){
		//$('#dialogAgentHead').text($('#orgCode option:selected').text());
		$('#dialogAgentHead').text($('#agentId').val() + " 수정입력");
	}
	
	//SMS 사용여부에 따라 입력활성화 처리
	$('#smsUseYn').trigger('change');
}


/**
 * 	Agent측 입력 Form Dialog에서 Apply버튼 클릭시 처리될 Callback Method
 *	처리내용 :
 *		- 추가인지, 수정인지에 따라 URL분기
 *		- ajax로 서버에 반영한다.
 *		- 처리가 완료되면 안내 Dialog를 띄우고 gridAgent을 리로드한다.
 */
function handleApplyBtnClick_DialogAgent(){
	if(agentValidator.form()){
		var operation = "추가";
		var url = '<c:url value="/config/addAgent"/>';
		if($('#agent_applyType').val()=="U"){
			operation = "수정";
			url = '<c:url value="/config/modifyAgent"/>';
		}
		$.post(url
			,$('#formAgent').serialize()
			,function(data){
				openResultDialog(data, operation,"dialogAgent");
				jQuery("#gridAgent").jqGrid('setGridParam',{page:1}).trigger("reloadGrid");
				//$(this).dialog('close'); ALERT에서 닫음
			}	
		);
	}
}

/**
 *	Agent Job 측 입력 Form Dialog가 열릴 때 선행처리될 Callback Method
 *	처리내용 :
 *		- dialogAgentJob쪽 Hidden Field인 agentId 에, 
 *		  gridAgent에서 선택된 rowData의 해당 값을 bind한다.
 *		- Dialog 상단부에 안내문구를 작성한다.
 */
function handleOpen_DialogAgentJob(event, ui){
	//form.cmxform label { width: 130px; }
	$('.cmxform label').css({"width": "100px"});
	var gridAgentRowId = null;
	var gridAgentRowIDs = $('#gridAgent').jqGrid('getGridParam','selarrrow');
	if(gridAgentRowIDs!=null && gridAgentRowIDs.length > 0){
		gridAgentRowId = gridAgentRowIDs[gridAgentRowIDs.length-1];
	}
	else{
		openAlertDialog('Illegal Access!!!');
		return false;
	}
	var gridAgentRowData = $('#gridAgent').jqGrid('getRowData',gridAgentRowId);
	$("#jobAgentId").val(gridAgentRowData.agentId);
	
	
	//안내문구 처리
	var applyType = $(this).find('input[name$="applyType"]').val();
	//var headText = "<b>[" + $('#orgCode option:selected').text() + "]</b> : ";
	var headText = "<b>[" + $('#jobAgentId').val() + "]</b> : ";
	if(applyType=="U"){
		headText += $(this).find("#jobName").val();
		headText += " 변경";
	}
	else{
		headText += " 작업생성";
	}
	$('#dialogAgentJobHead').html(headText);
	
	//jdbcUrl 입력창에 안내문구 표시
	$('#jdbcDriverClassName').trigger('change');
}

/**
 * 	Agent Job측 입력 Form Dialog에서 Apply버튼 클릭시 처리될 Callback Method
 *	처리내용 :
 *	- 추가 건인지, 수정 건인지에 따라 URL을 분기한다.
 *	- ajax로 서버에 반영한다.
 *	- 처리가 완료되면 안내 Dialog를 띄우고 gridAgentJob을 리로드한다.
 */
function handleApplyBtnClick_DialogAgentJob(){
	if(agentJobValidator.form()){
		
		//URL 분기 및 서버처리완료 후 안내문구 대응
		var operation = "추가";
		var url = '<c:url value="/config/addAgentJob"/>';
		if($('#job_applyType').val()=="U"){
			operation = "수정";
			url = '<c:url value="/config/modifyAgentJob"/>';
		}
		if($('#batchSelectCount').val()==""){
			$('#batchSelectCount').val(0);
		}
		//서버에 처리요청
		$.ajax({
			type: "POST"
			,url: url
			,data: $('#formAgentJob').serialize()
			,success: function(data,textStatus,jqXHR){
				//안내 문구 표시
				openResultDialog(data, operation, "dialogAgentJob");
				//grid 리로드 (검색시 agentId값도 조건에 반영되도록 조치함)
				var agentJobDataUrl = '<c:url value="/config/agentJobList"/>?agentId=';
				agentJobDataUrl += encodeURIComponent($('#jobAgentId').val());
				jQuery("#gridAgentJob").jqGrid('setGridParam',{url:agentJobDataUrl, page:1}).trigger("reloadGrid");
				//$(this).dialog('close');  ALERT에서 닫음
			}
			,error: function(jqXHR, textStatus, errorThrown){
				alert('textStatus : ' + textStatus);
				alert('errorThrown : ' + errorThrown);
				alert('responseText : ' + jqXHR.responseText);
			}
		});
		/*
		$.post(url
			,$('#formAgentJob').serialize()
			,function(data){	//완료시 처리
				//안내 문구 표시
				openResultDialog(data, operation, "dialogAgentJob");
				//grid 리로드 (검색시 agentId값도 조건에 반영되도록 조치함)
				var agentJobDataUrl = '<c:url value="/config/agentJobList"/>?agentId=';
				agentJobDataUrl += encodeURIComponent($('#jobAgentId').val());
				jQuery("#gridAgentJob").jqGrid('setGridParam',{url:agentJobDataUrl, page:1}).trigger("reloadGrid");
				//$(this).dialog('close');  ALERT에서 닫음
			}
		).fail(function(){
			openAlertDialog("처리에 실패하였습니다.");
		});
		*/
	}
	else{
		agentJobValidator.focusInvalid();
	}
}

/**
 * 	Dialog가 닫혔을 때의 처리를 하는 메서드
 *	Dialog내의 Form Control을 초기화 및 validator가 화면에
 *	표시한(표시했을 경우) 경고문구도 초기화한다.
 */
function resetForm(subject, validator){
	var formSelector = '#form' + subject; 
	$(formSelector + ' input,textarea').val('');
	$(formSelector + ' option:first-child').prop("selected","selected");
	if(validator!=null){
		validator.resetForm();
	}
	$('#dialog'+subject+'Head').text('신규추가');
}

/**
 * Validation을 설정함.
 */
function initValidation(){
	agentValidator = $('#formAgent').validate({
		rules: {
			smsCellNo:{
				required: function(){
					return $("#smsUseYn").val()=='Y';
				}
			}
		}
	});
	
	$.validator.addMethod('duplicate'
		,function(value,element,gridID){
			var valid = true;
			var sourceDatas = $('#' + gridID).jqGrid('getRowData');
			//비교할 데이터가 없으면 신규이므로 valid.
			if(sourceDatas==null || sourceDatas.length==0){
				return valid;
			}
			//동일한 job name이 있는지 확인
			$.each(sourceDatas,function(idx,row){
				//modify의 경우, 현재 수정중인 건의 jobId와 다른 row의 것과 비교함
				if(row.jobId==$('#jobId').val()){
					return true;//continue
				}
				if($(element).val()==row[element.name]){
					valid = false;
					return false;//break
				}
			});
			
			return valid;
		}
		,$.validator.format("The value is duplicated.")

	);
	agentJobValidator = $('#formAgentJob').validate({
		rules: {
			jobName:{
				duplicate:'gridAgentJob'
			}
		}
		//,onfocusout: false	//select form에 addMethod로 추가한 method를 적용할 경우,
							//onfocusout event에도 validate 체킹을 함
	});
	
}


/**
 * Configuration Grid 및 Schedule Grid를 설정함.
 */
function initGrid() {
	//=================================================================
	//	Configuration List표시용 Grid
	//=================================================================
	jQuery("#gridAgent").jqGrid(
	{
		url : '<c:url value="/config/agentList"/>'
		,mtype : 'POST'
		,datatype : "json"
		,colNames : [ 'Agent ID', 'Org Code','기관명', 'OS', 'Charset', '웹서비스 유저명', '웹서비스 패스워드', 'SMS 여부', 'SMS TEL', '담당자명', '담당자연락처', '등록일자', '수정일자' ]
		,colModel : [ 
			{ name : 'agentId', index : 'agent_id', width : 120 }
			,{ name : 'orgCode', index : 'org_code', width : 80 }
			,{ name : 'orgName', index : 'org_code'
				,formatter : orgNameFormatter, width : 100 }
			,{ name : 'operatingSystem', index : 'operating_system', width : 100 }
			,{ name : 'charset', index : 'charset', width : 70 }
			,{ name : 'websvcUser', index : 'websvc_user', width : 100 }
			,{ name : 'websvcPass', index : 'websvc_pass', width : 80 }
			,{ name : 'smsUseYn', index : 'sms_use_yn', width : 60 }
			,{ name : 'smsCellNo', index : 'sms_cell_no', width : 100 }
			,{ name : 'officerName', index : 'officer_name', width : 80 }
			,{ name : 'officerContact', index : 'officer_contact', width : 80 }
			,{ name : 'createdDate', index : 'created_date'
				,formatter : datetimeFormatter, width : 100 }
			,{ name : 'modifiedDate', index : 'modified_date'
				,formatter : datetimeFormatter, width : 100 }
		]
		,rowNum : 10
		,rowList : [ 10, 20, 30 ]
		,pager : '#pagerAgentGrid'
		,sortname : 'agent_id'
		,viewrecords : true
		,sortorder : "desc"
		,multiselect: true
		,multiboxonly:true
		,caption : "Agent 목록"
		,jsonReader : {
			root: "root"
			,repeatitems: false
		}
		,onSelectRow: function(rowId,status){
			handleRowSelect_GridAgent(rowId,status);
		}
		,onSelectAll: function(rowIds,status){
			if(rowIds!=null && rowIds.length > 0){
				handleRowSelect_GridAgent(rowIds[rowIds.length-1],status);
			}
			else{
				handleRowSelect_GridAgent(null,status);
			}
		}
		,loadError: handleLoadError_GridAgent
		,gridComplete: handleComplete_GridAgent
	});
	jQuery("#gridAgent").jqGrid('navGrid', '#pagerAgentGrid', {
		edit : false
		,add : false
		,del : false
		,search: false
		,refresh: false
		//,addfunc:function(){
		//	$('#dialogAgent').dialog("open");
		//}
	});
	jQuery("#gridAgent").jqGrid('hideCol', ["orgCode","orgName","charset","websvcPass","smsCellNo","officerContact"]);
	
	
	//=================================================================
	//	Schedule List표시용 Grid
	//=================================================================
	jQuery("#gridAgentJob").jqGrid(
	{
		url : '<c:url value="/config/agentJobList"/>?agentId='
		,mtype : 'POST'
		,datatype : "json"
		,colNames : [
			'작업 ID', '작업명', 'Agent ID', '작업구분', '실행시각'
			,'메인SQL', '전처리SQL', '후처리SQL', 'JDBC DRIVER', 'JDBC URL'
			,'JDBC USERNAME', 'JDBC PASSWORD', 'Server SQL', '레코드제한', '등록일자', '수정일자'
		]
		,colModel : [
		    { name : 'jobId', index : 'job_id', width:66}
			,{ name : 'jobName', index : 'job_name', width : 100 }
			,{ name : 'agentId', index : 'agent_id', width : 50 }
			,{ name : 'jobType', index : 'job_type', width : 50 }
			,{ name : 'execTime', index : 'agent_exec_time', width : 80 }
			,{ name : 'sqlMain', index : 'sql_main', width : 150 }
			,{ name : 'sqlPre', index : 'sql_pre', width : 150 }
			,{ name : 'sqlPost', index : 'sql_post', width : 150 }
			,{ name : 'jdbcDriverClassName', index : 'jdbc_driver_class_name', width : 100 }
			,{ name : 'jdbcUrl', index : 'jdbc_url', width : 215 }
			,{ name : 'jdbcUsername', index : 'jdbc_username', width : 80 }
			,{ name : 'jdbcPassword', index : 'jdbc_password', width : 80 }
			,{ name : 'serverSql', index : 'server_sql', width : 150 }
			,{ name : 'batchSelectCount', index : 'batch_select_count', width : 80 }
			,{ name : 'createdDate', index : 'created_date'
				,formatter : datetimeFormatter, width : 100 }
			,{ name : 'modifiedDate', index : 'modified_date'
				,formatter : datetimeFormatter, width : 100 }
		]
		,rowNum : 10
		,rowList : [ 10, 20, 30 ]
		,pager : '#pagerAgentJobGrid'
		,sortname : 'job_id'
		,viewrecords : true
		,sortorder : "desc"
		,multiselect: true
		,multiboxonly:true
		,caption : "Agent 작업목록"
		,jsonReader : {
			root: "root"
			,repeatitems: false
		}
		,onSelectRow: handleRowSelect_GridAgentJob
		,onSelectAll: handleAllSelect_GridAgentJob
		,gridComplete: handleComplete_GridAgentJob
	});
	jQuery("#gridAgentJob").jqGrid('navGrid', '#pagerAgentJobGrid', 
		{
			edit : false
			,add : false
			,del : false
			,search: false
			,refresh: false
			//,addfunc:function(){
			//	$('#dialogAgentJob').dialog("open");
			//}
		}
		,{'agentId':$('#jobAgentId').val()}
	);
	jQuery("#gridAgentJob").jqGrid('hideCol', [
	   	"agentId", "jobType", "sqlMain", "sqlPre"
		,"sqlPost", "jdbcDriverClassName", "jdbcUsername"
		,"jdbcPassword", "serverSql", "batchSelectCount"
	]);
}

/**
 * Grid 내 날짜표시컬럼에서의 날짜값 표시처리
 *
 *	=> 	서버에서 java.util.Date의 값을 millisec으로 보내기 때문에 
 *		그에 대한 처리가 필요함.
 *		서버에서 보낸 값을 변경하지 않기 위해, 서버에서 보낸 값이 bind된 컬럼은 hide하고,
 *		별도의 컬럼을 추가하여 화면표시 용도로 사용한다. 
 */
function datetimeFormatter(cellValue,option,rowObject){
	 var result = '';
	 if(cellValue!=null){
		 result = new Date(cellValue).format('yyyy-mm-dd HH:MM');
	 }
	return result;
}


function orgNameFormatter(cellValue, option, rowObject){
	var result = '';
	var orgCode = rowObject.orgCode;
	if(orgCode!=null){
		result = $('#orgCode option[value="' + orgCode + '"]').text();
	}
	return result;
}
 
/**
 * Agent측 Grid에서 row 클릭시 Event Handling
 *	rowId : 클릭된 row의 ID
 *	status : checkbox가 check이면 true, uncheck이면 false
 */
function handleRowSelect_GridAgent(rowId,status){
	var inactivateButton = true;
	//체크해제시, 선택된 것의 rowID를 찾아서 처리
	//체크시, 기존처리대로
	if(!status){
		var rowIdArray = $('#gridAgent').jqGrid('getGridParam','selarrrow');
		if(rowIdArray!=null && rowIdArray.length > 0){
			rowId = rowIdArray[rowIdArray.length-1];
		}
		else{
			rowId = null;
		}
	}
	
	var agentId = '';
	var agentJobDataUrl = '<c:url value="/config/agentJobList"/>?agentId=';
	if(rowId == null) {
		// gridAgentJob을 초기화한다.
		if(jQuery("#gridAgentJob").jqGrid('getGridParam','records') > 0 )
		{
			
			jQuery("#gridAgentJob").jqGrid('setGridParam',{url:agentJobDataUrl, page:1});
			jQuery("#gridAgentJob").trigger('reloadGrid');
		}
		$("#gridAgentJob").jqGrid('setCaption',"Agent 작업목록");
	} else {
		// gridAgentJob을 로딩한다.
		var rowData = $("#gridAgent").jqGrid('getRowData', rowId);
		agentJobDataUrl += encodeURIComponent(rowData.agentId);
		jQuery("#gridAgentJob").jqGrid('setGridParam',{url:agentJobDataUrl, page:1});
		jQuery("#gridAgentJob").jqGrid('setCaption',"Agent 작업목록 : "+orgNameFormatter(rowData.agentId, null, rowData))
			.trigger('reloadGrid');
		inactivateButton = false;
		
		//Job 측 Form 내 hidden값에 bind할 agentId 를 취득한다.
		agentId = rowData.agentId;
		//$('#btnModifyAgentJob, #btnRemoveAgentJob').button("option","disabled",true);//handleComplete_GridAgentJob에서 처리됨
	}

	//Job측 Form내의 jobAgentId 컨트롤에 값을 할당한다.
	$('#jobAgentId').val(agentId);
	$('#btnModifyAgent, #btnRemoveAgent, #btnAddAgentJob').button("option","disabled",inactivateButton);
	//$('#btnRemoveAgent').button("option","disabled",inactivateButton);
	
}

function handleLoadError_GridAgent(xhr,status,error){
	alert(error);
}

/**
 * gridAgent가 표시가 완료되었을 때 처리되는 Callback Method
 */
function handleComplete_GridAgent(){
	$('#btnModifyAgent,#btnRemoveAgent,#btnAddAgentJob,#btnModifyAgentJob').button("option","disabled",true);

	$("#gridAgentJob").jqGrid('setCaption',"Agent 작업목록");
	$("#jobAgentId").val('');
	// gridAgent가 로딩됨과 동시에 gridAgent를 바라보던 gridAgentJob은 초기화되도록 조치함.
	var agentJobDataUrl = '<c:url value="/config/agentJobList"/>?agentId=';
	$('#gridAgentJob').jqGrid('setGridParam',{url:agentJobDataUrl, page:1}).trigger("reloadGrid");
	//$('#btnRemoveAgent').button("option","disabled",true);
	//$('#btnAddAgentJob').button("option","disabled",true);
	//$('#btnModifyAgentJob').button("option","disabled",true);
	//$('#btnRemoveAgentJob').button('option','disabled',true);
}


function handleRowSelect_GridAgentJob(rowId,status){
	var inactivateModifyButton = true;
	
	//체킹된 row 존재여부 확인
	var rowIdArray = $('#gridAgentJob').jqGrid('getGridParam','selarrrow');
	if(rowIdArray!=null && rowIdArray.length > 0){
		rowId = rowIdArray[rowIdArray.length-1];
		if(rowIdArray.length==1){
			//하나만 선택되었을 때만 수정버튼 활성화
			inactivateModifyButton = false;
		}
	}
	else{
		rowId = null;
	}
	var inactivateRemoveButton = true;
	if(rowId!=null){
		inactivateRemoveButton = false;
	}
	//$('#btnModifyAgentJob, #btnRemoveAgentJob').button("option","disabled",inactivateButton);
	
	$('#btnRemoveAgentJob').button("option","disabled",inactivateRemoveButton);
	$('#btnModifyAgentJob').button("option","disabled",inactivateModifyButton);
}

function handleAllSelect_GridAgentJob(rowIdArray,status){
	var firstRowId = null;
	var inactivateModifyButton = true;
	//체킹시에만 체킹된 row 존재여부 확인
	if(rowIdArray!=null && rowIdArray.length > 0){
		firstRowId = rowIdArray[rowIdArray.length-1];
		if(rowIdArray.length==1){
			//하나만 선택되었을 때만 수정버튼 활성화
			inactivateModifyButton = false;
		}
	}
	var inactivateRemoveButton = true;
	if(status && (firstRowId!=null)){
		inactivateRemoveButton = false;
	}
	$('#btnRemoveAgentJob').button("option","disabled",inactivateRemoveButton);
	$('#btnModifyAgentJob').button("option","disabled",inactivateModifyButton);
}

/**
 *	gridAgentJob의 데이터가 모두 로딩되고, gridAgentJob에 엮인
 *	여타 처리가 완료되었을 때 처리되는 Callback Method
 *	
 */
function handleComplete_GridAgentJob(){
	
	//=================================================================
	//	Job쪽 버튼 활성/비활성화 처리
	//=================================================================
	var inactivateAddBtn = false;
	if($('#jobAgentId').val()==""){
		inactivateAddBtn = true;
	}
	$('#btnAddAgentJob').button("option","disabled",inactivateAddBtn);
	$('#btnModifyAgentJob, #btnRemoveAgentJob').button("option","disabled",true);
}

/**
 * Button 및 일부 입력폼내 컨트롤에 대해 Event를 설정한다.
 */
function initEvent(){
	//=================================================================
	//	Configuration 측 Button
	//=================================================================
	$('#btnAddAgent').button().on('click',handleAddBtnClick_BelowGrid);
		//jQuery("#gridAgent").jqGrid('addRowData',1,{'userId':'debug','pass':'debug','agentId':'debug'});
	//});
	$('#btnModifyAgent').button({'disabled':true}).on('click',handleModifyBtnClick_BelowGrid);
	$('#btnRemoveAgent').button({'disabled':true}).on('click',handleRemoveBtnClick_BelowGrid);
	
	//=================================================================
	//	Agent 측 입력폼 : SMS사용여부값에 따라 SMS수신 폰번호 입력란을 활성화시킨다.
	//=================================================================
	$('#smsUseYn').on('change',function(){
		if($(this).val()=='N'){
			$('#smsCellNo').val('');
			$('#smsCellNo').prop('disabled',true);
		}
		else{
			$('#smsCellNo').prop('disabled',false);
		}
	});
	$('#smsCellNo').prop('disabled',true);
	
	
	//=================================================================
	//	Agent Job 측 Button
	//=================================================================
	$('#btnAddAgentJob').button({disabled:true}).on('click',handleAddBtnClick_BelowGrid);
	$('#btnModifyAgentJob').button({disabled:true}).on('click',handleModifyBtnClick_BelowGrid);
	$('#btnRemoveAgentJob').button({'disabled':true}).on('click',handleRemoveBtnClick_BelowGrid);
	
	//	JDBC Driver Class 변경시 처리 : jdbcUrl 입력창에 hint표시
	$('#jdbcDriverClassName').on('change',handleSelectChange_JdbcDriver);
	$('#jdbcUrl,#batchSelectCount').tooltip().off("mouseover mouseout");

}

/**
 * 데이터 추가 버튼 클릭시 이벤트 Handling (Configuration - Job 공통)
 * 세부적인 사항은 해당 Form Dialog내에서 처리됨
 */
function handleAddBtnClick_BelowGrid(event){
	var dialogID = $(event.currentTarget).parent().attr('id');
	dialogID = dialogID.replace("div","dialog");
	$('#' + dialogID + ' input[name$="applyType"]').val("I");
	$('#' + dialogID).dialog("open");
}
/*
 * 데이터 변경 버튼 클릭시 이벤트 Handling(Configuration - Job 공통)
 * 세부적인 사항은 해당 Form Dialog내에서 처리됨
 */
function handleModifyBtnClick_BelowGrid(event){
	var subject = $(event.currentTarget).parent().attr('id').replace("div","");
	var gridSelector = "#grid" + subject;
	var dialogSelector = "#dialog" + subject;
	var rowId = jQuery(gridSelector).jqGrid('getGridParam','selrow');
	if(rowId==null){
		//openAlertDialog("수정할 " + subject + "이 선택되지 않았습니다.");
		openAlertDialog("수정할 건이 선택되지 않았습니다.");
		return false;
	}
	
	var rowData = jQuery(gridSelector).jqGrid('getRowData',rowId);
	bindForm( ('form' + subject),rowData );
	
	$(dialogSelector + ' input[name$="applyType"]').val("U");
	$(dialogSelector).dialog("open");
}

/**
 * Agent쪽 삭제버튼 클릭시 Event Handling
 * gridAgent 내에서 선택된 row들의 agentId값만 모은 배열을 파라메터로 하여,
 * 서버에 삭제요청을 하고 그 결과를 받는다.
 */
function handleRemoveBtnClick_BelowGrid(event){
	var subject = $(event.currentTarget).parent().attr('id').replace("div","");
	var gridSelector = "#grid" + subject;
	var rowIds = $(gridSelector).jqGrid('getGridParam','selarrrow');
	if(rowIds==null){
		//alert("삭제할 Configuration이 선택되지 않았습니다.");
		openAlertDialog("삭제할 건이 선택되지 않았습니다.");
		return false;
	}
	
	var idField = "agentId",removeURL = '<c:url value="/config/removeAgent"/>';
	if(subject=="AgentJob"){
		idField = "jobId";
		removeURL = '<c:url value="/config/removeAgentJob"/>';
	}
	var rowIDs = new Array();
	var rowData = null;
	$.each(rowIds,function(idx,rowId){
		rowData = jQuery(gridSelector).jqGrid('getRowData',rowId);
		rowIDs[idx] = rowData[idField];
	});
	$.post(removeURL
		,{"ids" : rowIDs}
		,function(data){
			openResultDialog(data, "삭제", "");
			jQuery(gridSelector).jqGrid('setGridParam',{page:1}).trigger("reloadGrid");
		}
	);
}

/**
 * Grid에서 선택한 row의 데이터를 Dialog내 Form Control에 바인딩함.
 * 정상적인 처리를 위해 json데이터의 key와 Form Control의 name이 동일해야 함.
 * @param formID
 */
function bindForm(formID, rowData){
	$.each(rowData,function(fieldName,fieldValue){
		$('#'+formID).find('#' + fieldName).val(fieldValue);
	});
}

/**
 *	dialogAlert을 서버처리결과를 알려주는 용도로 사용함
 *	
 */
function openResultDialog(response, operation, parentDialogID){
	var titleText = operation + " 실패";
	var msgText = operation + "에 실패하였습니다.";
	if(response.success){
		titleText = operation + "완료";
		msgText = response.message;
	}
	$('#dialogAlert').dialog("option", 'title', titleText);
	$('#alertMessage').text(msgText);
	$('#dialogAlert input:hidden').val(parentDialogID);
	$('#dialogAlert').dialog('open');
}

/**
 * dialogAlert을 Alert 용도로 사용함
 */
function openAlertDialog(msgText){
	$('#dialogAlert').dialog('option', 'title', "Message");
	$('#alertMessage').text(msgText);
	$('#dialogAlert').dialog('open');
}


function handleSelectChange_JdbcDriver(event){
	var selectedDBType = $(event.currentTarget).find('option:selected').text();
	var title = "";
	switch(selectedDBType){
	case "Oracle":
		title = "jdbc:oracle:thin:@HOSTNAME:PORT:SID";
		break;
	case "SqlServer":
		title = "jdbc:sqlserver://HOSTNAME:PORT;databaseName=DB명";
		break;
	case "MySQL":
		title = "jdbc:mysql://HOSTNAME:PORT/DB명";
		break;
		
	case "HSQLDB":
		title = "jdbc:hsqldb:hsql://HOSTNAME:PORT/DB명";
	}
	$('#jdbcUrl').attr('title',title);
}

</script>
<title>EPIS 범용 정보연계 솔루션 : Agent 설정</title>
</head>
<body>
	<!-- ======================================================================================== -->
	<!-- 메뉴 표시부 : Configuration 설정 / 로그화면 -->
	<!-- ======================================================================================== -->
	<ul id="menu">
		<li><a href='<c:url value="/config/main"/>'>Configuration 설정</a></li>
		<li><a href='<c:url value="/config/logView"/>'>Log 목록</a></li>
	</ul>
	
	
	<!-- ======================================================================================== -->
	<!-- Agent List 표시부 : Grid -->
	<!-- ======================================================================================== -->
	<div id="divAgent">
		<h1>Agent 설정</h1>
		<table id="gridAgent"></table>
		<div id="pagerAgentGrid"></div>
		<button id="btnAddAgent">추가</button>
		<button id="btnModifyAgent">수정</button>
		<button id="btnRemoveAgent">삭제</button>
	</div>
	
	<!-- ======================================================================================== -->
	<!-- Job List 표시부 : Grid -->
	<!-- ======================================================================================== -->
	<div id="divAgentJob">
		<h1>작업 설정</h1>
		<table id="gridAgentJob"></table>
		<div id="pagerAgentJobGrid"></div>
		<button id="btnAddAgentJob">추가</button>
		<button id="btnModifyAgentJob">수정</button>
		<button id="btnRemoveAgentJob">삭제</button>
	</div>
	
	<!-- ======================================================================================== -->
	<!-- Agent 입력 Dialog -->
	<!-- ======================================================================================== -->
	<div id="dialogAgent" title="Agent 설정">
		<p><b id="dialogAgentHead">신규추가</b></p>
		<form id="formAgent" name="formAgent" class="cmxform" style="width:370px;"><!-- User ID', 'Pass', 'Client ID','Use SMS', 'Cell No. -->
			<fieldset>
			<p>
				<label for="orgCode">기관 코드</label>
				<select name="orgCode" id="orgCode">
					<option value="" label="기관선택" selected="selected">기관선택</option>
<c:forEach items="${orgList}" var="org">
					<option value="${org.orgCode}" label="${org.orgName}">${org.orgName}</option>
</c:forEach>
				</select>
			</p>
			<p>
				<label for="operatingSystem">OS Type</label>
				<select name="operatingSystem" id="operatingSystem">
<c:forEach items="${osTypes}" var="os">
					<option value="${os}" label="${os}">${os}</option>
</c:forEach>
				</select>
			</p>
			<p>
				<label for="charset">Character Set</label>
				<select name="charset" id="charset">
					<option value="EUC-KR" label="EUC-KR">EUC-KR</option>
					<option value="UTF-8" label="UTF-8">UTF-8</option>
				</select>
			</p>
			<p>
				<label for="websvcUser">웹서비스 사용자ID</label>
				<input type="text" name="websvcUser" id="websvcUser" 
					class="required alphanumeric" />
			</p>
			<p>
				<label for="websvcPass">웹서비스 Password</label>
				<input type="password" name="websvcPass" id="websvcPass"
					class="required password alphanumeric" />
			</p>
			<p>
				<label for="officerName">담당자명</label>
				<input type="text" name="officerName" id="officerName">
			</p>
			<p>
				<label for="officerContact">담당자 연락처</label>
				<input type="text" name="officerContact" id="officerContact">
			</p>
			<p>
				<label for="smsUseYn">SMS사용여부</label>
				<select id="smsUseYn" name="smsUseYn">
					<option selected="selected" value="N" label="N">N</option>
					<option value="Y" label="Y">Y</option>
				</select>
			</p>
			<p>
				<label for="smsCellNo">휴대폰번호</label>
				<input type="text" name="smsCellNo" id="smsCellNo"
					class="required" />
			</p>
			</fieldset>
			<input type="hidden" id="agentId" name="agentId"/>
			<input type="hidden" id="agent_applyType" name="agent_applyType"/>
		</form>
	</div>
	
	
	<!-- ======================================================================================== -->
	<!-- Job 입력 Dialog -->
	<!-- ======================================================================================== -->
	<div id="dialogAgentJob" title="Job 설정">
		<p id="dialogAgentJobHead"></p>
		
		<form id="formAgentJob" name="formAgentJob" class="cmxform"><!-- User ID', 'Pass', 'Client ID','Use SMS', 'Cell No. -->
			<fieldset>
			<p>
				<label for="jobName">작업명</label>
				<input type="text" name="jobName" id="jobName"
					class="required" />
			</p>
			<p>
				<label for="execTime">실행시각</label>
				<input type="text" name="execTime" id="execTime"
					class="required time" />
			</p>
			<p>
				<label for="jdbcUsername">DB Username</label>
				<input type="text" name="jdbcUsername" id="jdbcUsername"
					class="required">
			</p>
			<p>
				<label for="jdbcPassword">DB Password</label>
				<input type="password" name="jdbcPassword" id="jdbcPassword"
					class="required">
			</p>
			<p>
				<label for="jdbcDriverClassName">DB Type</label>
				<select name="jdbcDriverClassName" id="jdbcDriverClassName">
					<option selected="selected" value="oracle.jdbc.driver.OracleDriver" label="Oracle">Oracle</option>
					<option value="com.microsoft.sqlserver.jdbc.SQLServerDriver" label="SqlServer">SqlServer</option>
					<option value="com.mysql.jdbc.Driver" label="MySQL">MySQL</option>
					<option value="org.hsqldb.jdbcDriver" label="HSQLDB">HSQLDB</option>
				</select>
			</p>
			<p>
				<label for="jdbcUrl">JDBC URL</label>
				<input type="text" name="jdbcUrl" id="jdbcUrl"
					class="required">
			</p>
			<p>
				<label for="sqlMain">Main SQL</label>
				<textarea name="sqlMain" id="sqlMain" 
					class="required"></textarea>
			</p>
			<p>
				<label for="sqlPre">전처리 SQL</label>
				<textarea name="sqlPre" id="sqlPre" ></textarea>
			</p>
			<p>
				<label for="sqlPost">후처리 SQL</label>
				<textarea name="sqlPost" id="sqlPost">
				</textarea>
			</p>
			<p>
				<label for="serverSql">서버처리 SQL</label>
				<textarea name="serverSql" id="serverSql" 
					class="required"></textarea>
			</p>
			<p>
				<label for="batchSelectCount">레코드 제한</label>
				<input type="text" name="batchSelectCount" id="batchSelectCount"
					class="number" title="agent가 1회 select시 취득할 레코드 수">
			</p>
			</fieldset>
			<input type="hidden" id="jobAgentId" name="agentId"/>
			<input type="hidden" id="jobId" name="jobId"/>
			<input type="hidden" id="jobType" name="jobType" value="A"/>
			<input type="hidden" id="job_applyType" name="job_applyType"/>
		</form>
	</div>
	
	<!-- ======================================================================================== -->
	<!-- 안내문구 및 경고 메세지 표시용 Dialog -->
	<!-- ======================================================================================== -->
	<div id="dialogAlert" title="">
		<p id="alertMessage"></p>
		<form><input type="hidden" id="dialogID" name="dialogID"/></form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Info Page</title>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.10.2.js"/>'></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#btnNew').bind('click',function(){
		
		popupView(1,0,'insert');
	});
});

function popupView(bbsId,bulletinId,mode){
	$('#bulletinInfo input:hidden[name="bulletin.bbsId"]').val(bbsId);
	$('#bulletinInfo input:hidden[name="bulletin.bbsIdx"]').val(bulletinId);
	$('#bulletinInfo input:hidden[name="mode"]').val(mode);
	var url = '<c:url value="/example/bulletin/pop/view"/>?' + $('#bulletinInfo').serialize();
	var style = "center:yes; dialogwidth:400px; dialogheight:300px; dialogleft:70px; dialogtop:100px; scroll:no; status:no; ";
	var rtnModal = window.showModalDialog(url,window,style);
	//rtnModal : 모달창에서 window.returnValue 에할당한 Object
	
	if(!$.isEmptyObject(rtnModal) && rtnModal.successful){
		window.location.reload(true);
	}
}
</script>
</head>
<body>
<table>
<thead><tr>
<th>번호</th><th>제목</th>
</tr></thead>
<tbody>

<c:forEach items="${bulletinList}" var="row">
<tr style="cursor:hand;" onclick="javascript:popupView('${row.bbsId}','${row.bbsIdx}','update')"><td>${row.bbsIdx}</td><td>${row.bbsTitle}</td></tr>
</c:forEach>

</tbody>
</table>
<button id="btnNew" type="button">쓰기</button>
<form id="bulletinInfo" method="post" action='<c:url value="/example/bulletin/pop/view"/>'>
	<input type="hidden" name="bulletin.bbsId" value="1">
	<input type="hidden" name="bulletin.bbsIdx">
	<input type="hidden" name="mode"/>
</form>
</body>
</html>
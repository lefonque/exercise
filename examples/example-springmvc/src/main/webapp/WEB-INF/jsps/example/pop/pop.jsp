<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modal Page</title>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.10.2.js"/>'></script>
<script type="text/javascript">
window.name="pop";
$(document).ready(function(){
<c:if test="${not empty message}">
	alert('${message}');
	window.returnValue = {'successful':true};
</c:if>
	$('#btnSave').bind('click',function(){
		$('#bulletinInfo').attr('action','<c:url value="/example/bulletin/pop/save"/>');
		$('#bulletinInfo').submit();
	});
});
</script>
</head>
<body>
<form:form modelAttribute="bulletinInfo" method="post" target="pop">
	<fieldset>
		<label>제목</label>
		<form:input path="bulletin.bbsTitle"/><br>
		<label>내용</label>
		<form:textarea path="bulletin.bbsDesc"/>
	</fieldset>
	<form:hidden path="bulletin.bbsId"/>
	<form:hidden path="mode"/>
</form:form>
<button id="btnSave">저장</button>
</body>
</html>
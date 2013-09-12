<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Info Page</title>
<link rel="stylesheet" href='<c:url value="/resources/css/jquery-ui.css"/>'></link>
<link rel="stylesheet" href='<c:url value="/resources/css/demos.css"/>'></link>
<style>
.ui-autocomplete {
	max-height: 100px;
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
}
</style>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.10.2.js"/>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-ui.js"/>'></script>
<script type="text/javascript">
$(document).ready(function(){
	var json = JSON.parse('${json}');
	
	$('#textName').autocomplete({
		source: json,
		focus: function(event,data){
			return false;
		},
		select: function(event, data){
			$("#textName").val(data.item.label);
			$('#hiddenId').val(data.item.value);
			return false;
		}
	});
});

function checking(){
	alert("Name : "  + $('#textName').val());
	alert("ID : "  + $('#hiddenId').val());
}


</script>
</head>
<body>

<form method="post">
	<input id="textName"/>
	<input type="hidden" id="hiddenId"/>
	<button id="btnNew" type="button" onclick="javascript:checking();">체크</button>
</form>
</body>
</html>
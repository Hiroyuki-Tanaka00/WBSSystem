<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model.WBSBean" %>

<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Input.css">
<link rel="stylesheet" type="text/css" href="CSS/validationEngine.jquery.css">

<script src = "js/jquery-1.8.2.min.js" type = "text/javascript"></script>
<script src = "js/jquery.validationEngine.js" type = "text/javascript"></script>
<script src = "js/languages/jquery.validationEngine-ja.js" type = "text/javascript"></script>

<meta http-equiv = "Content-Type" content = "text/html" charset="UTF-8">
<title>タスク追加画面</title>
</head>

<% String userNameSession = (String)session.getAttribute("userName");
if(userNameSession == null){
	response.sendRedirect("Login.jsp");
}
%>


<body>

	<form action="WBSDetailCon" method="post"></form>

<%request.setCharacterEncoding("UTF-8"); String wbsName = request.getParameter("WBSNAME"); %>

<%String failureMessage = (String)request.getAttribute("addFailure"); %>

<form action = "infoAddCon" method = "post" id= "formID">



<div class="container">
	<input type = "hidden" name = "WBSNAME" value = "<%= wbsName %>" id = "<%= wbsName %>">
		 <a href="javascript:void(0)" onclick="DetailLink('<%= wbsName %>');">一覧</a>
</div>





<br>

	<h1>タスク追加画面</h1>

<div style="text-align: center; height:10px;">
<!-- エラーメッセージが存在するときだけ表示する -->
<% if (failureMessage != null) {%>

   <font id = 'TimeoutMessage'  color='red'><%=failureMessage %></font>

<%
}
%>
</div>
<br>


<p>
</p>
	<table border = "1">
	<caption>追加する項目を入力してください</caption>
	<thead>
	<tr>
		<th>項目</th>
		<th>入力欄</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>工程名</td>
		<td><input type = "text" name = "planName" size = "30" value = "" class="validate[required]"> </td>
	</tr>
	<tr>
		<td>担当者名</td>
		<td><input type = "text" name = "userName" size = "30" value = "" class="validate[required]"></td>

	</tr>
	<tr>
		<td>開始予定日</td>
		<td><input type = "date" name = "startPlanDay" size = "30" value = "" class="validate[required]"></td>
	</tr>
	<tr>
		<td>終了予定日</td>
		<td><input type = "date" name = "endPlanDay" size = "30" value = "" class="validate[required]"></td>
	</tr>
	<tr>
		<td>予定工数</td>
		<td><input type = "number" name = "workPlanLoad" size = "30" value = ""  class="validate[required],custom[number],min[1]"></td>
	</tr>
	</tbody>
	</table>

	<br>
	<br>

	<div class = "buttonall">
	<input type = "submit" name="add" value = "追　加" class = "button" id = "click_add">
	</div>





</form>

	<script src = "js/click_add.js" type = "text/javascript" charset = "UTF-8"></script>

	<script src = "js/TimeoutMessage.js" type="text/javascript"></script>


    <script type="text/javascript">
	function DetailLink(wbsName){
		var form = document.forms[0];
		var input = document.getElementById(wbsName)
		form.appendChild(input);
		document.body.appendChild(form);
		form.submit();
}
    </script>

     <script type="text/javascript">
        jQuery(document).ready(function(){
            jQuery("#formID").validationEngine();
        });
    </script>


</body>
</html>
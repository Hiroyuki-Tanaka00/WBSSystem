<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model.WBSBean" %>

<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<link rel="stylesheet" type="text/css" href="CSS/validationEngine.jquery.css">

<script src = "js/jquery-1.8.2.min.js" type = "text/javascript"></script>
<script src = "js/jquery.validationEngine.js" type = "text/javascript"></script>
<script src = "js/languages/jquery.validationEngine-ja.js" type = "text/javascript"></script>

<meta http-equiv = "Content-Type" content = "text/html" charset="UTF-8">
<title>タスク変更画面</title>
</head>

	<link rel="stylesheet" type="text/css" href="CSS/Input.css">

<body>

	<form action="WBSDetailCon" method="post"></form>

<% String userNameSession = (String)session.getAttribute("userName");
if(userNameSession == null){
	response.sendRedirect("Login.jsp");
}
%>

	<%String wbsName = (String)request.getAttribute("WBSNAME"); %>


	<!-- リクエストスコープからエラーメッセージを受け取る -->
<%String failureMessage = (String)request.getAttribute("updateFailure"); %>

<form action = "infoUpdateCon" method = "post"  id= "formID">
<div class="container">

	<input type = "hidden" name = "WBSNAME" value = "<%= wbsName %>" id = "<%= wbsName %>">
		 <a href="javascript:void(0)" onclick="DetailLink('<%= wbsName %>');">一覧</a>
</div>


	<h1>タスク変更画面</h1>



<div style="text-align: center; height:10px;">
<!-- エラーメッセージが存在するときだけ表示する -->
<% if (failureMessage != null) {%>

   <font id = 'TimeoutMessage' color='red'><%=failureMessage %></font>

<%
}
%>
</div>
<br>
<p>
<%
  Object result2 = request.getAttribute("beforeItemBean");
  WBSBean beforeTaskBean = null;
  if(result2 != null){
	  beforeTaskBean = (WBSBean)result2;
  }
%>
</p>
	<table border = "1">
	<caption>変更する項目を入力してください</caption>
	<thead>
	<tr>
		<th>項目</th>
		<th>入力欄</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>工程名</td>

		<td>
		<input type = "hidden" name = "planId" value = "<%= beforeTaskBean.getPlanId() %>" id = "<%= beforeTaskBean.getPlanId() %>">
		<input type = "text" name = "planName" size = "30" value = "<%= beforeTaskBean.getPlanName() %>"  class="validate[required]"> </td>
	</tr>
	<tr>
		<td>担当者名</td>
		<td><input type = "text" name = "userName" size = "30" value = "<%= beforeTaskBean.getUserName() %>" class="validate[required]"></td>

	</tr>
	<tr>
		<td>開始予定日</td>
		<td><input type = "date" name = "startPlanDay" size = "30" value = "<%= beforeTaskBean.getStartPlanDayStr() %>"class="validate[required]"></td>
	</tr>
	<tr>
		<td>終了予定日</td>
		<td><input type = "date" name = "endPlanDay" size = "30" value = "<%= beforeTaskBean.getEndPlanDayStr() %>" class="validate[required]"></td>
	</tr>
	<tr>
		<td>開始日</td>
		<td><input type = "date" name = "startDay" size = "30" value = "<%= beforeTaskBean.getStartDayStr() %>"></td>
	</tr>
	<tr>
		<td>終了日</td>
		<td><input type = "date" name = "endDay" size = "30" value = "<%= beforeTaskBean.getEndDayStr() %>" ></td>
	</tr>
	<tr>
		<td>工数</td>
		<td><input type = "number" name = "workLoad" size = "30" value = "<%= beforeTaskBean.getWorkload() %>" class="custom[number],min[1]"></td>
	</tr>
	</tbody>
	</table>

	<br>
	<br>

	<div class = "buttonall">
	<input type = "submit" name="update" value = "更　新" class = "button" id = "click_update">
	</div>

</form>



	<script src = "js/click_update.js" type = "text/javascript" charset = "UTF-8"></script>

	<script src = "js/TimeoutMessage.js" type="text/javascript"></script>


		<script type = "text/javascript">
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
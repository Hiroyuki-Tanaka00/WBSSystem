<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model.WBSBean" %>

<%@page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規ユーザ作成画面</title>
</head>

	<link rel="stylesheet" type="text/css" href="CSS/Input_User.css">
	<link rel="stylesheet" type="text/css" href="CSS/validationEngine.jquery.css">

	<script src = "js/jquery-1.8.2.min.js" type = "text/javascript"></script>
	<script src = "js/jquery.validationEngine.js" type = "text/javascript"></script>
	<script src = "js/languages/jquery.validationEngine-ja.js" type = "text/javascript"></script>

<body>

<% String userNameSession = (String)session.getAttribute("userName");
if(userNameSession == null){
	response.sendRedirect("Login.jsp");
}
%>

<%String failureMessage = (String)request.getAttribute("useraddFailure"); %>

<form action = "UserAddCon" method = "post"   id= "formID">

<input type = "hidden" name = "USERNAME" value = "<%= userNameSession %>" id = "<%= userNameSession %>">



<div class="container">
		 <a href="javascript:void(0)" onclick="UserListLink();">ユーザ一覧</a>

</div>

	<h1>新規ユーザ作成作成画面</h1>

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
</p>
	<table border = "1">
	<caption>ユーザ情報を入力してください</caption>
	<thead>
	<tr>
		<th>項目</th>
		<th>入力欄</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>ユーザ名</td>
		<td><input type = "text" name = "userNameInput" size = "30" value = "" class="validate[required],maxSize[20]"></td>

	</tr>
	<tr>
		<td>パスワード</td>
		<td><input type = "password" name = "passwordInput" size = "30" value = ""  class="validate[required],maxSize[16]"></td>

	</tr>
	<tr>
		<td>確認の為、パスワードの再入力をしてください</td>
		<td><input type = "password" name = "passwordInput1" size = "30" value = ""  class="validate[required],maxSize[16]"></td>

	</tr>
	</tbody>
	</table>

	<br>
	<br>

	<div class = "buttonall">
	<input type = "submit" name="add" value = "追　加" class = "button" id = "click_add">
	</div>

</form>

<script src = "js/click_update.js" type = "text/javascript" charset = "UTF-8"></script>

<script src = "js/TimeoutMessage.js" type="text/javascript"></script>


	<script type = "text/javascript">
	function UserListLink(	){
		var form = document.forms[0];
		document.body.appendChild(form);
		form.submit();
	}
	</script>

</body>
</html>
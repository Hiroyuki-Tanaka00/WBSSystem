<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<link rel="stylesheet" type="text/css" href="CSS/Style_Login.css">
<body>

<h1>ログイン画面</h1>

<!-- リクエストスコープからエラーメッセージを受け取る -->
<%String failureMessage = (String)request.getAttribute("loginFailure"); %>
<!-- エラーメッセージが存在するときだけ表示する -->
<div style="text-align: center; height:10px;">
<% if (failureMessage != null) {%>

   <font id = 'TimeoutMessage' color='red'><%=failureMessage %></font>

<%
}
%>
</div>
<br>

<form action="LoginCon" method="post">
  <p>ユーザー名：<input type="text" name="userName"></p>
  <p>パスワード：<input type="password" name="Password"></p>
  <p><input type="submit" value="ログイン"></p>
</form>

<script src = "js/TimeoutMessage.js" type="text/javascript"></script>

</body>
</html>
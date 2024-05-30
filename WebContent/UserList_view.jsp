<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model.WBSBean" %>

<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/User_List.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ一覧</title>
</head>
<body>

<form action="WBSListCon" method="post"></form>

<% String userNameSession = (String)session.getAttribute("userName");
if(userNameSession == null){
	response.sendRedirect("Login.jsp");
}
%>

<%String SuccessMessage = (String)request.getAttribute("useraddSuccess"); %>

<div class="container">
<a href="javascript:void(0)" onclick="WBSListLink('<%= userNameSession %>');">一覧</a>
<a href="javascript:void(0)" onclick="AddLink(<%= userNameSession %>);">追加</a>
</div>

<input type = "hidden" name = "USERNAME" value = "<%= userNameSession %>" id = "<%= userNameSession %>">

<h1>ユーザ一覧</h1>
<div style="text-align: center; height:10px;">
<!-- Successメッセージが存在するときだけ表示する -->
<% if (SuccessMessage != null) {%>

   <font id = 'TimeoutMessage' color='green'><%=SuccessMessage %></font>

<%
}
%>
</div>
<br>
<br>

<div class="table-contents">
  <table border="1">
    <thead>
      <tr>
        <th>ユーザID</th>
        <th>ユーザ名</th>
      </tr>
    </thead>
    <tbody>
      <%
    //リクエスト属性からWBSBeanのインスタンスを取得
      ArrayList<WBSBean> wb = (ArrayList<WBSBean>) request.getAttribute("userlists");
      for(WBSBean detail : wb) { %>
      <tr>
        <td><%=detail.getUserId() %></td>
        <td><%=detail.getUserName() %></td>
      </tr>
      <% } %>
    </tbody>
  </table>
</div>

	<script src = "js/TimeoutMessage.js" type="text/javascript"></script>

	<script type = "text/javascript">
	function WBSListLink(userNameSession){
			var form = document.forms[0];
			var input = document.getElementById("<%= userNameSession %>")
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();
	}
	</script>

			<script type = "text/javascript">
	function AddLink(){
		    var form = document.createElement("form"); // フォームを作成
		    form.method = "POST"; // メソッドを指定
		    form.action = "UserAdd.jsp"; // 送信先のURLを指定
			document.body.appendChild(form);
			form.submit();
	}
	</script>


</body>
</html>
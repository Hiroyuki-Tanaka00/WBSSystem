<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList" %>

	<!DOCTYPE html>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>プロジェクト一覧画面</title>
	</head>
	<link rel="stylesheet" type="text/css" href="CSS/Style_Login_Success.css">
	<body>


	<form action="WBSDetailCon" method="post"></form>
	<form action="UserListCon" method="post"></form>
	<form action="WBSCreateCon" method="post"></form>

	<%String userNameSession = (String)session.getAttribute("userName");
	if(userNameSession == null){
		response.sendRedirect("Login.jsp");
	}
	%>

	<%String SuccessMessage = (String)request.getAttribute("wbsaddSuccess"); %>

	<%String userName = (String)request.getAttribute("userName"); %>
	<%String wbsName = (String)request.getAttribute("WBSNAME"); %>
	<%ArrayList<String> wbsNamelist = (ArrayList<String>) request.getAttribute("WBSNAMELIST");%>

<div class="container">
<a></a>
<a href="LogoutCon" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
</div>

	<h1>ようこそ <%=userNameSession %>さん</h1>

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

<div class="wbsnamelist-table" <% if(wbsNamelist == null || wbsNamelist.size() == 0) { %>style="overflow:hidden;"<% } %>>
  <% if(wbsNamelist == null || wbsNamelist.size() == 0) { %>
    <p>参加しているプロジェクトはありません。</p>
  <% } else { %>
    <table border="1">
      <thead>
        <tr>
          <th>WBS名</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <% for (String wbsNames : wbsNamelist) { %>
          <tr>
            <td><%=wbsNames %></td>
            <td>
              <input type="hidden" name="WBSNAME" value="<%=wbsNames %>" id="<%=wbsNames %>">
              <a href="javascript:void(0)" class="button-link" onclick="DetailLink('<%=wbsNames %>');">詳細</a>
            </td>
          </tr>
        <% } %>
      </tbody>
    </table>
  <% } %>
</div>


<div class="admin_control">
    <%if (userNameSession != null){
        if (userNameSession.equals("admin")) {
    %>
            <h2>あなたは管理者です</h2>
            <a href="javascript:void(0)" onclick="UserListLink();">ユーザ一覧</a>
            <input type = "hidden" name = "WBSNAME" value = "<%= wbsName %>" id = "<%= wbsName %>">
            <a href="javascript:void(0)" onclick="WBSAddLink('<%= wbsName %>');">プロジェクト作成</a>
    <%
        }
    }
    %>
</div>
	</body>

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

	<script type = "text/javascript">
	function UserListLink(){
			var form = document.forms[1];
			document.body.appendChild(form);
			form.submit();
		}
	</script>

	<script type = "text/javascript">
	function WBSAddLink(wbsName){
		    var form = document.createElement("form"); // フォームを作成
		    form.method = "POST"; // メソッドを指定
		    form.action = "WBSAdd.jsp"; // 送信先のURLを指定
			var input = document.getElementById("<%= wbsName %>")
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();
		}
	</script>

	</html>
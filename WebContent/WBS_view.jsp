<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="model.WBSBean" %>

<%@page import="java.util.ArrayList" %>

<%@page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>タスク一覧</title>


</head>

	<link rel="stylesheet" type="text/css" href="CSS/WBS_view.css">


<body>

<%String SuccessMessage = (String)request.getAttribute("setSuccess"); %>

<%String wbsName = (String)request.getAttribute("WBSNAME"); %>

<% String userNameSession = (String)session.getAttribute("userName"); %>

<form action="infoUpdateCon" method="post"></form>

<form action="WBSListCon" method="post"></form>

<input type = "hidden" name = "WBSNAME" value = "<%= wbsName %>" id = "<%= wbsName %>">

<input type = "hidden" name = "USERNAME" value = "<%= userNameSession %>" id = "<%= userNameSession %>">



<div class="container">
<a href="javascript:void(0)" onclick="WBSListLink('<%= userNameSession %>');">一覧</a>
<a href="LogoutCon" onclick="return confirm('ログアウトしますか？')">ログアウト</a>
</div>

<h1>タスク一覧</h1>
<h2><%= wbsName %></h2>

<div style="text-align: center; height:10px;">
<!-- Successメッセージが存在するときだけ表示する -->
<% if (SuccessMessage != null) {%>

   <font id = 'TimeoutMessage'  color='green'><%=SuccessMessage %></font>

<%
}
%>
</div>

	<div class="table-contents">
  	<table border = "1">

    <thead>
      <tr>
        <th>工程名</th>
        <th>担当者名</th>
        <th>開始予定日</th>
        <th>終了予定日</th>
        <th>開始日</th>
        <th>終了日</th>
        <th>予定工数</th>
        <th>工数</th>
        <th>状況</th>
        <th>項目選択</th>
      </tr>
    </thead>
<%
// リクエスト属性からWBSBeanのインスタンスを取得
ArrayList<WBSBean> wb = (ArrayList<WBSBean>) request.getAttribute("detaillists");
if (wb == null || wb.isEmpty()) {
%>
    <tbody>
      <tr>
        <td colspan="10">データがありません。
               新規データを追加してください
        <a href="javascript:void(0)" onclick="AddLink();">追加</a>
        </td>


      </tr>
    </tbody>
<%
} else {
  for(WBSBean detail : wb) {
%>

        <tbody>
      <tr>
       <th><%=detail.getPlanName() %></th>
       <th><%=detail.getUserName() %></th>
       <th><%=new SimpleDateFormat("yyyy-MM-dd").format(detail.getStartPlanDay())%></th>
       <th><%=new SimpleDateFormat("yyyy-MM-dd").format(detail.getEndPlanDay())%></th>
		<th>
		  <% if (detail.getStartDay() == null) { %>
		    入力なし
		  <% } else { %>
		    <%=new SimpleDateFormat("yyyy-MM-dd").format(detail.getStartDay())%>
		  <% } %>
		</th>
		<th>
		  <% if (detail.getEndDay() == null) { %>
		    入力なし
		  <% } else { %>
		    <%=new SimpleDateFormat("yyyy-MM-dd").format(detail.getEndDay())%>
		  <% } %>
		</th>
		<th><%=detail.getWorkPlanload() %></th>
				<th>
		  <% if (detail.getWorkload() == 0) { %>
		    入力なし
		  <% } else { %>
		    <%= detail.getWorkload() %>
		  <% } %>
		</th>
		<th>
		  <% if (detail.getStartDay() != null && detail.getEndDay() != null) { %>
		    完了
		  <% } else if (detail.getStartDay() != null) { %>
		    進行中
		  <% } else { %>
		  	未進行
		  <% } %>
		</th>
		<th>
		<input type = "hidden" name = "planId" value = "<%= detail.getPlanId() %>" id = "<%= detail.getPlanId() %>">
        <a href="javascript:void(0)" onclick="UpdateLink(<%= detail.getPlanId() %>);">更新</a>
        <a href="javascript:void(0)" onclick="AddLink();">追加</a>
		</th>
      </tr>
 <%
    }
}
%>
</tbody>
	</table>
	</div>

	<script src = "js/TimeoutMessage.js" type="text/javascript"></script>

	<script type = "text/javascript">
	function UpdateLink(planId){
			var form = document.forms[0];
			var input = document.getElementById(planId)
			var input1 = document.getElementById("<%= wbsName %>")
			form.appendChild(input);
			form.appendChild(input1);
			document.body.appendChild(form);
			form.submit();
	}
	</script>

	<script type = "text/javascript">
	function WBSListLink(userNameSession){
			var form = document.forms[1];
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
		    form.action = "TaskAdd.jsp"; // 送信先のURLを指定
			var input = document.getElementById("<%= wbsName %>")
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();
	}
	</script>

</body>

</html>
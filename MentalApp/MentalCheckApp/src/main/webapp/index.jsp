<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<h1>メンタルチェック</h1>
<p>ログイン</p>
<%String errorMessage = (String) request.getAttribute("error"); %>
<%if(errorMessage != null) {%>
<p style="color:red;"><%=errorMessage %></p>
<%} %>
<form method="post" action="loginServlet">
	<input type="text" name="name" id="name" placeholder="名前"required><br>
	<input type="password" name="password" id="password" placeholder="パスワード" required><br>
	<button type="submit">ログインする</button>
</form>
<p>新規登録はこちら</p>
<p class="arrow">↓↓↓</p>
<a href="registerServlet"><button type="button">登録フォームへ</button></a>
</body>
</html>
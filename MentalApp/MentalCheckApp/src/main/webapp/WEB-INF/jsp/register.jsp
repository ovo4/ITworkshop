<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="css/register.css">
</head>
<body>
<h1>新規アカウント作成</h1>
<form method="post" action="registerServlet">
<input type="text" name="name" id="name" placeholder="名前"required><br>
<input type="password" name="password" id="password" placeholder="パスワード" required><br>
<button type="submit">登録する</button>
</form>
</body>
</html>
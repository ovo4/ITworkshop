<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="Dao.UserDao" %>
<%@ page import="model.User" %>
<%@ page import="model.Question" %>
<%
    UserDao userDao = new UserDao();
    ResultSet rs = userDao.getAllQuestions();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンタルチェック</title>
<link rel="stylesheet" href="css/userMain.css">
</head>
<body>
<h1>メンタルチェック</h1>
<h2>ようこそ、${sessionScope.user.name}さん！</h2><br>
<div class="logout">
<a href="logoutServlet">
	<button type="button">ログアウト</button>
</a>
</div>
<h2>以下の質問に答えて、メンタルチェックを行いましょう</h2>

<form action="mentalCheckServlet" method="POST">
    <%
        while (rs.next()) {
            String questionText = rs.getString("question_text");
            String option1Text = rs.getString("option_1_text");
            String option2Text = rs.getString("option_2_text");
            String option3Text = rs.getString("option_3_text");
            String option4Text = rs.getString("option_4_text");
            int questionId = rs.getInt("question_id");
    %>
    <div class="question-container">
    	<p class="question-text"><%= questionText %></p>
    <div class="option-container">
    		<div>
    			<input type="radio" name="question_<%= questionId %>" value="10" id="question_<%= questionId %>_1">
    			<label for="question_<%= questionId %>_1"><%= option1Text %></label>
    		</div>
    		<div>
                <input type="radio" name="question_<%= questionId %>" value="7" id="question_<%= questionId %>_2">
                <label for="question_<%= questionId %>_2"><%= option2Text %></label>
            </div>
            <div>
                <input type="radio" name="question_<%= questionId %>" value="5" id="question_<%= questionId %>_3">
                <label for="question_<%= questionId %>_3"><%= option3Text %></label>
            </div>
            <div>
                <input type="radio" name="question_<%= questionId %>" value="3" id="question_<%= questionId %>_4">
                <label for="question_<%= questionId %>_4"><%= option4Text %></label>
            </div>
          </div>
        </div>
    <%
        }
    %>
    <input type="submit" value="結果を送信">
</form>
</body>
</html>

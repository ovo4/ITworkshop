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
</head>
<body>
<h1>メンタルチェック</h1>

<h2>ようこそ、${sessionScope.user.name}さん！</h2><br>
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
    <p><%= questionText %></p>
    <input type="radio" name="question_<%= questionId %>" value="10"><%= option1Text %><br>
    <input type="radio" name="question_<%= questionId %>" value="7"> <%= option2Text %><br>
    <input type="radio" name="question_<%= questionId %>" value="5"> <%= option3Text %><br>
    <input type="radio" name="question_<%= questionId %>" value="3"> <%= option4Text %><br>
    <%
        }
    %>
    <input type="submit" value="結果を送信">
</form>
</body>
</html>

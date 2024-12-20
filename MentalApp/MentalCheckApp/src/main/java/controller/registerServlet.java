package controller;

import java.io.IOException;

import Dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/registerServlet")
    

    public class registerServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // 新規登録フォームを表示するJSPに遷移
            request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
        }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("name");
            String password = request.getParameter("password");

            // ユーザー情報をUserオブジェクトに設定
            User user = new User();
            user.setName(name);
            user.setPassword(password);

            // データベースにユーザー登録
            UserDao userDao = new UserDao();
            userDao.registerUser(user);

            // 登録完了後、ログイン画面にリダイレクト
            response.sendRedirect("index.jsp");
        }
    }

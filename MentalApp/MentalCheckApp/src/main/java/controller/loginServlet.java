package controller;

import java.io.IOException;

import Dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name = request.getParameter("name");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(name);

        if (user != null && user.getPassword().equals(password)) {
            // ログイン成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if ("ADMIN".equals(user.getRole())) {
                // 管理者の場合は管理者用メイン画面にリダイレクト
            	request.getRequestDispatcher("WEB-INF/jsp/adminMain.jsp").forward(request, response);
            } else {
                // 一般ユーザーの場合はユーザー用メイン画面にリダイレクト
            	request.getRequestDispatcher("WEB-INF/jsp/userMain.jsp").forward(request, response);
            }
        } else {
            // ログイン失敗
            request.setAttribute("error", "無効なユーザーIDまたはパスワードです");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

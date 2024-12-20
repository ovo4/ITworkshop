package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import com.google.gson.Gson;

import Dao.MentalCheckDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MentalCheckResult;
import model.User;

@WebServlet("/mentalCheckServlet")
public class mentalCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションからユーザー情報を取得
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUser_id();
        
        // メンタルチェックのスコア計算
        int totalScore = 0;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("question_")) {
                String score = request.getParameter(paramName);
                totalScore += Integer.parseInt(score);
            }
        }

        // メンタルチェック結果を保存
        MentalCheckDao mentalCheckDao = new MentalCheckDao();
        mentalCheckDao.saveMentalCheckResult(String.valueOf(userId), totalScore);
        ResultSet resultSet = mentalCheckDao.getMentalCheckResultsByUserId(userId);
        List<MentalCheckResult> pastResults = mentalCheckDao.getPastResults(userId);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(MentalCheckResult result : pastResults) {
        	String formattedDate = sdf.format(result.getDate());
        	 result.setFormattedDate(formattedDate);
        }
        
        Gson gson = new Gson();
        String jsonPastResults = gson.toJson(pastResults);

        
        String resultMessage = "";
        if(totalScore >= 85) {
        	resultMessage ="素晴らしい精神状態です！！ポジティブな気持ちで日々を過ごせています、この状態を維持しましょう！";
        }else if(totalScore >= 70) {
        	resultMessage ="良好です！少しストレスや困難を感じることはありますが適切に対処出来ています。無理せず自分のペースで過ごしましょう。";
        }else if(totalScore >= 40) {
        	resultMessage ="ストレスを感じています、疲れや不安がたまってきているかもしれません。少し休息をとりましょう。";
        }else {
        	resultMessage ="改善が必要な状態です。一人で抱え込まず、信頼できる人や専門家のサポートを受けることを勧めます。";
        }

        // 結果をJSPに渡して表示
        request.setAttribute("totalScore", totalScore);
        request.setAttribute("resultMessage",resultMessage);
        request.setAttribute("resultSet", resultSet);
        request.setAttribute("pastResults", jsonPastResults); 
        request.getRequestDispatcher("/WEB-INF/jsp/mentalCheckResult.jsp").forward(request, response);
    }
}

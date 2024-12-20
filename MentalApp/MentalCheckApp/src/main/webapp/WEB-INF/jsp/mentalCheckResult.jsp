<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンタルチェック結果</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<h1>メンタルチェック結果</h1>

<h2>あなたの合計ポイント: ${requestScope.totalScore}</h2>
<h3>結果:</h3>
<p>${requestScope.resultMessage}</p>

<canvas id="resultchart" width="200" height="100"></canvas>

<script>

var pastResults = JSON.parse('${requestScope.pastResults}'); 
var labels = pastResults.map(function(result) {
    var date = new Date(result.formattedDate);
    return date.toLocaleDateString(); 
});
var data = pastResults.map(function(result) {
    return result.score;
});


labels.push("今回の結果");
data.push(${requestScope.totalScore}); 


var ctx = document.getElementById('resultchart').getContext('2d');


var resultChart = new Chart(ctx, {
    type: 'bar', 
    data: {
        labels: labels,
        datasets: [{
            label: 'メンタルチェックスコア',
            data: data, 
            backgroundColor: 'rgba(75, 192, 192, 0.2)', 
            borderColor: 'rgba(75, 192, 192, 1)', 
            borderWidth: 1 
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true 
            }
        }
    }
});
</script>
<a href="logoutServlet">
	<button type="button">ログアウト</button>
</a>
</body>
</html>

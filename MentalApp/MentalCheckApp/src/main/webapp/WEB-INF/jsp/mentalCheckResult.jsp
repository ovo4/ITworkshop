<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>メンタルチェック結果</title>
    <link rel="stylesheet" href="css/mentalCheckResult.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <header>
        <h1>メンタルチェック結果</h1>
    </header>

    <main>
     
        <section class="logout-container">
            <a href="logoutServlet" aria-label="ログアウト">
                <button type="button">ログアウト</button>
            </a>
        </section>

        
        <section class="result-summary">
            <h2>あなたの合計ポイント: ${requestScope.totalScore}</h2>
            <h3>結果</h3>
            <p>${requestScope.resultMessage}</p>
        </section>

        <section class="result-chart-container">
            <canvas id="resultchart" width="400" height="200"></canvas>
        </section>
    </main>

    <script>
        
        const pastResults = JSON.parse('${requestScope.pastResults}');
        const labels = pastResults.map(result => new Date(result.formattedDate).toLocaleDateString());
        const data = pastResults.map(result => result.score);

        
        labels.push("今回の結果");
        data.push(${requestScope.totalScore});

        
        const ctx = document.getElementById('resultchart').getContext('2d');
        new Chart(ctx, {
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
</body>
</html>

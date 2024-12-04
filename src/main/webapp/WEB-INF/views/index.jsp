<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 50px;
        text-align: center;
    }
    table {
        width: 60%;
        margin: auto;
        border-collapse: collapse;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }
    th, td {
        padding: 8px;
        text-align: center;
        border: 1px solid #ddd;
    }
    th {
        background-color: #4CAF50;
        color: white;
    }
    button {
        width: 300px;
        height: 50px;
        background-color: skyblue;
        border-radius: 5px;
        font-size: 20px;
    }
    p {
        font-size: 22px;
        font-weight: bold;
        margin-bottom: 15px;
    }
</style>
<body>
    <h1>팝업 허용을 요청합니다.</h1><br><br>
    <button onclick="psadmin_open()">관리자 페이지 열기</button>
    <br>
    <p>로그인 계정 견본</p>
    <table>
        <thead>
            <tr>
                <th>아이디</th>
                <th>비밀번호</th>
                <th>구분</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>admin1</td>
                <td>pensionmaster1234</td>
                <td>관리자</td>
            </tr>
            <tr>
                <td colspan="3">관리자 계정은 로그인 화면에 넣어두었습니다.</td>
            </tr>
            <tr>
                <td>hong</td>
                <td>h1234</td>
                <td>사용자</td>
            </tr>
            <tr>
                <td>parkpark</td>
                <td>park1234</td>
                <td>사용자</td>
            </tr>
        </tbody>
    </table>
</body>
<script>
    function pension_open() {
        window.open("http://pensionlife.seongmin.site/main", "", "width=550, height=900");
    }
    function psadmin_open() {
        window.open("http://pensionlife.seongmin.site/admin", "", "width=550, height=900");
    }
    pension_open();
</script>
</html>
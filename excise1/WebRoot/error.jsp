<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
    <title>出错了</title>
    <link rel="stylesheet" href="css/error.css">
    <script language="javaScript" src="js/error.js"></script>
</head>
<body>
  <div id="errorDiv">
    <div id="errorHint">
      <p id="errorInfo">${info}</p>
      <p><span id="leaveTime">10</span>秒后自动返回到登录界面</p>
      <p>不能跳转，请<a href="login.html">点击这儿</a></p>
    </div>
  </div>
</body>
</html>

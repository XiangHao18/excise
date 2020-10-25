<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
    <title>系统主页</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
</head>
<body>
  <div id="container">
    <div id="header">
      <div id="rightTop">
        当前用户：<span>${currentUser.chrName}</span> &nbsp;[<a href="logout.do">安全退出</a>]
      </div>

      <div id="menu">
        <ul>
          <li><a href="#">首页</a></li>
          <li class="menuDiv"></li>
          <li><a href="getDownloadList.do">资源下载</a></li>
          <li class="menuDiv"></li>
          <li><a href="userController.jsp">用户管理</a></li>
          <li class="menuDiv"></li>
          <li><a href="srcController.jsp">资源管理</a></li>
          <li class="menuDiv"></li>
          <li><a href="userHome.jsp">个人中心</a></li>
        </ul>
      </div>
      <div id="banner">
      </div>
    </div>
  </div>
</body>
</html>

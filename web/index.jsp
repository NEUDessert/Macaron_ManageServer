<%--
  Created by IntelliJ IDEA.
  User: killeryuan
  Date: 2016/9/10
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <a href="/user/getUsername.do">得到已登录的名字</a><br>
    <a href="/user/login.do?username=killeryuan&password=wang200424">登录</a>
    <a href="/user/register.do?username=killeryuan&password=wang200424&email=497112006@qq.com&phone=15734003447">注册（用户名存在）</a>
    <a href="/user/register.do?username=lichenxi&password=li960324&email=li@qq.com&phone=18842569699">注册（用户名不存在）</a>
  </body>
</html>

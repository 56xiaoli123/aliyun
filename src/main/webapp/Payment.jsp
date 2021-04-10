<%--
  Created by IntelliJ IDEA.
  User: ckw
  Date: 2020/12/15
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
<c:when test="${error!=null}">
    支付失败
</c:when>
<c:otherwise>
    支付成功<br>
    商户订单号: ${out_trade_no}<br>
    支付宝交易号: ${trade_no}<br>
    付款金额:  ${total_amount}<br>
</c:otherwise>
</c:choose>


    <a href="index.jsp">返回首页</a>
</body>
</html>

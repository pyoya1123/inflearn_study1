<%--<%@ page import="hello.servlet.domain.member.Member" %> 이젠 얘도 필요가 없음.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    밑에 Member로 캐스팅을 해주는 이유는 getAttribute 반환 타입이 object임.--%>
<%--    <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>--%>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
<%--    위와 같은 식으로 .id, .username 이런식으로 조회하면 자동으로 getId, getUsername 가 호출이 됨.--%>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% // 이렇게 하면 여기에 자바 코드 넣을 수 있음

    //request, response는 그냥 사용이 가능함.
    // jsp도 결국 servlet으로 자동으로 변환됨. 그래서 그 서비스 로직이 그냥 그대로 호출이 된다고 이해하면 됨.

    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<%--밑에 있는 html 코드는 write를 이용해서 println으로 다 찍어버리는거임.--%>
<%-- % 표시가 없는 html 코드는 http response 에 다 담기는거임.--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<%--여기서 주의할 점은 밑에 action의 경로를 save로 했다는거임. 이거는 다른 곳에서도 save에 호출하기 때문에 이렇게 상대경로로 설정한거임..--%>
<%--만약에 /save로 하면, local~~/save로 이동하는데, /를 빼면 현재 local~/servlet-mvc/members/new-from 경로에서 local~/servlet-mvc/members/save 로 바뀜 --%>
<%--이것을 상대경로라고함. 보통은 절대 경로가 좋다고 하심.--%>
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
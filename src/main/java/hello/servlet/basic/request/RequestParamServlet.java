package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/*
1. 파라미터 전송 기능
http://localhost:8080/request-param?username=hello&age=20

 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

        // name은 키고, value를 또 봅아야됨.
        // request.getParameter라고 하면, 여기서 paramName은 이름을 꺼내는거고, 여기서 이름은 username=hello에서 username임.
        // username에 속한 hello를 꺼내려면 request.getParameter(paramName)해서 키를 넣어주면 값을 꺼낼 수 있음.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println();

        // http://localhost:8080/request-param?username=hello&age=20&username=hello2
        // 위와 같은 방식으로 같은 이름에 여러개의 값이 들어갈 수 있음.
        // 이렇게하면 그냥 내부 우선순위에서 먼저 잡히는 애가 나옴
        // 이 경우에는 밑에와 같은 방식으로 모두 출력 가능
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for(String name : usernames){
            System.out.println("name = " + name);
        }

        response.getWriter().write("ok");
    }
}

package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // 서블릿이 호출되면 이 서비스 메소드가 호출이 됨.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 서블릿 http 요청이 오면 was인 servlet container가 request response 객체를 만들어서 이 서블릿에 던져줌.
        // 그래서 /hello 라고 url에 호출하면 웹 브라우저가 HTTP 요청 메시지를 만듬. 그리고 이거를 서블릿에다가 던짐.

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답 메시지를 한번 보내볼거임.
        // 응답은 HttpServletResponse에 찍어줌.
        // 여기에 뭔가 값을 넣으면 웹 브라우저에 응답하는 response-http 응답 메시지에 데이터가 담겨서 나가게 됨.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // 위에 두개는 컨텐트 타입, 헤더 정보에 들어감.
        response.getWriter().write("hello " + username); // write를 쓰면 HTTP 메세지 바디에 데이터가 들어감.
    }
}

/*
먼저 스프링 부트를 가지고 일단 우리가 스프링 부트를 실행했음.
스트링 부트를 실행하면서 내장 톰켓 서버가 스프링 부트가 내장 톰켓 서버를 띄워줌.
그러면서 톰켓 서버는 내부에 서블릿 컨테이너 기능을 가지고 있음.
그러면서 이 서블릿 컨테이너를 통해서 그 서블릿을 다 생성 해줌.
그리고 서블릿 컨테이너 안에 위에서 선언했던 HelloServlet이 생성이 된거임.
 */

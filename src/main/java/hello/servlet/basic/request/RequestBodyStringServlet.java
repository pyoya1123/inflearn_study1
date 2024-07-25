package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // 메세지 바디의 내용을 바이트 코드로 얻을 수가 있음.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 위에를 스트링으로 바꿔야됨.
        //항상 BYTE를 문자로 변환할 때는 어떤 인코딩인지 알려줘야 함.
        // 반대로 문자를 byte로 바꿀 때도 어떤 인코딩인지 알려줘야 됨. 지금은 대부분  UTF-8 사용함.
        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}

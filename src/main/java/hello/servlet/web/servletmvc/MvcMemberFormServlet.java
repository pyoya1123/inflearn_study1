package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 애가 이제 컨트롤러가 될거임
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    /*
    제일 처음에 할게 뭐냐면, 멤버 폼을 보여주고 싶음.
    MVC 패턴을 쓰면 항상 컨트롤을 거쳐서 뷰로 들어가야됨.
    그러면 컨트롤러에 일단 요청이 다 들어와야됨.
     */

    // 고객 요청이 오면 밑에 service가 호출이 될거임.
    // 그다음에 service가 밑에 viewPath인 jsp 경로를 다시 호출함.
    // 그러면 서버 내부에서 서버끼리 호출함.
    // 저기로 제어권을 넘겨주는거임.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);// 컨트롤러에서 뷰로 이동해야됨. 그때 사용하는거임.
        dispatcher.forward(req,resp); // 이걸 호출하면 진짜 그 servlet에서 JSP를 호출할 수 있음. 이렇게 하면 그 jsp를 찾아서 넘어가서 호출이 됨.
    }
//    forward는  클라이언트 웹 브라우저 입장에서 보면 한 번 호출하고 응답 그냥 바로 받은거임
    // 호출하고, 그냥 서버에서 w 호출하고, view 호출하고, HTTP 응답 코드를 만들어서 보내고
    // 그런데 redirect는 보내고 받았더니 redirect네? 하고 보내고 받고 이렇게 해서 두번임.
}

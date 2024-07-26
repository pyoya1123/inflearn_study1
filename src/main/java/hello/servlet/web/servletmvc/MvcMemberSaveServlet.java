package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 요청 정보 받고
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age")); // req.getParameter의 응답 결과는 항상 문자임. 그래서 숫자는 숫자 타입으로 변환 해줘야함.

        // 비즈니스 로직을 컨트롤러가 호출해줬음.
        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보관한다. 왜냐하면 이 밑에 이제 jsp view가 나와야됨.
        // name을 member라고 주고, member 객체를 넣어주면, 리퀘스트 객체의 내부 저장소가 있다고 했는데,
        // 여기 내부에 맵 같은게 있음. 여기에다가 멤버라는게 저장이 됨.
        req.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);// viewPath로 넘어가겠다는 거임.
        dispatcher.forward(req,resp); // 얘는 내부에서 호출한다고 했음. 이러면 뷰가 넘어가고, save-result.jsp로 갈거니까, jsp파일을 만들어줘야함.

    }
}

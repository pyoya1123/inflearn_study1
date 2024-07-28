package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// * 부분에 어떤 링크가 들어오든 다 이 서블릿이 호출됨.
@WebServlet(name="frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // Url을 넣고, 이 컨트롤러 v1을 넣을거임.
    // 키는 "어떤 URL을 호출이 되면 컨트롤러 v1을 꺼내서 호출해" 이런식으로 구현할거임
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 이게 매핑 정보임.
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // 이렇게 하면 http://localhost:8080/front-controller/v1/members/hello 여기서 front-controller/v1/members/hello 이 부분을 얻어올 수 있음.
        String requestURI = request.getRequestURI();

        // 만약에 /front-controller/v1/members/new-form 이 url이 요청되었다면, 그러면 MemberFormControllberV1 객체 인스턴스가 반환이 될거임.
        ControllerV1 controller = controllerMap.get(requestURI); // 어쨌든 인터페이스로 꺼내게 되면 이 코드로 굉장히 일관성 있게 사용할 수 있음.
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response); // 반환된 MemberFormControllerV1 객체에서 오버라이드된 process 메서드가 호출이 될거임.
    }
}

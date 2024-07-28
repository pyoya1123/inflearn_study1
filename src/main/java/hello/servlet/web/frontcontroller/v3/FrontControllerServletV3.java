package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap
        Map<String, String> paramMap = createParamMap(request);
        paramMap.forEach((k,v) -> {
            System.out.println(k + ":" + v + "@@@@@@@");
        });

        ModelView mv = controller.process(paramMap);
        // 컨트롤 호출하고, 모델 뷰 받는거까지 다 됐음. 이제 뭘 해야되냐?
        // 논리 이름을 물리 이름으로 바꿔야됨. 지금 이름만 넘어가는데, 이걸 가지고 MyView를 반환을 해줘야함.
        // 그래서 ViewResolver라는 기능이 있는거임. 실제 view를 찾아주는 해결자 역할을 함.

        String viewName = mv.getViewName();// 논리이름 ex) new-form
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String,String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        /*
        request.getParameterNames():
        요청에 포함된 모든 파라미터 이름을 Enumeration<String> 형태로 가져옵니다. 여기서는 username과 age가 포함됩니다.

        .asIterator():
        Enumeration 객체를 Iterator로 변환합니다. 이 Iterator는 username과 age라는 두 개의 요소를 가집니다.

        .forEachRemaining(paramName -> ...):
        Iterator는 두 번 반복되며, 각각의 반복에서 paramName은 한 번 username, 다음번에 age가 됩니다.

        따라서, 주어진 예시에서 paramName은 처음에는 username, 다음에는 age가 됩니다.

        이 과정을 통해서 paramMap에는 다음과 같은 내용이 저장됩니다:
        paramMap.put("username", "a")
        paramMap.put("age", "1")
        결국 paramMap은 {"username": "a", "age": "1"}이 됩니다.
         */


        return paramMap;
    }
}

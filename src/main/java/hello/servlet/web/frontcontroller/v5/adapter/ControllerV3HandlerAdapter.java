package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); // 이 컨트롤러 V3 인터페이스를 구현한 뭔가가 넘어오게 되면 얘는 참을 반환함.
    }

    // handle은 실제로 돌리는거임.
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV3 controller = (ControllerV3) handler;
        // 위에처럼 캐스팅을 해도 괜찮음. 왜냐하면 위에 supports에서 컨트롤러 v3만 지원한다고 했음. 그렇기 때문에 실제 호출이 될 때 이 핸들이 호출이 될텐데,
        // 그럴 때 supports로 한 번 걸렀기 때문에, 얘가 걸리는건 이제 앞에서 프론트 컨트롤러에서 걸렸고, 프론트 컨트롤러에서 서포트도 호출하고 핸들도 호출할거임.
        // supports를 호출해서, 걸러진 애를 찾아서, 그 다음에 걔를 가지고 핸들을 호출하기 때문에 여기 핸들을 v3라고 확정이 된거임. 그래서 캐스팅 해도 됨.

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    /*
    그러니까 이 어댑터의 역할이, 핸들러를 호출해주고 그 결과가 옴. 그 반환 타입을 Model View로 맞춰서 반환을 해줘야함.
    v3는 지금 모델뷰를 반환하기 때문에 그대로 딱딱 맞음.
    근데 나중에 v4는 스트링을 반환했었음. 논리적인 뷰 이름을 반환했던거 기억하면 됨.
    그렇기 떄문에 v4일 땐 로직이 달라질 거임.
     */

    private Map<String,String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

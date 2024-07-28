package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // 우리가 만든 프레임워크에 종속적인거지, 서블릿에 종속적이지 않음.
    ModelView process(Map<String, String> paramMap);
}

package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

/*
클라이언트가 http 요청을 하면, 먼저 프론트 컨트롤러가 받고, 이 맵핑 정보에서 나한테 맵핑된 컨트롤을 찾음.
그래서 찾아서 그 컨트롤을 호출하고, 그 컨트롤은 당연히 view로 포워드 할거임.
그러면 jsp가 렌더링 되면서 클라이언트는 html 응답을 받게 될거임.

여기 인터페이스가 중요하다고 했는데, 컨트롤들이 다 interface를 맞춰서 구현을 할거임.
 */
package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // jsp로 이동하고 막 이런거를 렌더링 한다고 표현할거임. 뭔가 실제 view가 렌더링 되도록 동자갛는거 httpServletRequest를 받고
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request); // request에 key, value를 다 담아놓음.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // model에 있는 데이터를 request attribute로 바꾼다.
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        // jsp는 attribute에서 값을 꺼내기 때문에 model에 있는 값들을 setAttribute로 해서 값을 설정해줘야함.
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}

package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
스프링부트가 자동 등록하는 핸들러 매핑과 핸들러 어댑터가 있음.
0순위는 RequestMappingHandlerMapping임. 이게 뭐냐면, 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용함.
근데 이러한 애노테이션이 없다면 밑의 과정이 실행이되는거임. 어? @RequestMapping 없고, @Controller 없고,이러면 0순위는
무시가 되고, 1순위 매핑을 봤는데 어? 너 있네 이렇게 되는거임.
1 순위는 BeanNameUrlHandlerMapping인데, 이게 뭐냐면,
스프링 빈의 이름으로 핸들러를 찾는거임.
즉, 밑에 처럼 "/springmvc/old-controller 라는 이름의 스프링 빈을 찾는 핸들러 매핑을 등록함.
 */
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}

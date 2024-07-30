package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping
    public ModelAndView save() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);

        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {
        // http request에서 막 getparameter로 해서 꺼내는게 아니라,
        // 프론트 컨트롤에서 그런건 다 처리하고, 이 맵에다가 요청 파라미터 정보를 다 넣어서 넘겨줄거임.
        // 그러면 여기선 단순히 꺼내서 쓰기만 하면 됨.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}

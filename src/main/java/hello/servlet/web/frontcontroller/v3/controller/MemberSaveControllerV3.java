package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // http request에서 막 getparameter로 해서 꺼내는게 아니라,
        // 프론트 컨트롤에서 그런건 다 처리하고, 이 맵에다가 요청 파라미터 정보를 다 넣어서 넘겨줄거임.
        // 그러면 여기선 단순히 꺼내서 쓰기만 하면 됨.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member); // 저장된 결과로 member을 넣어줌.
        return mv;
    }
}

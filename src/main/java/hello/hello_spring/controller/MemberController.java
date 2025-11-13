package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;
    // new memberService로 여러 컨트롤러에서 생성한다면 인스턴스를 여러개 생성하는걸
    // 아래와 같은 방법으로 하나만 두고 여러 컨트롤러에서 공용으로 사용

    @Autowired // Autowired를 사용하면 스프링 컨테이너에 있는 멤버 서비스를 가져와서 연결 시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

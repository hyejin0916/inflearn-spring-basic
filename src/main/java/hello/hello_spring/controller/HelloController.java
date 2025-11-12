package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // '/hello' 경로로 들어오는 GET 요청
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // 스프링 MVC의 ViewResolver가 resources/templates/hello.html 뷰 파일을 찾아 렌더링 지시
    }
}

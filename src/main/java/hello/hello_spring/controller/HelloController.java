package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // '/hello' 경로로 들어오는 GET 요청
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // 스프링 MVC의 ViewResolver가 resources/templates/hello.html 뷰 파일을 찾아 렌더링 지시
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // 파라미터로 받아옴('?name=입력값'으로 조회), model에 담으면 view에서 렌더링할 때 씀
        model.addAttribute("name", name);
        // model → 컨트롤러에서 뷰로 데이터를 전달할 때 사용하는 객체
        // addAttribute(key, value) → 데이터를 키-값 쌍으로 추가
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 'http body에 내용을 직접 넣어주겠다.' 라는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // html 코드가 아닌 hello ${name} 이 그대로 response
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) { // 'Hello'는 반환 타입
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체는 json 타입으로 response
    }

    static class Hello { // static: 클래스안에서 클래스를 사용 가능(HelloController.Hello)
        private String name;

        public String getName() { // 프로퍼티 접근 방식
            return name;
        }

        public void setName(String name) { // 프로퍼티 접근 방식
            this.name = name;
        }
    }
}

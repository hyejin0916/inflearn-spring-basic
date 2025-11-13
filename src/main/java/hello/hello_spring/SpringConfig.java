package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // memberService를 스프링 빈에 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // memberRepository를 스프링 빈에 등록
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

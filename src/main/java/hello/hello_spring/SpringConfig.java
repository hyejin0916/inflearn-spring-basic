package hello.hello_spring;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // memberService를 스프링 빈에 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // memberRepository를 스프링 빈에 등록
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }
}

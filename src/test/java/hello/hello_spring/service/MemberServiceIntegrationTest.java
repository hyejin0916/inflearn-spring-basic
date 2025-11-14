package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트 끝나면 데이터 롤백해주는 역할
class MemberServiceIntegrationTest {

    // test 할때는 constructor를 이용해서 의존성 주입할 필요까지 없고 @Autowired로 편하게 진행
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given (뭔가가 주어졌는데)
        Member member = new Member();
        member.setName("spring");

        // when (이걸 실행했을 때)
        Long saveId = memberService.join(member);

        // then (결과가 이게 나와야 한다.)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 멤버2를 넣으면 예외가 터져야 한다.

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}
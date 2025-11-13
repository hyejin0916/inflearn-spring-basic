package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // Assertions.assertThat() -> assertThat()으로 사용 가능

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =  new MemoryMemberRepository();

    @AfterEach // 메서드가 실행이 끝날 때 마다 동작
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Optional에서 값을 꺼낼 때는 get() 사용 -> 권장하지 않지만 테스트 코드에서는 가능
        assertThat(member).isEqualTo(result);
        // member와 result가 같은지 확인할 수 있음
    }

    @Test
    public void findByName() {
        // member 생성
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // member 생성
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

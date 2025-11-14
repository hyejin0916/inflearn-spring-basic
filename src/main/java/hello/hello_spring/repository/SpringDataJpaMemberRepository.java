package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 interface를 받을 때는 implement가 아닌 extends를 사용
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
// 스프링 데이터 JPA가 JpaRepository를 받고 있으면 구현체를 자동으로 만들어줌
// 스프링 데이터 JPA가 구현체를 만들어서 스프링 빈에 자동으로 등록

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}

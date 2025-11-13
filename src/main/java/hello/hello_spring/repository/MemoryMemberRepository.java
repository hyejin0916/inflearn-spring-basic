package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional.ofNullable null을 반환할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // stream(): 리스트를 하나씩 처리
                .filter(member -> member.getName().equals((name)))
                .findAny(); // 조건에 맞는 요소가 여러개 있더라도 아무거나 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); // store를 비움
    }
}

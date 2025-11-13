package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    // memberRepository를 외부에서 넣어주도록 설정(DI)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);

        // ifPresent: 값이 있으면 로직 동작
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

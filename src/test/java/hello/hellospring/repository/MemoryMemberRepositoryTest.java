package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("hspark1");

        Member member2 = new Member();
        member2.setName("hspark2");

        repository.save(member1);
        Member result = repository.findByName("hspark1").get();
        assertThat(member1).isEqualTo(result);
        assertThat(member2).isNotEqualTo(result);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("hspark1");

        Member member2 = new Member();
        member2.setName("hspark2");

        repository.save(member1);
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}

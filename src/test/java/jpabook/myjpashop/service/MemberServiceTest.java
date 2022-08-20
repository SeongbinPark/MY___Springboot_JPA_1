package jpabook.myjpashop.service;

import jpabook.myjpashop.domain.Member;
import jpabook.myjpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.DiscriminatorValue;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("회원 가입")
    public void joinTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("park");//생성메서드로 바꾸자.

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.find(savedId));
    }

    @Test(expected = IllegalStateException.class)
    @DisplayName("중복 회원 체크")
    public void duplicatedMemberCheck() throws Exception {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("kim");
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);
        //then

        fail("중복 회원이 검증되지 않았습니다.");

    }

}
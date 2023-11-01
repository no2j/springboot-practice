package com.spring.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import com.spring.boot.domain.Member;
import com.spring.boot.repository.MemberRepository;

// 통합 테스트 방식
/*
 * Test 레벨에서 @Transactional 어노테이션을 사용하면
 * DB작업 후 각 테스트가 끝나면 각각 rollback을 자동으로 해준다.
 * 즉 다음 테스트를 반복해서 실행할 수 있다.
 */
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
	
	//Test용도이기 때문에 필드 주입방식으로 사용해도 상관없다.
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	//Test는 반복될 수 있어야한다.
	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("spring");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	// Test는 정상 작동 되는지도 중요하지만 예외처리도 잘 되는지도 중요하다.
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
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		// then
	}
	
}

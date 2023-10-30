package com.spring.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spring.boot.domain.Member;
import com.spring.boot.repository.MemoryMemberRepository;

class MemberServiceTest {
	/*
	 * 테스트 케이스 생성 방법
	 * Navigator에 해당 클래스 파일을 우클릭 후 New -> JUnit Test Case를 클릭 후
	 * Next를 누른다음에 Test할 메서드를 선택할 수 있다.
	 */
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
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
		
		// 예외처리 방법 try-catch
	/*
		try {
			memberService.join(member2);
			fail();
		} catch(IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123");
		}
	*/
		
		// 예외처리 방법2 assertThrows();
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		// then
	}
	
	@Test
	void testFindMembers() {
	}

	@Test
	void testFindOne() {
	}

}

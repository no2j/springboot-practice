package com.spring.boot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.domain.Member;
import com.spring.boot.repository.MemberRepository;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	
	/*
	 * 스프링 컨테이너에 올라간 것들만 @Autowired가 동작한다.
	 */
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원 가입 메서드
	public Long join(Member member) {
		
		validateDuplicateMember(member); // 중복 회원 판별 메서드
		
		memberRepository.save(member);
		return member.getId();
		
	}

// 1번 방식
//	Optional<Member> result = memberRepository.findByName(member.getName());
//	result.ifPresent(m ->{
//		throw new IllegalStateException("이미 존재하는 회원입니다.");
//	});
	
// 2번 방식
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
					.ifPresent(m -> {
						throw new IllegalStateException("이미 존재하는 회원입니다.");
					});
	}

	/* 1번방식과 2번 방식의 차이점
	 * 1번 방식은 result를 작성하여 해당 result에 .ifPresent를 사용한 방식이고
	 * 2번 방식은 메소드 체인을 사용하여 작성한 방식이다.
	 * 
	 * isPresent() 메소드
	 * - Boolean 타입
	 * - Optional 객체가 값을 가지고 있다면 true, 없다면 false 리턴
	 * 
	 */
	
	// 전체 회원 조회 메서드
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	// 특정 회원 조회 메서드
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}

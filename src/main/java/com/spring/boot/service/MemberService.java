package com.spring.boot.service;

import java.util.List;
import java.util.Optional;

import com.spring.boot.domain.Member;
import com.spring.boot.repository.MemberRepository;

public class MemberService {

	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원 가입 메서드
	public Long join(Member member) {
		
		validateDuplicateMember(member); // 중복 회원 판별
		
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

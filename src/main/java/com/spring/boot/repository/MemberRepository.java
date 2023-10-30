package com.spring.boot.repository;

import java.util.List;
import java.util.Optional;

import com.spring.boot.domain.Member;

public interface MemberRepository {

	// 자바8 에서 추가된 것으로  null을 반환할때 optional로 감싸서 반환해주는 역할을 함.
	Member save(Member member);
	
	Optional<Member> findById(Long id);
	
	Optional<Member> findByName(String name);
	
	List<Member> findAll();
	
}

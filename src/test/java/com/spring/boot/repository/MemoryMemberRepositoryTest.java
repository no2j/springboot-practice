package com.spring.boot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.spring.boot.domain.Member;

class MemoryMemberRepositoryTest {

	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// Test는 Test메서드의 순서를 보장하지 않음 그러므로 하나의 테스트를 수행한 후에 AfterEach로 클리어를 해주어야함.
	// Test메서드가 끝날떄마다 AfterEach에 해당하는 동작을 하는 콜백메서드
	
	/*
	 * Test는 서로 순서와 관계없이 즉 서로 의존관계없이 설계되어야한다.
	 * 하나의 테스트가 끝날 때마다 저장소 및 데이터를 지워줘야한다.
	 * 그래서 AfterEach를 사용하여 하나의 메서드가 실행한 후에 비워주는 동작을 수행하도록 한다.
	 */
	
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
		
		Assertions.assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		//.get()사용하면 Optional을 한번 까서 꺼낼 수 있음.
		assertThat(result).isEqualTo(member1);
	}
	

	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
	
}

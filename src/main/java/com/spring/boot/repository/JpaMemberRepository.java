package com.spring.boot.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import com.spring.boot.domain.Member;

// JPA 방식
public class JpaMemberRepository implements MemberRepository {

	/*
	 * 	JPA는 Spring JDBCTemplate 방식과 달리
	 * 	SQL쿼리도 자동으로 처리해주기 때문에 개발 생산성을 높여주는 이점이 있다.
	 * 
	 *  JPA 사용 시 주의 사항
	 *  항상 트랜잭션이 있어야한다.
	 */
	
	// JPA는 EntityManager로 모든 동작을 처리한다.	
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member; 
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		// JPQL
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class) .getResultList();
	}

}

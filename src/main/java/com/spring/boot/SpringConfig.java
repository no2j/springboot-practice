package com.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.boot.aop.TimeTraceAop;
import com.spring.boot.repository.MemberRepository;
import com.spring.boot.service.MemberService;

/*
 *	스프링 빈(bean)등록 방법 2 - 자바 코드로 직접 스프링 빈을 등록하는 방식
 */

@Configuration
public class SpringConfig {

	/* JDBC 방식
	 * private DataSource dataSource;
	 * 
	 * @Autowired public SpringConfig(DataSource dataSource) { this.dataSource =
	 * dataSource; }
	 */

	/* JPA 방식
	private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	*/

	// Spring Data JPA 방식
	private final MemberRepository MemberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		MemberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(MemberRepository);
	}
	
//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
		/*
		 *	다형성으로  MemoryMemberRepository에서
		 *	JdbcMemberRepository으로 다른 코드 수정없이 교체할 수 있었음.
		 */
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
		
//	}
	
	// AOP
	@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}
}

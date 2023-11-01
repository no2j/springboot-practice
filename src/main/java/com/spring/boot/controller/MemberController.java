package com.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.boot.domain.Member;
import com.spring.boot.service.MemberService;

/*
 *	스프링 빈(bean)등록하는 방법 1 - 컴포넌트 스캔 & 의존관계 설정 방식
 *	의존성 주입 (DI)으로 스프링 컨테이너에서 싱글톤 객체로 관리하게 된다.
 *
 *	< DI의 의존성 주입 3가지 방법 >
 *	1. 생성자 주입 - 추천
 *		@Autowired
		public MemberController(MemberService memberService) {
			super();
			this.memberService = memberService;
		}
		의존관계가 실행중에 동적으로 변하는 경우가 없기 때문에 생성자 주입을 권장한다.
		
 *	2. 필드 주입
 *		@Autowired
 *		private final MemberService memberService;
 *		필드 주입의 경우 편하고 코드가 간결해지는 장점이 있지만
 *		외부에서 수정이 불가능하기 때문에 테스트 코드 작성시 객체 수정이 불가하다는 점이 있다.
 *
 *	3. Setter 주입
	 	@Autowired
	  	public void setMemberService(MemberService memberService) {
			this.memberService = memberService;
		}
		
		Setter 주입의 경우 public 접근제한자이기 때문에 노출이 된다는 점,
		잘못 바뀔 수 있다는 문제점이 생길 수 있다. 변경에 열려있다.
		
 */

@Controller
public class MemberController {

	private final MemberService memberService;


	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		//redirect:/ => 홈 화면으로 이동시킴
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members",members);
		
		return "members/memberList";
		
	}
	
}

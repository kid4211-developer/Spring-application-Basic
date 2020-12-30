package hello.hellospring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
	 MemberService memberService;
	 MemoryMemberRepository memberRepository;
	 
	 @BeforeEach
	 public void beforeEach() {
		 memberRepository = new MemoryMemberRepository();
		 /* DI : MemberService 입장에선 MemberRepository에 대해 Dependency Injection 됐다고 볼수있음 */
		 memberService = new MemberService(memberRepository);
	 }
	 
	 @AfterEach
	 public void afterEach() {
		 memberRepository.clearStore();
	 }
	 
	 @Test
	 public void 회원가입() throws Exception {
		 
		 //Given
		 Member member = new Member();
		 member.setName("hello");
		 
		 //When
		 Long saveId = memberService.join(member);
		 
		 //Then
		 Member findMember = memberRepository.findById(saveId).get();
		 assertEquals(member.getName(), findMember.getName());
	 }
	 
	 @Test
	 public void 중복_회원_예외() throws Exception {
		 
		 //Given
		 Member member1 = new Member();
		 member1.setName("spring");
		 
		 Member member2 = new Member();
		 member2.setName("spring");
		 
		 //When
		 memberService.join(member1);
		 IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));//예외가 발생해야 한다.
		 assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		 System.out.println(e.getMessage());
	 }
}

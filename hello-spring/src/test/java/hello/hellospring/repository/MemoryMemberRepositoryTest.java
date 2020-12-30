package hello.hellospring.repository;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach //Test중 각 Method가 끝날때마다 호출됨
	public void afterEach() {
		/* Test는 서로 의존성이 있어서는 안되기 때문에
		 * 공용으로 사용하는 객체에 대한 저장소를 초기화 해주어야함 */
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get(); // Optional로 반환된 객체는 get()을 통해 가지고 있는 값을 형변환하듯 꺼내줘야 함
		
		/* 매전 console에 log를 찍어 확인할 수는 없기 때문에 Assertions 객체를 사용함
		 * 만약 조건에 부합하지 않으면 JUnit Test에서 에러가 발생함
		 * jupiter의 assertEquals 방식 / assertj의 assertThat 방식 */
		//System.out.println("result = " + (result == member));
		//org.junit.jupiter.api.Assertions.assertEquals(member, null);
		Assertions.assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
	 
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
	 
		//when
		Member result = repository.findByName("spring1").get();
	 
		//then
		Assertions.assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		//when
		List<Member> result = repository.findAll();
		
		//then
		Assertions.assertThat(result.size()).isEqualTo(2);
	}
	
}

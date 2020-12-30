package hello.hellospring.repository;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach //Test�� �� Method�� ���������� ȣ���
	public void afterEach() {
		/* Test�� ���� �������� �־�� �ȵǱ� ������
		 * �������� ����ϴ� ��ü�� ���� ����Ҹ� �ʱ�ȭ ���־���� */
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get(); // Optional�� ��ȯ�� ��ü�� get()�� ���� ������ �ִ� ���� ����ȯ�ϵ� ������� ��
		
		/* ���� console�� log�� ��� Ȯ���� ���� ���� ������ Assertions ��ü�� �����
		 * ���� ���ǿ� �������� ������ JUnit Test���� ������ �߻���
		 * jupiter�� assertEquals ��� / assertj�� assertThat ��� */
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

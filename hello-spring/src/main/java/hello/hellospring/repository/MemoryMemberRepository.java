package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store=new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		/* Id�� ȸ���� �����ɶ� �ý����� �����ϴ� �Ϸù�ȣ�� ���� 
		 * �׷��� �ڵ��� sequence�� ������ �� �ְ� �� */
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		/* Optional : ���� Null�� ��� ��ȯ�ϱ� ���� */
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		/* loop�� ������ �ϳ��� ��ġ���ǿ� �����ϸ� Member��ü�� ��ȯ�ϰ�
		 * ������ Optional�� ���� null�� ��ȯ�� */
		return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
	}

	@Override
	public List<Member> findAll() {
		/* store�� ������ �ִ� Member�� (= value��)�� List�� ��Ƽ� ��ȯ���� */
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}

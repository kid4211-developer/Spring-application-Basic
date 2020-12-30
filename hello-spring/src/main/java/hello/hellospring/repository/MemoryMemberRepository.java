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
		/* Id는 회원이 생성될때 시스템이 생성하는 일련번호와 같음 
		 * 그래서 자도은 sequence로 관리될 수 있게 함 */
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		/* Optional : 값이 Null일 경우 반환하기 위함 */
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		/* loop를 돌려서 하나라도 일치조건에 부합하면 Member객체를 반환하고
		 * 없으면 Optional을 통해 null을 반환함 */
		return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
	}

	@Override
	public List<Member> findAll() {
		/* store가 가지고 있는 Member들 (= value들)을 List에 담아서 반환해줌 */
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}

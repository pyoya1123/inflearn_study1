package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {

    // 밑에 static 빼도 되긴 함.
    private static Map<Long, Member> store = new HashMap<Long, Member>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    // 이렇게 만들어주면 무조건 이 함수를 통해서 조회를 할 수가 있음.
    public static MemberRepository getInstance() {
        return instance;
    }

    /*
    싱글톤으로 만들 땐, 생성자로 만들어야함. 그래서 아무나 생성하지 못하게 해줘야함.
     */
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    // 이렇게 하면 스토어에 있는 모든 값들을 꺼내서 새로운 ArrayList에 담아서 넘겨줌.
    // 왜 이렇게 하냐면, new ArrayList에 값을 넣거나 밖에서 조작해도 스토어에
    // 있는 value list를 건들지 않고 싶기 때문임.
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

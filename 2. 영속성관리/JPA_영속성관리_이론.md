#### JPA에서 가장 중요한 2가지
1. 객체와 관계형 데이터베이스 매핑하기
2. 영속성 컨텍스트

#### 영속성 컨텍스트란?
: "엔티티를 영구 저장하는 환경"이라는 뜻. <br>
논리적인 개녕으로 눈에 보이지 않는다. <br>
엔티티 매니저를 통해 영속성 컨텍스트에 접근.

#### 엔티티의 생명주기
1) 비영속(new/transient) : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 객체를 생성한 상태(비영속)
- `Member member = new Member();`
- `member.setId("member1");`
- `member.setUsername("회원1");`
- `EntityManager em = emf.createEntityManager();`
- `em.getTransaction().begin();`
2) 영속(managed) : 영속성 컨텍스트에 관리되는 상태
- 객체를 저장한 상태(영속)
- `em.persist(member);` // 이떄 DB에 저장되지 않는다. __commit 하는 시점에서 DB로 날라간다!__
5) 준영속(detached) : 영속성 컨텍스트에 저장되었다가 분리된 상태
- 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
- `em.detach(member);`
7) 삭제(removed) : 삭제된 상태
- 객체를 삭제한 상태
- `em.remove(member);`

#### 1차 캐시
(1차 캐시를 영속성 컨텍스트락고 이해해도 됨)

#### 영속 엔티티의 동일성 보장
`Member a = em.find(Member.class, "member1");`
`Member b = em.find(Member.class, "member1");`
`System.out.println(a == b);` // 동일성 true
- 1차 캐시로 반복 가능한 읽기(REPEATABLE READ) 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공. 

#### 엔티티 등록시 트랜잭션 지원하는 쓰기 지연
: 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다!
persist 하면 1차 캐시로 넣는다. commit을 하는 순간 쓰기 지연 SQL 저장소의 데이터가 flush 되어 DB로 날라간다. 


#### 플러시
: 영속성 컨텍스트의 변경내요응ㄹ 데이터베이스에 반영
1) 플러시 발생
- 변경 감지
- 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
- 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송(등록, 수정, 삭제 ㅜ커리)
2) 영속성 컨텍스트를 플러시하는 방법
- `em.flush()` // 직접 호출
- 트랜잭션 커밋 // 플러시 자동 호출
- JPQL 쿼리 실행 // 플러시 자동 호출
3) 플러시 모드 옵션
- `FlushModeType.AUTO` : 커밋이나 쿼리를 실행할 떄 플러시(default)
- `FlushModeType.COMMIT`: 커밋할 때만 플러시
4) 즉 플러시는!
- 영속성 컨텍스트를 비우지 않는다
- 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
- 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화하면 된다.

#### 준영속상태
: 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)된 것.
영속성 컨텍스트가 제공하는 기능 사용 못한다.
1) 준영속 상태로 만드는 방법
- `em.detach(entity)` : 특정 엔티티만 준영속 상태로 전환
- `em.clear()` : 영속성 컨텍스트를 완전히 초기화
- `em.close()` : 영속성 컨텍스트 종료

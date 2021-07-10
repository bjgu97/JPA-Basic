### SQL 중심적인 개발의 문제점:
- 무한 반복과 지루한 코드
- 패러다임의 불일치

### 그렇다면 객체를 자바 컬렉션에 저장하듯이 DB에 저장할 수는 없을까?
-> 그래서 등장한 개념이 JPA

### JPA란?
Java Persistence API (자바 진영의 ORM 기술 표준)
객체는 객체대로, 관계형 DB는 관계형 DB대로 설계하고 ORM 프레임워크가 중간에서 매핑.

### JPA 사용 이유?
1) 생산성
2) 유지보수
3) JPA와 패러다임 불일치 해결

### JPA의 성능 최적화 기능
1) 1차 캐시와 동일성 보장
- 같은 트랜잭션 안에서는 같은 엔티티 반환
- DB Isolation level이 read commit이여도 애플리케이션에서 repeatable read 보장 (건너뛰기)
2) 트랜잭션을 지원하는 쓰기 지연
- 트랜잭션을 커밋할 때까지 INSERT SQL 모음
- JDBC BATCH SQL 기능을 사용해서 한번에 SQL로 전송
3) 지연 로딩과 즉시 로딩 둘다 가능
- 지연 로딩: 객체가 실제 사용될 때 로딩
- 즉시 로딩: JOIN SQL로 한번에 연관된 객체까지 미리 조회

### 데이터베이스 방언?
: SQL 표준을 지키지 않는 특정 데이터베이스만의 고유 기능. 즉 JPA는 데이터베이스에 종속되지 않는다.

ex)
`@Entity` : JPA를 사용하는 아이구나!

`@Id` : id라는 것 의미

`@table(name="USER")` : USER 테이블 이용

`@Column(name="username")` : 테이블 열 이름 

`EntityManagerFactory emf = Persistence.createEntityManagerFactory()` : 웹 서버 올라오는 순간에 하나만 생성

`EntityManager em = emf.createEntityManager()` : 고객의 요청이 올때마다 생성; 쓰레드간 공유 X

`EntityTransaction tx = em.getTransaction()` : JPA에서 트랜잭션 반드시 필요

`tx.begin()` : JPA의 모든 데이터 변경은 트랜잭션 안에서 실행!

### JPQL이란?
JPA를 사용하면 엔티티 객체를 중심으로 개발. 여기서 문제는 검색 쿼리!
모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능하다.
따라서 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요. 
=> _그래서 나온게 JPQL_
즉, JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어를 제공한다.

ex) `List<Member> result = em.createQuery(qlString("select n from Member", Member.class).getResultList();`

ex) `List<Member> result = em.createQuery(qlString("select n from Member", Member.class).getResultList().setMaxResult(10).setFirstResult(1);`




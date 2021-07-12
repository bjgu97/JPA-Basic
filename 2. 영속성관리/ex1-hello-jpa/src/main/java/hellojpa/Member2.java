package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Member") // 매핑할 테이블 정보를 알려준다.
public class Member2 {

    @Id // pk가 뭔지 알려주기기
    private long id;
    private String name;

    // 기본생성자
    public Member2() {
    }

    public Member2(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package of_f.of_f_spring.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "platform_seq")
    private Long platformSeq;
    
    @Column(name = "role_name")
    private String roleName;
}

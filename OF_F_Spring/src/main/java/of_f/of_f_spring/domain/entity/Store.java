package of_f.of_f_spring.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "User_seq")
    private Long OFFUserSeq;

    @Column
    private String name;

    @Column(name = "business_number")
    private String businessNumber;

    @Column
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "deposit_date")
    private String depositDate;

    @Column(name = "business_phone_number")
    private String businessPhoneNumber;

    @Column(name = "open_date")
    private String openDate;

    @Column
    private int status;

    @OneToOne
    @JoinColumn(name = "User_seq", insertable = false, updatable = false)
    private User user;

}

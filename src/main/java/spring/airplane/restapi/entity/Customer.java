package spring.airplane.restapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 250)
    private String fullname;

    @Column(length = 250)
    private String email;

    @Column(length = 250)
    private String password;

    @Column(length = 250)
    private String hobby;

    @Column(length = 250)
    private String photos;

    @OneToOne(mappedBy = "customer")
    private Credit customerCredits;

    @OneToMany(mappedBy = "customer")
    private List<Booking> customerBookings;

}
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
@Table(name = "destinations")
public class Destination {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 250)
    private String name;

    @Column(name = "description", columnDefinition = "longtext")
    private String description;

    @Column
    private Double price;

    @Column(length = 250)
    private String photos;

    @Column
    private Boolean insurance;

    @Column
    private Boolean refundable;

    @Column
    private Double vat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "destination")
    private Set<Booking> destinationBookings;

}



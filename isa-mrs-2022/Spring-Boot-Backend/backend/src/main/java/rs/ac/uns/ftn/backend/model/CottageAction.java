package rs.ac.uns.ftn.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cottage_action")
public class CottageAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "max_people_num")
    private String maxPeopleNum;

    @Column(name = "more_information")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "action_start")
    private LocalDate startAction;

    @Column(name = "action_end")
    private LocalDate endAction;

}

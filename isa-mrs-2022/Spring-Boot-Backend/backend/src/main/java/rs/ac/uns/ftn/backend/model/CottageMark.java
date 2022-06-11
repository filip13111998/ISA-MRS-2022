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
@Table(name = "cottage_mark")
public class CottageMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark")
    private Double mark;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "entity")
    private Long entity;
}

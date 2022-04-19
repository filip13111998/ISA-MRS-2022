package rs.ac.uns.ftn.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstructorDTO {

    private Long id;

    private String name;

    private String email;

    private String addressInstructor;

    private String description;

    private double averageMark;

}

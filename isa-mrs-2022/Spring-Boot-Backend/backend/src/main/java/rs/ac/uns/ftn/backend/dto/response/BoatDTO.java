package rs.ac.uns.ftn.backend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import rs.ac.uns.ftn.backend.model.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoatDTO {

    private Long id;

    private String name;

    private BoatImage bi;

    private String address;

    private String type;

    private double averageMark;

}

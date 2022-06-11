package rs.ac.uns.ftn.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.backend.model.MyUser;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRequestDTO {
   private String description;

   private String username;

}




package modernjavainaction.chapter6practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetOwnerDetails {
    private String firstName;
    private String lastName;
    private int age;
    private MarritalStatus marritalStatus;
    private PetDetails petDetails;
    private boolean isEmployed;
}

package modernjavainaction.chapter6practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDetails {
    private String name;
    private int age;
    private Pet type;
    private String breed;

}

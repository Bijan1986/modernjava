package modernjavainaction.chapter4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    private  String name;
    private  boolean vegetarian;
    private  int calories;
    private  Type type;
    public enum Type { MEAT, FISH, OTHER }
}
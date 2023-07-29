package modernjavainaction.chapter6practice;

import modernjavainaction.chapter6practice.dto.PetOwnerDetails;
import modernjavainaction.chapter6practice.util.PetUtils;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class MainDemo{
    public static void main(String[] args) {
        PetUtils petUtils = new PetUtils();
        var petOwnersList = petUtils.getDefaultData();
        //System.out.println(petOwnersList);
        getPetsOwnedByOwner("Shruti","Panicker",petOwnersList);
    }

    private static void getPetsOwnedByOwner(String firstName, String lastName, List<PetOwnerDetails> petOwnersList) {
        Map<String, List<PetOwnerDetails>> petsOwnedByUser = petOwnersList.stream()
                .collect(groupingBy(PetOwnerDetails::getFirstName,
                        filtering(petOwnerDetails -> petOwnerDetails.getFirstName().equalsIgnoreCase(firstName),toList())));
        System.out.println(petsOwnedByUser);
    }
}

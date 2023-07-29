package modernjavainaction.chapter6practice.util;

import modernjavainaction.chapter6practice.dto.MarritalStatus;
import modernjavainaction.chapter6practice.dto.Pet;
import modernjavainaction.chapter6practice.dto.PetDetails;
import modernjavainaction.chapter6practice.dto.PetOwnerDetails;

import java.util.Arrays;
import java.util.List;

public class PetUtils {
    public List<PetOwnerDetails> getDefaultData(){
        List<PetOwnerDetails> allPetOwners = Arrays.asList(
              PetOwnerDetails.builder().firstName("Shruti")
                      .lastName("Panicker")
                      .age(33)
                      .marritalStatus(MarritalStatus.MARRIED)
                      .petDetails(
                              PetDetails.builder()
                                      .name("Lucky")
                                      .age(4)
                                      .type(Pet.DOG)
                                      .breed("Jack Russell")
                                      .build()

                      )
                      .isEmployed(true)
                      .build(),
                PetOwnerDetails.builder().firstName("Shruti")
                        .lastName("Panicker")
                        .age(33)
                        .marritalStatus(MarritalStatus.MARRIED)
                        .petDetails(
                                PetDetails.builder()
                                        .name("Jenny")
                                        .age(3)
                                        .type(Pet.DOG)
                                        .breed("Jack Russell")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName1")
                        .lastName("lastName1")
                        .age(28)
                        .marritalStatus(MarritalStatus.SINGLE)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName91")
                                        .age(7)
                                        .type(Pet.CAT)
                                        .breed("Persian")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName2")
                        .lastName("lastName2")
                        .age(31)
                        .marritalStatus(MarritalStatus.MARRIED)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName2")
                                        .age(11)
                                        .type(Pet.CAT)
                                        .breed("Ragdoll")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName3")
                        .lastName("lastName3")
                        .age(30)
                        .marritalStatus(MarritalStatus.MARRIED)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName3")
                                        .age(1)
                                        .type(Pet.DOG)
                                        .breed("Golden Retriever")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName4")
                        .lastName("lastName4")
                        .age(29)
                        .marritalStatus(MarritalStatus.DIVORCED)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName4")
                                        .age(6)
                                        .type(Pet.DOG)
                                        .breed("Labrador")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName5")
                        .lastName("lastName5")
                        .age(38)
                        .marritalStatus(MarritalStatus.SINGLE)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName5")
                                        .age(8)
                                        .type(Pet.DOG)
                                        .breed("Jack Russell")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName6")
                        .lastName("lastName6")
                        .age(45)
                        .marritalStatus(MarritalStatus.MARRIED)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName6")
                                        .age(9)
                                        .type(Pet.CAT)
                                        .breed("Ragdoll")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName7")
                        .lastName("lastName7")
                        .age(44)
                        .marritalStatus(MarritalStatus.DIVORCED)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName7")
                                        .age(2)
                                        .type(Pet.CAT)
                                        .breed("Persian")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName8")
                        .lastName("lastName8")
                        .age(18)
                        .marritalStatus(MarritalStatus.SINGLE)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName8")
                                        .age(1)
                                        .type(Pet.CAT)
                                        .breed("Persian")
                                        .build()

                        )
                        .isEmployed(true)
                        .build(),
                PetOwnerDetails.builder().firstName("firstName9")
                        .lastName("lastName9")
                        .age(62)
                        .marritalStatus(MarritalStatus.MARRIED)
                        .petDetails(
                                PetDetails.builder()
                                        .name("randomName9")
                                        .age(5)
                                        .type(Pet.DOG)
                                        .breed("Golden Retriever")
                                        .build()

                        )
                        .isEmployed(true)
                        .build()



        );
        return allPetOwners;
    }
}

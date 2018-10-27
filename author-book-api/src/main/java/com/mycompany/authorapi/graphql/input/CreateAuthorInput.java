package com.mycompany.authorapi.graphql.input;

import lombok.Data;

@Data
public class CreateAuthorInput {

    private String firstName;
    private String lastName;
//  private LocalDate birthday; // Waiting for LocalDate Scalar: https://github.com/graphql-java/graphql-java/issues/991

}

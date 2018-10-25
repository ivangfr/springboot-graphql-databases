package com.mycompany.authorapi.graphql.input;

import lombok.Data;

@Data
public class CreateAuthorInput {

    private String firstName;
    private String lastName;

}

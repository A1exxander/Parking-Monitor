package com.citycite_api.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

@Data
public class UserRequest {

    @NotNull
    @Size(min = 2, max = 32)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 32)
    private String lastName;

    @NotNull @Past
    private Date birthDate;

    @Null
    private MultipartFile profilePicture;

}

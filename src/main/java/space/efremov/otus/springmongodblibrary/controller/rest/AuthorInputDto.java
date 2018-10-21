package space.efremov.otus.springmongodblibrary.controller.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class AuthorInputDto {

    @NotBlank
    private String name;

}

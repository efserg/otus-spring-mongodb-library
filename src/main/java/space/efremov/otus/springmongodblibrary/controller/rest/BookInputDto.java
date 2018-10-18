package space.efremov.otus.springmongodblibrary.controller.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookInputDto {

    @NotBlank
    private String title;

    @NotEmpty
    private List<String> authorIds;

    @NotBlank
    private String publisher;

    private List<String> tags;

    @Min(1)
    @Max(2100)
    private Integer year;

    @NotBlank
    private String isbn;

}

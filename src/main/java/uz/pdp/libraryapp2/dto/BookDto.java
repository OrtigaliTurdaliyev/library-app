package uz.pdp.libraryapp2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class BookDto {
    private Integer id;
    private String title;
    private String isbn;
    private Integer count;
    private String description;

    private List<AuthorDto> authorDtoList;
    private List<Integer> authorsIds;

    private List<CategoryDto> categoryDtoList;
    private List<Integer> categoriesIds;
    private Integer languageId;
    private String languageName;

}

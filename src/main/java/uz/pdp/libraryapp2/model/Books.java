package uz.pdp.libraryapp2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Books {
    private Integer id;
    private String title;
    private String totalCount;
    private String description;

    private List<Author> authors;
    private String isbn;

    private List<Categories> categories;
    private Language language;
}

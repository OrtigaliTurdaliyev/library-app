package uz.pdp.libraryapp2.records;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Records {
    private int id;
    private boolean is_returned;
    private int dataTime;
    private int users_id;
}

package pl.inzynier.netintegrator.db.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseConfiguration {

    private String address;
    private String user;
    private String password;

}

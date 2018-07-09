package io.zensoft.share.model.diesel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DieselAccount {


    private String username;
    private String password;

    public DieselAccount(String userName, String password){
        this.username = userName;
        this.username = password;
    }

}

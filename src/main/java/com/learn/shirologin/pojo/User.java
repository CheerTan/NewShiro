package com.learn.shirologin.pojo;



import lombok.Data;
import lombok.ToString;

import java.sql.Date;


@Data
@ToString
public class User {
    private Integer id;
    private String username;
    private String userpassword;
    private String useremail;
    private Integer status;

    private Date createdate;
    private String updatetime;

    public User() {
    }
}

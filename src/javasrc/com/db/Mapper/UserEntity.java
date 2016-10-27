package com.db.Mapper;

import com.Validator.OnlyOne;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by Tinyhome on 16/7/14.
 */
public class UserEntity {

    private int user_id;
    private String user_login;
    private String user_pwd;

    public Date getUser_regdate() {
        return user_regdate;
    }

    public void setUser_regdate(Date user_regdate) {
        this.user_regdate = user_regdate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /*@NotBlank(message = "用户名不能为空")
    @Length(min = 6,max = 20,message = "用户名长度必须在6-20之间")*/
    @Pattern(regexp = "^[A-Z]\\w{5,19}$",message = "用户名首字母必须大写,长度6到20之间")
    @OnlyOne(message = "用户名必须唯一")
    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    private Date user_regdate;


}

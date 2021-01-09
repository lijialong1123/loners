package com.example.demo.serializable;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * @author : coder
 * @create 2021/1/6 14:36
 */
@AllArgsConstructor
@Data
public class User {

    private String id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        //序列化
        //String userId = UUID.randomUUID().toString().replaceAll("-", "");
        String userId = "2085564466cf4d7e92dd08cf2a4f3ddf";
        String james = JSON.toJSONString(new User(userId, "james", 11));
        System.out.println(james);
        //反序列化
        String userString = "{\"age\":11,\"id\":\"2085564466cf4d7e92dd08cf2a4f3ddf\",\"name\":\"james\"}";
        System.out.println(JSON.parseObject(userString, User.class));
    }

}

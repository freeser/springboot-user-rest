package com.free.controller;


import com.free.beans.Result;
import com.free.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getUser/{id}")
    public Object getUser(@PathVariable Integer id) {

        //String url = "http://localhost:8080/user/"+id;
        String url = "http://localhost:8080/user/{id}";

        //String forObject = restTemplate.getForObject(url, String.class,id);
        Result forObject = null;
        try {
            forObject = restTemplate.getForObject(url, Result.class, id);

            ResponseEntity<Result> forEntity = restTemplate.getForEntity(url, Result.class, id);
            System.out.println(forEntity);
            Result body = forEntity.getBody();
            System.out.println(body);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        //User user=userService.getUserById(id);
        return forObject;
    }

    // 新增 /user/add
    @PostMapping("/add")
    public Result addUser(User user) {
        String url = "http://localhost:8080/user/add";
        Result result = restTemplate.postForObject(url, user, Result.class);

        ResponseEntity<Result> resultResponseEntity = restTemplate.postForEntity(url, user, Result.class);

        System.out.println(resultResponseEntity);
        System.out.println(resultResponseEntity.getBody());
//        Result body = resultResponseEntity.getBody();

        return result;
    }

    // 修改 /user1
    @PostMapping("/update")
    public Result editUser(User user) {

        //String url = "http://localhost:8080/user/update";
        String url = "http://localhost:8080/user/updateMap";

        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        //设置访问参数
        HashMap<String, User> params = new HashMap<>();
        params.put("user", user);

        HttpEntity<Map> entity = new HttpEntity<Map>(params, headers);
        //HttpEntity<User> entity = new HttpEntity<User>(user, headers);


        ResponseEntity<Result> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, Result.class);
        Result body = exchange.getBody();


        return body;
    }

    // 删除 /user1
    @DeleteMapping("/{id}")
    public Result editUser(@PathVariable Integer id) {
        //String url = "http://localhost:8080/user/{id}";
        //restTemplate.delete(url,id);

        HttpEntity<Integer> entity = new HttpEntity<Integer>(2);

        String url1 = "http://localhost:8080/user/"+id;

        // 基于restTemplate 调用删除
        ResponseEntity<Result> exchange = restTemplate.exchange(url1, HttpMethod.DELETE, entity, Result.class);
        System.out.println(exchange);
        return Result.buildSuccess("删除成功");
    }
}
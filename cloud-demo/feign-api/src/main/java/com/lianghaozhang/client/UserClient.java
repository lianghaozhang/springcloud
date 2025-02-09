package com.lianghaozhang.client;

import com.lianghaozhang.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userService")
public interface UserClient {
    @GetMapping("user/{id}")
    User getUserById(@PathVariable("id") Long id);
}

package cn.itcast.user.web;

import cn.itcast.user.config.StudentProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentProperties studentProperties;
//    @Value("${name}")
//    private String name;
//    @Value("${age}")
//    private String age;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,
                          @RequestHeader(value = "Xxx", required = false) String xxx,
                          @RequestParam(value = "isEnableDefaultFilter", required = false) String isEnableDefaultFilter) {
        System.out.println("isEnableDefaultFilter: " + isEnableDefaultFilter);
        return userService.queryById(id);
    }

//    @GetMapping("/testGetConfigValue")
//    public String testGetConfigValue() {
//        return name + "-" + age;
//    }

    @GetMapping("/testGetConfigValue")
    public StudentProperties testGetConfigValue() {
        return studentProperties;
    }
}

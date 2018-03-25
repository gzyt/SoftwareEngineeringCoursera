package com.nwnu.fundamentaloperations.controller;

import com.nwnu.fundamentaloperations.domain.UserEntity;
import com.nwnu.fundamentaloperations.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserRestController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/user")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/LoginCheck",method = RequestMethod.POST)
    public String loginCheck(@Param("username") String username, @Param("password") String password,
                             HttpServletRequest request){
        UserEntity userEntity = userRepository.findByUsername(username);
        logger.info(username+" "+password);
        if (userEntity != null&&userEntity.getPassword().equals(password)){ ;
        logger.info("sucess");
            return "2";
        }
        logger.info("fail");
        return "1";
    }
}

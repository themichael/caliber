package com.revature.caliber.salesforce;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {



    @RequestMapping(method = RequestMethod.GET, value = "/success")
    @ResponseBody
    public String success() {


        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().
                getPrincipal();


        return "User "+user.getUsername()+". Role is "+ user.getAuthorities().toString();

    }


}

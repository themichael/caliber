package com.revature.caliber.salesforce.controllers;

import com.revature.caliber.salesforce.models.SalesforceUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/vp")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    @ResponseBody
    public String success() {
        SalesforceUser user = (SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        return "User "+user.getUsername()+". Role is "+ user.getAuthorities().toString();

    }


}

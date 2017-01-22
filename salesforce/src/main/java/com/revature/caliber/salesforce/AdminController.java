package com.revature.caliber.salesforce;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("VP")
@RequestMapping("/vp")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    @ResponseBody
    public String success() {
        SalesforceUser user = (SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "User "+user.getUsername()+". Role is "+ user.getAuthorities().toString();

    }


}

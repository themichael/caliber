/*
*  A filter is too restrictive to do all of the needed authentication, redirect to authentication service
*  instead
*/
public class RedirectToAuthenticationPreFilter extends ZuulFilter {
 
    @Override
    public String filterType() {
        return "pre";
    }
 
    @Override
    public int filterOrder() {
        return 1;
    }
 
    @Override
    public boolean shouldFilter() {
        return true;
    }
 
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUrl = request.getRequestURL();

        //these endpoints should bypass authentication
        if(requestUrl.contains("/security/test"))
        {
            return new ModelAndView("forward:/security/authorize");
        }
        if(requestUrl.contains("/revoke"))
        {
            return new ModelAndView("forward:/security/revoke");
        }
        if(requestUrl.contains("/revoke"))
        {
            return new ModelAndView("forward:/security/revoke");
        }
        if(requestUrl.contains("/authenticated_token"))
        {
            return new ModelAndView("forward:/security/authenticated_token");
        }
        if(requestUrl.contains("/authenticated"))
        {
            return new ModelAndView("forward:/security/authenticated");
        }
        // add code to exclude localhost:8080/ nothing or caliber/ nothing eventually
        if(System.getEnv("CALIBER_DEV_MODE").equals("true"))
        {
            if(requestUrl.length() > 14 && requestUrl.substring(requestUrl.length() - 15).equals("localhost:8080/"))//ends with localhost:8080/
            return new ModelAndView("forward:/security/");
        }
        else
        {
            if(requestUrl.length() > 7 && requestUrl.substring(requestUrl.length() - 8).equals("caliber/"))//ends with caliber/
            return new ModelAndView("forward:/security/");
        }

        return new ModelAndView("forward:/security/authorize");//anything that is not one of those endpoints goes here
        return null;
    }
 
}
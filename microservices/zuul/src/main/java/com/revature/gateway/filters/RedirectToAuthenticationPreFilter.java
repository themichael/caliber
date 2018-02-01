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
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String contextURI = (String) context.get("requestURI");

        Cookie[] cookies = request.getCookies();
        boolean hasRole = false;
        for(cookie c : cookies)
        {
            if(c.getName().equals("role"))
            {
                hasRole = true;
            }
        }
        
        if(!hasRole)//if the user has already logged in, they will have a cookie named role
        {
            context.put("preRedirectRequestUri", contextURI);

            //these endpoints should bypass authentication
            if(contextURI.contains("/security/test"))
            {
                context.put("requestURI", "/security/authorize");
            }
            else if(contextURI.contains("/revoke"))
            {
                context.put("requestURI", "forward:/security/revoke");
            } 
            else if(contextURI.contains("/revoke"))
            {
                context.put("requestURI", "forward:/security/revoke");
            }
            else if(contextURI.contains("/authenticated_token"))
            {
                context.put("requestURI", "forward:/security/authenticated_token");
            }
            else if(contextURI.contains("/authenticated"))
            {
                context.put("requestURI", "forward:/security/authenticated");
            }
            else if(contextURI.length() > 14 && contextURI.substring(contextURI.length() - 15).equals("localhost:8080/"))//ends with localhost:8080/
            {
                context.put("requestURI", "forward:/security/");
            }
            else
            {
                if(contextURI.length() > 7 && contextURI.substring(contextURI.length() - 8).equals("caliber/"))//ends with caliber/
                context.put("requestURI", "forward:/security/");
            }
            else
                context.put("requestURI", "forward:/security/authorize");//anything that is not one of those endpoints goes here
        }
        return null;
    }
 
}
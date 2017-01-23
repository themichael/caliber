package com.revature.caliber.salesforce.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shehar on 1/19/2017.
 */
public class SalesforceUser implements UserDetails {

    private String user_id;
    private String organization_id;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private String role;



    public SalesforceUser(String username, String role, String user_id, String organization_id, String email, String first_name, String last_name) {
        this.username = username;
        this.role = role;
        this.user_id = user_id;
        this.organization_id = organization_id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public SalesforceUser(String user_id, String organization_id, String username, String email, String first_name, String last_name) {
        this.user_id = user_id;
        this.organization_id = organization_id;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return true;
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }


    @Override
    public String toString() {
        return "SalesforceUser{" +
                "user_id='" + user_id + '\'' +
                ", organization_id='" + organization_id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}

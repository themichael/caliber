package com.revature.caliber.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shehar on 1/19/2017.
 */
public class SalesforceUser implements UserDetails {
    @JsonProperty
    private String id;
    @JsonProperty
    private boolean asserted_user;
    @JsonProperty
    private String user_id;
    @JsonProperty
    private String organization_id;
    @JsonProperty
    private String username;
    @JsonProperty
    private String nick_name;
    @JsonProperty
    private String display_name;
    @JsonProperty
    private String email;
    @JsonProperty
    private boolean email_verified;
    @JsonProperty
    private String first_name;
    @JsonProperty
    private String last_name;
    @JsonProperty
    private String timezone;
    @JsonProperty
    private SalesforceUserPhotos photos;
    @JsonProperty
    private String addr_street;
    @JsonProperty
    private String addr_city;
    @JsonProperty
    private String addr_state;
    @JsonProperty
    private String addr_country;
    @JsonProperty
    private String addr_zip;
    @JsonProperty
    private String mobile_phone;
    @JsonProperty
    private boolean mobile_phone_verified;
    @JsonProperty
    private SalesforceUserStatus status;
    @JsonProperty
    private SalesforceUserUrls urls;
    @JsonProperty
    private boolean active;
    @JsonProperty
    private String user_type;
    @JsonProperty
    private String language;
    @JsonProperty
    private String locale;
    @JsonProperty
    private long utcOffset;
    @JsonProperty
    private String last_modified_date;
    @JsonProperty
    private boolean is_app_installed;
    @JsonProperty
    private String role;
    private SalesforceToken salesforceToken;

    public SalesforceUser() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAsserted_user() {
        return asserted_user;
    }

    public void setAsserted_user(boolean asserted_user) {
        this.asserted_user = asserted_user;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public SalesforceUserPhotos getPhotos() {
        return photos;
    }

    public void setPhotos(SalesforceUserPhotos photos) {
        this.photos = photos;
    }

    public String getAddr_street() {
        return addr_street;
    }

    public void setAddr_street(String addr_street) {
        this.addr_street = addr_street;
    }

    public String getAddr_city() {
        return addr_city;
    }

    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }

    public String getAddr_state() {
        return addr_state;
    }

    public void setAddr_state(String addr_state) {
        this.addr_state = addr_state;
    }

    public String getAddr_country() {
        return addr_country;
    }

    public void setAddr_country(String addr_country) {
        this.addr_country = addr_country;
    }

    public String getAddr_zip() {
        return addr_zip;
    }

    public void setAddr_zip(String addr_zip) {
        this.addr_zip = addr_zip;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public boolean isMobile_phone_verified() {
        return mobile_phone_verified;
    }

    public void setMobile_phone_verified(boolean mobile_phone_verified) {
        this.mobile_phone_verified = mobile_phone_verified;
    }

    public SalesforceUserStatus getStatus() {
        return status;
    }

    public void setStatus(SalesforceUserStatus status) {
        this.status = status;
    }

    public SalesforceUserUrls getUrls() {
        return urls;
    }

    public void setUrls(SalesforceUserUrls urls) {
        this.urls = urls;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public long getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(long utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(String last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    public boolean isIs_app_installed() {
        return is_app_installed;
    }

    public void setIs_app_installed(boolean is_app_installed) {
        this.is_app_installed = is_app_installed;
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

    public SalesforceToken getSalesforceToken() {
        return salesforceToken;
    }

    public void setSalesforceToken(SalesforceToken salesforceToken) {
        this.salesforceToken = salesforceToken;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return null;
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
                "id='" + id + '\'' +
                ", asserted_user=" + asserted_user +
                ", user_id='" + user_id + '\'' +
                ", organization_id='" + organization_id + '\'' +
                ", username='" + username + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", display_name='" + display_name + '\'' +
                ", email='" + email + '\'' +
                ", email_verified=" + email_verified +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", timezone='" + timezone + '\'' +
                ", photos=" + photos +
                ", addr_street='" + addr_street + '\'' +
                ", addr_city='" + addr_city + '\'' +
                ", addr_state='" + addr_state + '\'' +
                ", addr_country='" + addr_country + '\'' +
                ", addr_zip='" + addr_zip + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", mobile_phone_verified=" + mobile_phone_verified +
                ", status=" + status +
                ", urls=" + urls +
                ", active=" + active +
                ", user_type='" + user_type + '\'' +
                ", language='" + language + '\'' +
                ", locale='" + locale + '\'' +
                ", utcOffset=" + utcOffset +
                ", last_modified_date='" + last_modified_date + '\'' +
                ", is_app_installed=" + is_app_installed +
                ", role='" + role + '\'' +
                ", salesforceToken=" + salesforceToken +
                '}';
    }
}

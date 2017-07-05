package com.revature.caliber.security.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.revature.caliber.beans.Trainer;

/**
 * The type Salesforce user.
 */
public class SalesforceUser implements UserDetails {
	private static final long serialVersionUID = 8330084985503759701L;
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
	@JsonProperty
	private boolean is_lightning_login_user;

    @JsonProperty
    private SalesforceToken salesforceToken;

    @JsonProperty
    private Trainer caliberUser;
    
    /**
     * Instantiates a new Salesforce user.
     */
    public SalesforceUser() {
    	super();
    }

    /**
     * Instantiates a new Salesforce user.
     *
     * @param username        the username
     * @param role            the role
     * @param user_id         the user id
     * @param organization_id the organization id
     * @param email           the email
     * @param first_name      the first name
     * @param last_name       the last name
     */
    public SalesforceUser(String username, String role, String user_id, String organization_id, String email, String first_name, String last_name) {
        this.username = username;
        this.role = role;
        this.user_id = user_id;
        this.organization_id = organization_id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    /**
     * Instantiates a new Salesforce user.
     *
     * @param user_id         the user id
     * @param organization_id the organization id
     * @param username        the username
     * @param email           the email
     * @param first_name      the first name
     * @param last_name       the last name
     */
    public SalesforceUser(String user_id, String organization_id, String username, String email, String first_name, String last_name) {
        this.user_id = user_id;
        this.organization_id = organization_id;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    /**
     * Gets the Caliber user associated with the Salesforce account.
     * Loaded in by BootController
     * @return
     */
    public Trainer getCaliberUser() {
		return caliberUser;
	}
	
	//is_lightning_login_user
    public boolean isIs_lightning_login_user() {
        return is_lightning_login_user;
    }
	
    public void setIs_lightning_login_user(boolean is_lightning_login_user) {
        this.is_lightning_login_user = is_lightning_login_user;
    }

	
    /**
	 * Sets the Caliber user associated with the Salesforce account.
     * Loaded in by BootController 
	 * @param caliberUser
	 */
	public void setCaliberUser(Trainer caliberUser) {
		this.caliberUser = caliberUser;
	}

	/**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * Sets user id.
     *
     * @param user_id the user id
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets organization id.
     *
     * @return the organization id
     */
    public String getOrganization_id() {
        return organization_id;
    }

    /**
     * Sets organization id.
     *
     * @param organization_id the organization id
     */
    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Is asserted user boolean.
     *
     * @return the boolean
     */
    public boolean isAsserted_user() {
        return asserted_user;
    }

    /**
     * Sets asserted user.
     *
     * @param asserted_user the asserted user
     */
    public void setAsserted_user(boolean asserted_user) {
        this.asserted_user = asserted_user;
    }

    /**
     * Gets nick name.
     *
     * @return the nick name
     */
    public String getNick_name() {
        return nick_name;
    }

    /**
     * Sets nick name.
     *
     * @param nick_name the nick name
     */
    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    /**
     * Gets display name.
     *
     * @return the display name
     */
    public String getDisplay_name() {
        return display_name;
    }

    /**
     * Sets display name.
     *
     * @param display_name the display name
     */
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    /**
     * Is email verified boolean.
     *
     * @return the boolean
     */
    public boolean isEmail_verified() {
        return email_verified;
    }

    /**
     * Sets email verified.
     *
     * @param email_verified the email verified
     */
    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    /**
     * Gets timezone.
     *
     * @return the timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets timezone.
     *
     * @param timezone the timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * Gets photos.
     *
     * @return the photos
     */
    public SalesforceUserPhotos getPhotos() {
        return photos;
    }

    /**
     * Sets photos.
     *
     * @param photos the photos
     */
    public void setPhotos(SalesforceUserPhotos photos) {
        this.photos = photos;
    }

    /**
     * Gets addr street.
     *
     * @return the addr street
     */
    public String getAddr_street() {
        return addr_street;
    }

    /**
     * Sets addr street.
     *
     * @param addr_street the addr street
     */
    public void setAddr_street(String addr_street) {
        this.addr_street = addr_street;
    }

    /**
     * Gets addr city.
     *
     * @return the addr city
     */
    public String getAddr_city() {
        return addr_city;
    }

    /**
     * Sets addr city.
     *
     * @param addr_city the addr city
     */
    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }

    /**
     * Gets addr state.
     *
     * @return the addr state
     */
    public String getAddr_state() {
        return addr_state;
    }

    /**
     * Sets addr state.
     *
     * @param addr_state the addr state
     */
    public void setAddr_state(String addr_state) {
        this.addr_state = addr_state;
    }

    /**
     * Gets addr country.
     *
     * @return the addr country
     */
    public String getAddr_country() {
        return addr_country;
    }

    /**
     * Sets addr country.
     *
     * @param addr_country the addr country
     */
    public void setAddr_country(String addr_country) {
        this.addr_country = addr_country;
    }

    /**
     * Gets addr zip.
     *
     * @return the addr zip
     */
    public String getAddr_zip() {
        return addr_zip;
    }

    /**
     * Sets addr zip.
     *
     * @param addr_zip the addr zip
     */
    public void setAddr_zip(String addr_zip) {
        this.addr_zip = addr_zip;
    }

    /**
     * Gets mobile phone.
     *
     * @return the mobile phone
     */
    public String getMobile_phone() {
        return mobile_phone;
    }

    /**
     * Sets mobile phone.
     *
     * @param mobile_phone the mobile phone
     */
    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    /**
     * Is mobile phone verified boolean.
     *
     * @return the boolean
     */
    public boolean isMobile_phone_verified() {
        return mobile_phone_verified;
    }

    /**
     * Sets mobile phone verified.
     *
     * @param mobile_phone_verified the mobile phone verified
     */
    public void setMobile_phone_verified(boolean mobile_phone_verified) {
        this.mobile_phone_verified = mobile_phone_verified;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public SalesforceUserStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(SalesforceUserStatus status) {
        this.status = status;
    }

    /**
     * Gets urls.
     *
     * @return the urls
     */
    public SalesforceUserUrls getUrls() {
        return urls;
    }

    /**
     * Sets urls.
     *
     * @param urls the urls
     */
    public void setUrls(SalesforceUserUrls urls) {
        this.urls = urls;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public String getUser_type() {
        return user_type;
    }

    /**
     * Sets user type.
     *
     * @param user_type the user type
     */
    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets locale.
     *
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets locale.
     *
     * @param locale the locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Gets utc offset.
     *
     * @return the utc offset
     */
    public long getUtcOffset() {
        return utcOffset;
    }

    /**
     * Sets utc offset.
     *
     * @param utcOffset the utc offset
     */
    public void setUtcOffset(long utcOffset) {
        this.utcOffset = utcOffset;
    }

    /**
     * Gets last modified date.
     *
     * @return the last modified date
     */
    public String getLast_modified_date() {
        return last_modified_date;
    }

    /**
     * Sets last modified date.
     *
     * @param last_modified_date the last modified date
     */
    public void setLast_modified_date(String last_modified_date) {
        this.last_modified_date = last_modified_date;
    }

    /**
     * Is is app installed boolean.
     *
     * @return the boolean
     */
    public boolean isIs_app_installed() {
        return is_app_installed;
    }

    /**
     * Sets is app installed.
     *
     * @param is_app_installed the is app installed
     */
    public void setIs_app_installed(boolean is_app_installed) {
        this.is_app_installed = is_app_installed;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets first name.
     *
     * @param first_name the first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets last name.
     *
     * @param last_name the last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets salesforce token.
     *
     * @return the salesforce token
     */
    public SalesforceToken getSalesforceToken() {
        return salesforceToken;
    }

    /**
     * Sets salesforce token.
     *
     * @param salesforceToken the salesforce token
     */
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
        List<GrantedAuthority> authorities = new ArrayList<>();
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
package com.revature.caliber.salesforce.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Martino on 1/22/2017.
 */
public class SalesforceUserUrls {
    @JsonProperty
    private String enterprise;
    @JsonProperty
    private String metadata;
    @JsonProperty
    private String partner;
    @JsonProperty
    private String rest;
    @JsonProperty
    private String sobjects;
    @JsonProperty
    private String search;
    @JsonProperty
    private String query;
    @JsonProperty
    private String recent;
    @JsonProperty
    private String profile;
    @JsonProperty
    private String feeds;
    @JsonProperty
    private String groups;
    @JsonProperty
    private String users;
    @JsonProperty
    private String feed_items;
    @JsonProperty
    private String feed_elements;
    @JsonProperty
    private String custom_domain;

    public SalesforceUserUrls() {
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getSobjects() {
        return sobjects;
    }

    public void setSobjects(String sobjects) {
        this.sobjects = sobjects;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getFeeds() {
        return feeds;
    }

    public void setFeeds(String feeds) {
        this.feeds = feeds;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getFeed_items() {
        return feed_items;
    }

    public void setFeed_items(String feed_items) {
        this.feed_items = feed_items;
    }

    public String getFeed_elements() {
        return feed_elements;
    }

    public void setFeed_elements(String feed_elements) {
        this.feed_elements = feed_elements;
    }

    public String getCustom_domain() {
        return custom_domain;
    }

    public void setCustom_domain(String custom_domain) {
        this.custom_domain = custom_domain;
    }

    @Override
    public String toString() {
        return "SalesforceUserUrls{" +
                "enterprise='" + enterprise + '\'' +
                ", metadata='" + metadata + '\'' +
                ", partner='" + partner + '\'' +
                ", rest='" + rest + '\'' +
                ", sobjects='" + sobjects + '\'' +
                ", search='" + search + '\'' +
                ", query='" + query + '\'' +
                ", recent='" + recent + '\'' +
                ", profile='" + profile + '\'' +
                ", feeds='" + feeds + '\'' +
                ", groups='" + groups + '\'' +
                ", users='" + users + '\'' +
                ", feed_items='" + feed_items + '\'' +
                ", feed_elements='" + feed_elements + '\'' +
                ", custom_domain='" + custom_domain + '\'' +
                '}';
    }
}

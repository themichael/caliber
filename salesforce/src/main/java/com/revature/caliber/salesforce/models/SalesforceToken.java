package com.revature.caliber.salesforce.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Shehar on 1/19/2017.
 */
public class SalesforceToken {
    @JsonProperty
    private String access_token;

    @JsonProperty
    private String refresh_token;

    @JsonProperty
    private String signature;

    @JsonProperty
    private String scope;

    @JsonProperty
    private String id_token;

    @JsonProperty
    private String instance_url;

    @JsonProperty
    private String id;

    @JsonProperty
    private String token_type;

    @JsonProperty
    private String issued_at;


    public SalesforceToken() {}

    public SalesforceToken(String access_token, String signature, String scope, String instance_url, String id, String token_type, String issued_at) {
        this.access_token = access_token;
        this.signature = signature;
        this.scope = scope;
        this.instance_url = instance_url;
        this.id = id;
        this.token_type = token_type;
        this.issued_at = issued_at;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public String getInstance_url() {
        return instance_url;
    }

    public void setInstance_url(String instance_url) {
        this.instance_url = instance_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(String issued_at) {
        this.issued_at = issued_at;
    }

    @Override
    public String toString() {
        return "SalesforceToken{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", signature='" + signature + '\'' +
                ", scope='" + scope + '\'' +
                ", instance_url='" + instance_url + '\'' +
                ", id='" + id + '\'' +
                ", token_type='" + token_type + '\'' +
                ", issued_at='" + issued_at + '\'' +
                '}';
    }
}

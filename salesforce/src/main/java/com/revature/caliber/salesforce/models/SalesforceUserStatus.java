package com.revature.caliber.salesforce.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Martino on 1/22/2017.
 */
public class SalesforceUserStatus {
    @JsonProperty
    private String created_date;
    @JsonProperty
    private String body;

    public SalesforceUserStatus() {
    }

    public SalesforceUserStatus(String created_date, String body) {
        this.created_date = created_date;
        this.body = body;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "SalesforceUserStatus{" +
                "created_date='" + created_date + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

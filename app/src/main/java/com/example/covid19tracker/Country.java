package com.example.covid19tracker;

public class Country {
    String flagUrl;
    String country;
    String total;
    String active;
    String recovered;
    String deaths;

    public Country(String flagUrl, String country, String total, String active, String recovered, String deaths) {
        this.flagUrl = flagUrl;
        this.country = country;
        this.total = total;
        this.active = active;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public String getFlag() {
        return flagUrl;
    }

    public void setFlag(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}

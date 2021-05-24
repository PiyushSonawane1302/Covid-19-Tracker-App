package com.example.covid19tracker;

public class TotalData {
    String total;
    String active;
    String recovered;
    String deaths;

    public TotalData(String total, String active, String recovered, String deaths) {
        this.total = total;
        this.active = active;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public String getTotal() {
        return total;
    }

    public String getActive() {
        return active;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}

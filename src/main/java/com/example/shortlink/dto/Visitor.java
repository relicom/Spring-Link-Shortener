package com.example.shortlink.dto;


/**
 * this class have not identifier field so it's can not to be part of JPA @Entity
 * it's can be used by JdbcTemplate
 */
//@Entity
//@Table(name = "visitors")
public class Visitor {

    private Integer urlId;
    private String ip;
    private Long date;

//    @Column(name = "urlid")
    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

//    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

//    @Column(name = "date")
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "urlId=" + urlId +
                ", ip='" + ip + '\'' +
                ", date=" + date +
                '}';
    }
}

package com.example.shortlink.dto;

import javax.persistence.*;

@Entity
@Table(name = "links")
public class Url {

    private Integer urlId;
    private String url;
    private Long date;
    private String link;
//    @OneToMany
//    @JoinColumn(name = "visitors", referencedColumnName = "urlid")
//    private List<Visitor> visitors;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "urlid")
    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "date")
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Url{" +
                "urlId=" + urlId +
                ", url='" + url + '\'' +
                ", date=" + date +
                ", link='" + link + '\'' +
                '}';
    }
}

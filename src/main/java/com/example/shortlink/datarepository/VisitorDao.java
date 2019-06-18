package com.example.shortlink.datarepository;

import com.example.shortlink.dto.Url;
import com.example.shortlink.dto.Visitor;
import com.example.shortlink.exception.LinkParameterException;
import com.example.shortlink.exception.VisitorParameterException;
import com.example.shortlink.util.Couple;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitorDao {

    private JdbcTemplate jdbcTemplate;
    private LinkRepository linkRepository;

    public VisitorDao(JdbcTemplate jdbcTemplate, LinkRepository linkRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.linkRepository = linkRepository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void insertVisitor(String link, Visitor visitor) throws Throwable {

        Integer urlIdByLink = linkRepository.getUrlIdByLink(link);
        if (urlIdByLink == null) {
            throw new LinkParameterException("link is not exist");
        }
        visitor.setUrlId(urlIdByLink);

        int affect = jdbcTemplate.update("insert into `visitors`(urlid,ip,date)values(?,?,?)",
                visitor.getUrlId(),
                visitor.getIp(),
                visitor.getDate());
        if (affect < 0) {
            throw new VisitorParameterException("Visitor Parameter is not valid and can not to be insert");
        }
    }

    @Transactional
    public Couple<Url, Integer> getLinkStatistics(String link) {
        Url url = linkRepository.findFirstByLink(link);
        if (url != null) {
            Integer statistics = jdbcTemplate.queryForObject("select count(*) from visitors where urlid=?",
                    Integer.class,
                    url.getUrlId());

            return new Couple<>(url, statistics);
        }
        return null;
    }

}

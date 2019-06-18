package com.example.shortlink.datarepository;

import com.example.shortlink.dto.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface LinkRepository extends JpaRepository<Url, Integer> {

    @Query(value = "select urlid from links where link=?1", nativeQuery = true)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Integer getUrlIdByLink(String link);

    Url findFirstByLink(String link);

    @Query(value = "select url from links where link=?1", nativeQuery = true)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    String getUrlAddressByLink(String link);

}

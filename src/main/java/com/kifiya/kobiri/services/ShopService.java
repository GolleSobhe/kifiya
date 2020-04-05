package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShopService {

    String INSERT = "insert into SHOP (DATE,EMAIL,NOM,TELEPHONE,VILLE,GERANT_ID)" +
            "values (?,?,?,?,?,?)";
    String SELECT = "select GERANT_ID, VILLE, NOM, EMAIL, TELEPHONE, DATE from SHOP";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Shop> findAll() {
        List<Shop> shops = jdbcTemplate.query(SELECT, BeanPropertyRowMapper.newInstance(Shop.class));
        return shops;
    }

    public Shop save(Shop shop) {

        shop.setDate(new Date());
        jdbcTemplate.update(INSERT, shop.getDate(), shop.getEmail(), shop.getNom(), shop.getTelephone(), shop.getVille(), shop.getGerant().getId());
        return shop;
    }
}

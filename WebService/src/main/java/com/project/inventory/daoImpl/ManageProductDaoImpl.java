package com.project.inventory.daoImpl;

import com.project.inventory.dao.ManageProductDao;
import com.project.inventory.entity.ProductDetails;
import com.project.inventory.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.inventory.InitialDataLoad.metaDataQueries;

@Repository
public class ManageProductDaoImpl implements ManageProductDao {

    @Autowired
    private JdbcClient jdbcClient;

    @Override
    public int saveProductDetails(ProductDetails productDetails) {
        return jdbcClient.sql(metaDataQueries.get("saveProductDetails"))
                .paramSource(productDetails)
                .update();
    }

    @Override
    public List<String> getAllProductCategory() {
        return jdbcClient.sql(metaDataQueries.get("getAllProductCategory"))
                .query(ProductDetails.class)
                .stream()
                .map(ProductDetails::getCategory)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDetails> getAllProductDetails() {
        return jdbcClient.sql(metaDataQueries.get("getAllProductDetails"))
                .query(ProductDetails.class)
                .list();
    }
}

package com.project.inventory;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component("InitialDataLoad")
@Service
public class InitialDataLoad {
    @Value("${server.port}")
    private int portNumber;
    public static final Map<String, String> metaDataQueries = new HashMap<>();
    private final DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(InitialDataLoad.class.getName());

    public InitialDataLoad(DataSource dataSource) { this.dataSource = dataSource; }

    public Map<String, String> getMetaDataQueries() {
        return metaDataQueries;
    }

    @PostConstruct
    public void fetchMetaDataQueries() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM REP_METADATA_QUERIES")) {
             ResultSet resultSet = statement.executeQuery();

             while (resultSet.next()) {
                 String metaDataName = resultSet.getString("METADATA_NAME");
                 String metaDataQuery = resultSet.getString("METADATA_QUERY");
                 metaDataQueries.put(metaDataName, metaDataQuery);
             }
        } catch (SQLException e) {
                logger.info("Error!! : ", e);
        }
    }

    public void updateServiceDetails() {
        System.out.println("Services are Up at http://localhost:" + portNumber +"/login.html");
    }
}

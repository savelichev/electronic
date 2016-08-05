package ua.savelichev.electronic.dao;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import ua.savelichev.electronic.dao.interfaces.IConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCleaner {

    private static final Logger log = Logger.getLogger(TableCleaner.class);

    IConnectionFactory connectionFactoryForTest = ConnectionFactoryForTest.getInstance();

    public void cleanTables(String... tablesForClean) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactoryForTest.getConnection();

            for (String tableName : tablesForClean) {
                preparedStatement = connection.prepareStatement("DELETE FROM " + tableName);
                preparedStatement.executeUpdate();
                log.debug("Cleaned table: " + tableName);
            }

        } catch (SQLException | NamingException e) {
            log.error("Exception: " + e);
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.error("Exception during  closing resources: " + e);
                e.printStackTrace();
            }
        }
    }

    /*
     * Use when you want to clear all test tables
     * look carefully  on connectionFactoryForTest instance!!!
     */
    @Ignore
    @Test
    public void cleanAllTables() {
        String[] tables = {
//                "audio",
//                "camera",
//                "tv",
                "notebook",
                "phone",
//                "accessory"
                "storage",
                "orders",
                "order_item",
                "user"
        };

        cleanTables(tables);
    }
}

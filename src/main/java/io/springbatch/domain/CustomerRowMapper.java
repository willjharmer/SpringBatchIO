package io.springbatch.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by willharmer on 09/10/2016.
 */
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Customer(resultSet.getLong("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getDate("birthdate"));
    }
}

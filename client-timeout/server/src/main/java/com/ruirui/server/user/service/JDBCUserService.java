package com.ruirui.server.user.service;

import com.ruirui.server.user.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class JDBCUserService implements UserService {

    private JdbcTemplate jdbcTemplate;

    public JDBCUserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUser(UserDTO user) throws InterruptedException {
        jdbcTemplate.update("insert into user " +
                "(name, age) values (?, ?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
            }
        });

        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    @Override
    public List<UserDTO> listUsers() {
        return jdbcTemplate.query("select name, age from user", new RowMapper<UserDTO>() {
            @Override
            public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserDTO user = new UserDTO();
                user.setName(rs.getString(1));
                user.setAge(rs.getInt(2));
                return user;
            }
        });
    }
}

package com.example.backend.user.repository;

import com.example.backend.user.model.UserWithRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthDao {

    private final JdbcTemplate jdbc;

    public AuthDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<UserWithRole> findByEmail(String email) {
        String sql = """
            SELECT u.USER_ID, u.EMAIL, u.PASSWORD, r.ROLE_CD
            FROM TB_USER u
            JOIN TB_ROLE r ON u.ROLE_ID = r.ROLE_ID
            WHERE u.EMAIL = ?
        """;

        return jdbc.query(sql, new Object[]{email}, rs -> {
            if (rs.next()) {
                UserWithRole user = new UserWithRole();
                user.setUserId(rs.getInt("USER_ID"));
                user.setEmail(rs.getString("EMAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setRoleCd(rs.getString("ROLE_CD"));
                return Optional.of(user);
            }
            return Optional.empty();
        });
    }
}

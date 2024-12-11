package uz.pdp.testmaster.bot.DB;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import java.sql.Connection;

public class DBConfig {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/testmaster");
        config.setUsername("postgres");
        config.setPassword("root123");
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        dataSource = new HikariDataSource(config);
    }

    @SneakyThrows
    public static Connection getConnection() {
        return dataSource.getConnection();
    }

}

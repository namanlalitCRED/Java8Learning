package in.dreamplug.jobportal.health;

import com.codahale.metrics.health.HealthCheck;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;

@AllArgsConstructor
public class JobHealthCheck extends HealthCheck {

    private HikariDataSource dataSource;

    @Override
    protected Result check() throws Exception {
        final Connection connection = this.dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM DUAL");
        try {
            final boolean res = preparedStatement.execute();
            if (res) {
                System.out.println("DB working properly");
                return Result.healthy();
            } else {
                return Result.unhealthy("Error Connecting to MYSQL");
            }
        } catch (final Exception ex) {
            return Result.unhealthy(ex);
        }
    }
}

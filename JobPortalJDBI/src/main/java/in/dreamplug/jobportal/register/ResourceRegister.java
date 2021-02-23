package in.dreamplug.jobportal.register;

import com.google.common.base.Strings;
import com.zaxxer.hikari.HikariDataSource;
import in.dreamplug.jobportal.config.DataBaseConfiguration;
import in.dreamplug.jobportal.config.JobServiceConfiguration;
import in.dreamplug.jobportal.health.JobHealthCheck;
import in.dreamplug.jobportal.resource.JobResource;
import in.dreamplug.jobportal.service.JobService;
import io.dropwizard.setup.Environment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


@AllArgsConstructor
public class ResourceRegister {
    public void register(final Environment environment, final JobServiceConfiguration configuration){

        final HikariDataSource dataSource = createDataSource(environment, configuration.getDataBaseConfiguration());
        final Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        final JobService jobService = new JobService(jdbi);
        environment.jersey().register(new JobResource(jobService));
        environment.healthChecks().register("MYSQL", new JobHealthCheck(dataSource));

    }


    private HikariDataSource createDataSource(Environment environment, DataBaseConfiguration databaseConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(databaseConfig.getUrl());
        dataSource.setUsername(databaseConfig.getUser());
        dataSource.setPassword(databaseConfig.getPassword());
        dataSource.setMinimumIdle(databaseConfig.getMinPoolSize());
        dataSource.setMaximumPoolSize(databaseConfig.getMaxPoolSize());
        dataSource.setIdleTimeout((long)databaseConfig.getMaxIdleTime());
        dataSource.setMaxLifetime((long)databaseConfig.getMaxLifeTime());
        dataSource.setAutoCommit(databaseConfig.isAutoCommit());
        dataSource.setReadOnly(databaseConfig.isReadOnly());
        dataSource.setConnectionInitSql(databaseConfig.getTestStatement());
        dataSource.setConnectionTimeout((long)databaseConfig.getConnectionTimeout());
        if (!Strings.isNullOrEmpty(databaseConfig.getPoolName())) {
            dataSource.setPoolName(databaseConfig.getPoolName());
        }
        dataSource.setMetricRegistry(environment.metrics());
        return dataSource;
    }
}

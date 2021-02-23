package in.dreamplug.jobportal;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import in.dreamplug.jobportal.config.JobServiceConfiguration;
import in.dreamplug.jobportal.register.ResourceRegister;
import in.dreamplug.jobportal.resource.JobResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.configuration.ConfigurationSourceProvider;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class JobServiceApplication extends Application<JobServiceConfiguration> {



    public static void main(String[] args) throws Exception{
        new JobServiceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<JobServiceConfiguration> bootstrap) {
        ConfigurationSourceProvider provider = new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false));
        bootstrap.setConfigurationSourceProvider(provider);
    }

    @Override
    public void run(JobServiceConfiguration configuration, Environment environment) throws Exception {

        final ResourceRegister resourceRegister = new ResourceRegister();
        resourceRegister.register(environment, configuration);
        environment.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }
}

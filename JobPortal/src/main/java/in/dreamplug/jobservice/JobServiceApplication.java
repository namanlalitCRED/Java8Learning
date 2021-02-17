package in.dreamplug.jobservice;

import in.dreamplug.jobservice.config.JobServiceConfiguration;
import in.dreamplug.jobservice.service.JobService;
import in.dreamplug.jobservice.core.JobRepo;
import in.dreamplug.jobservice.resource.JobResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class JobServiceApplication extends Application<JobServiceConfiguration> {

    public static void main(String[] args) throws Exception{
        new JobServiceApplication().run(args);
    }

    @Override
    public void run(JobServiceConfiguration jobServiceConfiguration, Environment environment) throws Exception {

//      An Instance of the Interface is being created so that the APIs can access it.
        final JobRepo jobRepo = new JobService();

//      The instance created above is passed as the parameter here, through which the APIs can access the in-memory database.
        final JobResource jobResource = new JobResource(jobRepo);

//        Registering a resource
        environment.jersey().register(jobResource);
    }

}

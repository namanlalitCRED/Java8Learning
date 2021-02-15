package in.dreamplug.demo;

import in.dreamplug.demo.health.DemoHealthCheck;
import in.dreamplug.demo.resource.DemoResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class HelloApplication extends Application<HelloConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloApplication().run(new String[] {"server", "config.yml"});
    }

    @Override
    public void run(HelloConfiguration helloConfiguration, Environment environment) throws Exception {
        final DemoResource demoResource = new DemoResource(helloConfiguration.getMessage(), helloConfiguration.getFirstParameter(),
                helloConfiguration.getSecondParameter());

        final DemoHealthCheck demoHealthCheck = new DemoHealthCheck();
        environment.healthChecks().register("Naman", demoHealthCheck);

        environment.jersey().register(demoResource);
    }
}

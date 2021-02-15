package in.dreamplug.demo.health;

import com.codahale.metrics.health.HealthCheck;

public class DemoHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        final String field = "Naman!";
        if(!field.equalsIgnoreCase("Naman")) {
            return Result.healthy();
        }
        return Result.unhealthy("Error, this is not healthy!");
    }
}

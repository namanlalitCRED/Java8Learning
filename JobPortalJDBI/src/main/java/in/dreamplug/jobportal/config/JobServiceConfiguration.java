package in.dreamplug.jobportal.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class JobServiceConfiguration extends Configuration {
    private DataBaseConfiguration dataBaseConfiguration;

}

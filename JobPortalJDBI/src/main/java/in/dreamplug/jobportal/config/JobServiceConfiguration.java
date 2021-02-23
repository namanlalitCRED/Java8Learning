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
//    @Valid
//    @NotNull
//    private DataSourceFactory database = new DataSourceFactory();
//
//        @JsonProperty("database")
//        public void setDataSourceFactory(DataSourceFactory factory) {
//            this.database = factory;
//        }
//
//        @JsonProperty("database")
//        public DataSourceFactory getDataSourceFactory() {
//            return database;
//        }
}

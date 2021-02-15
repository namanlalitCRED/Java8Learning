package in.dreamplug.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Representation {
    private String data;

    public Representation(String data) {
        this.data = data;
    }

    @JsonProperty
    public java.lang.String getData() {
        return data;
    }

    @JsonProperty
    public void setData(String message){
        this.data = message;
    }

}

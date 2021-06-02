package io.helidon.examples.quickstart.mp;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.Collections;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ResponseService {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private final GreetingProvider greetingProvider;

    public JsonObject createResponse(String who) {
        String msg = String.format("%s %s!", greetingProvider.getMessage(), who);

        return JSON.createObjectBuilder()
                .add("message", msg)
                .build();
    }

    public void setMessage(JsonObject jsonObject) {
        String newGreeting = jsonObject.getString("greeting");

        greetingProvider.setMessage(newGreeting);
    }

    public JsonObject buildError() {
        return JSON.createObjectBuilder()
                .add("error", "No greeting provided")
                .build();
    }
}

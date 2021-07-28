package br.com.alura.challenge.back.feature;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import static java.lang.ThreadLocal.withInitial;

public enum TestContext {

    CONTEXT;

    private static final String RESPONSE = "RESPONSE";
    private static final String PAYLOAD = "PAYLOAD";

    private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) testContexts.get().get(name);
    }

    public <T> T set(String name, T object) {
        testContexts.get().put(name, object);
        return object;
    }

    public <T> ResponseEntity<T> getResponse() {
        return get(RESPONSE);
    }

    public <T> void setResponse(ResponseEntity<T> response) {
        set(RESPONSE, response);
    }

    public <T> T getPayload(Class<T> clazz) {
        return clazz.cast(get(PAYLOAD));
    }

    public <T> void setPayload(T object) {
        set(PAYLOAD, object);
    }

    public void reset() {
        testContexts.get().clear();
    }    
}

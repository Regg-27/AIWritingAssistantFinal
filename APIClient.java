package model;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;


public class APIClient {

    private static final APIClient INSTANCE = new APIClient();

    private final OpenAIClient client;

    private APIClient() {
        this.client = OpenAIOkHttpClient.fromEnv();
    }

    public static APIClient getInstance() {
        return INSTANCE;
    }

    public OpenAIClient getClient() {
        return client;
    }
}


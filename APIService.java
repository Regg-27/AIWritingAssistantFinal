package service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class APIService {

    private final OpenAIClient client;

    public APIService() {
        this.client = OpenAIOkHttpClient.fromEnv();
    }


    protected ChatCompletion sendRequest(ChatCompletionCreateParams params) {
        return client.chat().completions().create(params);
    }


    public String generateText(String prompt) {

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model(ChatModel.GPT_4_1_MINI)
                .addUserMessage(prompt)
                .maxCompletionTokens(200L)
                .build();

        ChatCompletion completion = sendRequest(params);

        return completion.choices()
                .get(0)
                .message()
                .content()
                .orElse("");
    }
}

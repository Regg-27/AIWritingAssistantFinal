package service;

import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class APIServiceTest {


    @Test
    void generateTextPropagatesException() {

        APIService svc = new APIService() {
            @Override
            protected ChatCompletion sendRequest(ChatCompletionCreateParams params) {
                throw new RuntimeException("Network failure");
            }
        };

        assertThrows(RuntimeException.class, () -> svc.generateText("hello"));
    }
}


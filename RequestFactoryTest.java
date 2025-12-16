package service;

import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import model.strategy.CreativeStrategy;
import model.strategy.WritingStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestFactoryTest {

    @Test
    void factoryBuildsValidChatRequest() {
        WritingStrategy strat = new CreativeStrategy();

        ChatCompletionCreateParams params =
                RequestFactory.buildChatRequest(strat, "hello world");

        assertNotNull(params);
        assertEquals(ChatModel.GPT_4_1_MINI, params.model());
        assertFalse(params.messages().isEmpty());
    }
}





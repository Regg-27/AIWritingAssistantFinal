package service;

import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import model.strategy.WritingStrategy;

public class RequestFactory {

    public static ChatCompletionCreateParams buildChatRequest(
            WritingStrategy strategy,
            String userInput
    ) {
        String prompt = strategy.buildPrompt(userInput);

        return ChatCompletionCreateParams.builder()
                .model(ChatModel.GPT_4_1_MINI)
                .addUserMessage(prompt)
                .maxCompletionTokens(200L)
                .build();
    }
}


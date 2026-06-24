package org.example.webflux.model.llmclient.gpt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.LlmChatRequestDto;
import org.example.webflux.model.llmclient.LlmModel;
import org.example.webflux.model.llmclient.gpt.GptMessageRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GptChatRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 6674873513869421361L;

    private List<GptCompletionRequestDto> messages;
    private LlmModel model;
    private Boolean stream;
    private GptResponseFormat response_format;

    public GptChatRequestDto(LlmChatRequestDto llmChatRequestDto) {
        if (llmChatRequestDto.isUseJson()) {
            this.response_format = new GptResponseFormat("json_object");
        }
        this.messages = List.of(new GptCompletionRequestDto(GptMessageRole.SYSTEM, llmChatRequestDto.getSystemPrompt()),
                new GptCompletionRequestDto(GptMessageRole.USER, llmChatRequestDto.getUserRequest()));
        this.model = llmChatRequestDto.getLlmModel();
    }
}

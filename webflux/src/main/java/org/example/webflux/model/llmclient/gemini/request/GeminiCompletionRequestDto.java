package org.example.webflux.model.llmclient.gemini.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webflux.model.llmclient.gemini.GeminiMessageRole;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeminiCompletionRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4288334660179313461L;

    private GeminiMessageRole role;
    private String content; // 채팅 내용

    public GeminiCompletionRequestDto(String content) {
        this.content = content;
    }
}

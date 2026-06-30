package org.example.webflux.service.llmclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webflux.exception.CommonError;
import org.example.webflux.exception.CustomErrorType;
import org.example.webflux.exception.ErrorTypeException;
import org.example.webflux.model.llmclient.LlmChatRequestDto;
import org.example.webflux.model.llmclient.LlmChatResponseDto;
import org.example.webflux.model.llmclient.LlmType;
import org.example.webflux.model.llmclient.gemini.request.GeminiChatRequestDto;
import org.example.webflux.model.llmclient.gemini.response.GeminiChatResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeminiWebClientService implements LlmWebClientService {

    private final WebClient webClient;
    @Value("${llm.gemini.key}")
    private String geminiApiKey;

    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto) {
        GeminiChatRequestDto geminiChatRequestDto = new GeminiChatRequestDto(requestDto);
        return webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + geminiApiKey)
                .bodyValue(geminiChatRequestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(String.class).flatMap(body -> {
                        log.error("Error Response: {}", body);
                        return Mono.error(new ErrorTypeException("API 요청 실패: " + body, CustomErrorType.GEMINI_RESPONSE_ERROR));
                    });
                }))
                .bodyToMono(GeminiChatResponseDto.class)
                .map(LlmChatResponseDto::new);
    }

    @Override
    public LlmType getLlmType() {
        return LlmType.GEMINI;
    }

    @Override
    public Flux<LlmChatResponseDto> getChatCompletionStream(LlmChatRequestDto requestDto) {
        GeminiChatRequestDto geminiChatRequestDto = new GeminiChatRequestDto(requestDto);
        return webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:streamGenerateContent?key=" + geminiApiKey)
                .bodyValue(geminiChatRequestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(String.class).flatMap(body -> {
                        log.error("Error Response: {}", body);
                        return Mono.error(new ErrorTypeException("API 요청 실패: " + body, CustomErrorType.GEMINI_RESPONSE_ERROR));
                    });
                }))
                .bodyToFlux(GeminiChatResponseDto.class)
                .map(LlmChatResponseDto::new);
    }
}

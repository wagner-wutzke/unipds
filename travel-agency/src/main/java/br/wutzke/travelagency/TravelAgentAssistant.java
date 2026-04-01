package br.wutzke.travelagency;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface TravelAgentAssistant {

    /**
     * O método 'chat' recebe a mensagem do usuário e retorna a resposta do LLM.
     * @param userMessage A mensagem do usuário.
     * @return A resposta gerada pelo modelo de linguagem.
     */
    String chat(String userMessage);
}

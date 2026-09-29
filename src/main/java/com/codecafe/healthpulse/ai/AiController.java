package com.codecafe.healthpulse.ai;

// ─────────────────────────────────────────────────────────────────────────────
// REFERENCE ONLY: Spring AI basic ChatClient
//
// To enable:
//   1. pom.xml -> add inside <dependencyManagement> (create it if missing):
//        <dependency>
//            <groupId>org.springframework.ai</groupId>
//            <artifactId>spring-ai-bom</artifactId>
//            <version>2.0.0</version>
//            <type>pom</type>
//            <scope>import</scope>
//        </dependency>
//      and inside <dependencies>:
//        <dependency>
//            <groupId>org.springframework.ai</groupId>
//            <artifactId>spring-ai-starter-model-openai</artifactId>
//        </dependency>
//   2. application.properties -> add:
//        spring.ai.openai.api-key=${OPENAI_API_KEY}
//        spring.ai.openai.chat.options.model=gpt-4o-mini
//      (set OPENAI_API_KEY as an environment variable; never hardcode the key)
//   3. Uncomment everything below.
//   4. Endpoints are under "ai/**", so they are PRIVATE (need JWT / Basic login).
//
// Other provider: swap the starter (spring-ai-starter-model-anthropic) and the
// property prefix (spring.ai.anthropic.*). The ChatClient code below stays the same.
// ─────────────────────────────────────────────────────────────────────────────

//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AiController {
//
//    private final ChatClient chatClient;
//
//    // Spring AI auto-configures ChatClient.Builder; build() gives the ChatClient
//    public AiController(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//    }
//
//    // GET http://localhost:9090/ai/chat?message=what is triage
//    @GetMapping("ai/chat")
//    public String chat(@RequestParam String message) {
//        return chatClient.prompt()
//                .user(message)
//                .call()
//                .content();
//    }
//
//    // GET http://localhost:9090/ai/triage?symptoms=chest pain and shortness of breath
//    // system() sets the AI's role/rules; user() is the actual input
//    @GetMapping("ai/triage")
//    public String triage(@RequestParam String symptoms) {
//        return chatClient.prompt()
//                .system("You are an emergency ward assistant. Reply with one word: CRITICAL, URGENT or ROUTINE.")
//                .user(symptoms)
//                .call()
//                .content();
//    }
//}

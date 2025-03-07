package it.unibo.device;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import it.unibo.prompt.zero.ZeroShotAgent;
import dev.langchain4j.model.chat.*;
import dev.langchain4j.model.ollama.*;
import dev.langchain4j.model.*;
import dev.langchain4j.model.chat.response.*;
import dev.langchain4j.data.message.*;

class GenerateDeviceImpl {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");

		final OllamaStreamingChatModel model = OllamaStreamingChatModel.builder().baseUrl("http://localhost:11434")
				.logRequests(true).logResponses(true).modelName("smollm").temperature(0.0).build();

		// final ChatLanguageModel model1 = OllamaChatModel.builder()
		// .baseUrl("http://localhost:11434")
		// .logRequests(true)
		// .logResponses(true)
		// .modelName("deepseek-r1:1.5b")
		// .temperature(0.0)
		// .build();
		// final var zeroShot = new ZeroShotAgent(model1, "");

		var query = """
				    Generate the unit tests for implementing this interface in a TDD-like way, write just the code without anything description:

				    package it.unibo.device;

				    public interface Device {
				        void on() throws IllegalStateException;

				        void off();

				        boolean isOn();

				        void reset();
				    }
				""";
		query = "hi!";

		var thread = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {

					}
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		};
		thread.start();

		model.chat(query, new StreamingChatResponseHandler() {
			public void onPartialResponse(String partialResponse) {
				System.out.print(partialResponse);
			}

			public void onCompleteResponse(ChatResponse completeResponse) {
				System.out.println("Finished!");
				thread.interrupt();
			}

			public void onError(Throwable error) {
				System.out.println("Error: " + error.toString());
			}
		});
	}
}

package br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint.json.DiscordRequestBodyJson;
import br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint.json.MessageJson;
import br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint.json.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class LambdaEntrypoint implements RequestHandler<Object, ResponseJson> {

	private ObjectMapper objectMapper;
	
	public LambdaEntrypoint() {
		objectMapper = new ObjectMapper();
	}
	
	@Override
	public ResponseJson handleRequest(Object input, Context context) {
		try {
			log.trace("Start input={}, context={}", input, context);
			System.out.println("input=" + input);
			System.out.println("context=" + context);
			
			String messageJsonStr = getMessage(input + "");
			System.out.println("messageJson=" + messageJsonStr);
			
			MessageJson messageJson = objectMapper.readValue(messageJsonStr, MessageJson.class);
			
			sendToDiscord(messageJson);
			
			String response = messageJson.getMessage();
			
			Map<String, String> headers = new HashMap<>();
			headers.put("Access-Control-Allow-Origin", "*");
			headers.put("Content-Type", "application/json");
			ResponseJson responseJson = new ResponseJson(false, 200, headers, response);
			System.out.println(responseJson);
			log.trace("End responseJson={}", responseJson);
			return responseJson;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			return new ResponseJson(false, 500, new HashMap<>(), e.getMessage());//TODO testar
		}
	}
	
	private String getMessage(String input) throws JsonProcessingException {
		List<String> inputs = Arrays.asList(input.split("="));
		
		List<String> messages = inputs.stream().filter(i -> i.contains("AlarmName") && i.contains("AlarmDescription")).toList();

		if(messages.isEmpty()) {
			MessageJson messageJson = MessageJson.builder()
					.alarmName("Não Identificado")
					.alarmDescription("Não foi possível identificar a causa do alarme. Favor checar os dashboards de monitoramento")
					.newStateReason("Não Identificado")
					.stateChangeTime(LocalDateTime.now().toString())
					.build();
			return objectMapper.writeValueAsString(messageJson);
		}
		
		return messages.get(0);
	}
	
	private void sendToDiscord(MessageJson messageJson) throws IOException {
		
		DiscordRequestBodyJson requestBody = new DiscordRequestBodyJson(messageJson);
		String requestBodyStr = objectMapper.writeValueAsString(requestBody);
		
		final String discordUrlWebHook = System.getenv("DISCORD_URL_WEBHOOK");
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				
				RequestBody body = RequestBody.create(requestBodyStr, mediaType);
				
				Request request = new Request.Builder()
				  .url(discordUrlWebHook)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				Response response = client.newCall(request).execute();
				System.out.println("discord http response: " + response.code());
	}
	
}

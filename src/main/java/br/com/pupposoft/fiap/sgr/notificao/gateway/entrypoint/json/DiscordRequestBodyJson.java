package br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint.json;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DiscordRequestBodyJson {
	
	private List<EmbedsJson> embeds;

	public DiscordRequestBodyJson(MessageJson messageJson) {
		
		String color = messageJson.getNewStateValue().equals("ALARM") ? "16711680" : "65280";
		
		embeds = Arrays.asList(new EmbedsJson(messageJson.getNewStateValue() + " - " + messageJson.getAlarmName(), color, messageJson.getMessage()));
	}
	
	@Getter
	@AllArgsConstructor
	private class EmbedsJson {
		private String title;
		private String color;
		private String description;
	}

}

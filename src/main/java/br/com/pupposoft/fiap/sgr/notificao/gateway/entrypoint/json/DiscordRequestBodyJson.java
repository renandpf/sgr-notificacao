package br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint.json;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DiscordRequestBodyJson {
	
	private List<EmbedsJson> embeds;

	public DiscordRequestBodyJson(MessageJson messageJson) {
		embeds = Arrays.asList(new EmbedsJson(messageJson.getAssunto(), "65280", messageJson.getConteudo()));
	}
	
	@Getter
	@AllArgsConstructor
	private class EmbedsJson {
		private String title;
		private String color;
		private String description;
	}

}

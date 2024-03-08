package br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageJson {
	
	@JsonProperty("AlarmName")
	private String alarmName;
	
	@JsonProperty("AlarmDescription")
	private String alarmDescription;
	
	@JsonProperty("NewStateValue")
	private String newStateValue;
	
	@JsonProperty("NewStateReason")
	private String newStateReason;
	
	@JsonProperty("StateChangeTime")
	private String stateChangeTime;
	
	@JsonIgnore
	public String getMessage() {
		return alarmDescription + " -> " + newStateReason +"[" + stateChangeTime + "]";
	}
}

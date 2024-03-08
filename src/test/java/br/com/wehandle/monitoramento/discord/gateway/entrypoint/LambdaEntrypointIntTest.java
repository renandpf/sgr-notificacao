package br.com.wehandle.monitoramento.discord.gateway.entrypoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import br.com.pupposoft.fiap.sgr.notificao.gateway.entrypoint.LambdaEntrypoint;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
@Disabled
class LambdaEntrypointIntTest {

	@SystemStub
	private EnvironmentVariables environmentVariables;

	@BeforeEach
	void init() {
		environmentVariables.set("DISCORD_URL_WEBHOOK", "XXX");
	}

	
	@Test
	void shouldSucessExistentAlarm() {
		
		LambdaEntrypoint lambdaEntrypoint = new LambdaEntrypoint();
		
		lambdaEntrypoint.handleRequest(getInput(), null);
		
	}
	
	@Test
	void shouldSucessNoExistentAlarm() {
		
		LambdaEntrypoint lambdaEntrypoint = new LambdaEntrypoint();
		
		lambdaEntrypoint.handleRequest(getInputNoMessage(), null);
		
	}
	
	private String getInput() {
		
		return "{Records=[{EventSource=aws:sns, EventVersion=1.0, EventSubscriptionArn=arn:aws:sns:sa-east-1:622547859380:monitoriamento:d86dba16-8c3a-4247-a562-8908f4e65ea5, Sns={Type=Notification, MessageId=4f45581d-5636-56c4-a9c1-067cd57ebd6d, TopicArn=arn:aws:sns:sa-east-1:622547859380:monitoriamento, Subject=ALARM: \"CORE - DB - USO CPU\" in South America (Sao Paulo), Message={\"AlarmName\":\"TESTE TESTE - IGNORAR\",\"AlarmDescription\":\"DESCRIÇÃO DO ALARME\",\"AWSAccountId\":\"622547859380\",\"AlarmConfigurationUpdatedTimestamp\":\"2024-03-05T20:30:45.526+0000\",\"NewStateValue\":\"OK\",\"NewStateReason\":\"Threshold Crossed: 1 out of the last 1 datapoints [23.196333333333335 (05/03/24 20:25:00)] was greater than the threshold (20.0) (minimum 1 datapoint for OK -> ALARM transition).\",\"StateChangeTime\":\"2024-03-05T20:31:04.488+0000\",\"Region\":\"South America (Sao Paulo)\",\"AlarmArn\":\"arn:aws:cloudwatch:sa-east-1:622547859380:alarm:CORE - DB - USO CPU\",\"OldStateValue\":\"OK\",\"OKActions\":[],\"AlarmActions\":[\"arn:aws:sns:sa-east-1:622547859380:monitoriamento\"],\"InsufficientDataActions\":[],\"Trigger\":{\"MetricName\":\"CPUUtilization\",\"Namespace\":\"AWS/RDS\",\"StatisticType\":\"Statistic\",\"Statistic\":\"AVERAGE\",\"Unit\":null,\"Dimensions\":[{\"value\":\"wehandle\",\"name\":\"DBInstanceIdentifier\"}],\"Period\":300,\"EvaluationPeriods\":1,\"DatapointsToAlarm\":1,\"ComparisonOperator\":\"GreaterThanThreshold\",\"Threshold\":20.0,\"TreatMissingData\":\"missing\",\"EvaluateLowSampleCountPercentile\":\"\"}}, Timestamp=2024-03-05T20:31:04.544Z, SignatureVersion=1, Signature=TFFbdl1tHPML2cTpVNfL94HCbTxWHGcUToqXrkhc7sAm0MwvqpXsz1m9ucNFcTKojEpmI6eEennnyPxPXoNgMxI9j0OSuHZhVviukSgiZfAzgZcN0NvmZ4gBceZMMdfkjcuIF5+B82kPA8wZit8OSGzeq8ns2oIFVljIko6lnbB0AfbK/VokhP/xKtcRxaaFmBKkusCDFtXycWT2cN+fuo5uynBDlv9TJcUp4B2wQAXvkPPeHh3hc165L1yfyqZVlG90RzrcFtQdHQDfJRef84neNZS0aVNJvEp2bt41Jg2AjiWzHaevK5fnmGVrCDn40ZsO7nDbrMS24yuoqKAB9A==, SigningCertUrl=https://sns.sa-east-1.amazonaws.com/SimpleNotificationService-60eadc530605d63b8e62a523676ef735.pem, UnsubscribeUrl=https://sns.sa-east-1.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:sa-east-1:622547859380:monitoriamento:d86dba16-8c3a-4247-a562-8908f4e65ea5, MessageAttributes={}}}]}";
		
	}
	
	private String getInputNoMessage() {
		
		return "{Records=[{EventSource=aws:sns, EventVersion=1.0, EventSubscriptionArn=arn:aws:sns:sa-east-1:622547859380:monitoriamento:d86dba16-8c3a-4247-a562-8908f4e65ea5, Sns={Type=Notification, MessageId=4f45581d-5636-56c4-a9c1-067cd57ebd6d, TopicArn=arn:aws:sns:sa-east-1:622547859380:monitoriamento, Subject=ALARM: \"CORE - DB - USO CPU\" in South America (Sao Paulo), Message={\"DIFF\":\"TESTE TESTE - IGNORAR\",\"DIFFF\":\"DESCRIÇÃO DO ALARME\",\"AWSAccountId\":\"622547859380\",\"AlarmConfigurationUpdatedTimestamp\":\"2024-03-05T20:30:45.526+0000\",\"NewStateValue\":\"ALARM\",\"NewStateReason\":\"Threshold Crossed: 1 out of the last 1 datapoints [23.196333333333335 (05/03/24 20:25:00)] was greater than the threshold (20.0) (minimum 1 datapoint for OK -> ALARM transition).\",\"StateChangeTime\":\"2024-03-05T20:31:04.488+0000\",\"Region\":\"South America (Sao Paulo)\",\"AlarmArn\":\"arn:aws:cloudwatch:sa-east-1:622547859380:alarm:CORE - DB - USO CPU\",\"OldStateValue\":\"OK\",\"OKActions\":[],\"AlarmActions\":[\"arn:aws:sns:sa-east-1:622547859380:monitoriamento\"],\"InsufficientDataActions\":[],\"Trigger\":{\"MetricName\":\"CPUUtilization\",\"Namespace\":\"AWS/RDS\",\"StatisticType\":\"Statistic\",\"Statistic\":\"AVERAGE\",\"Unit\":null,\"Dimensions\":[{\"value\":\"wehandle\",\"name\":\"DBInstanceIdentifier\"}],\"Period\":300,\"EvaluationPeriods\":1,\"DatapointsToAlarm\":1,\"ComparisonOperator\":\"GreaterThanThreshold\",\"Threshold\":20.0,\"TreatMissingData\":\"missing\",\"EvaluateLowSampleCountPercentile\":\"\"}}, Timestamp=2024-03-05T20:31:04.544Z, SignatureVersion=1, Signature=TFFbdl1tHPML2cTpVNfL94HCbTxWHGcUToqXrkhc7sAm0MwvqpXsz1m9ucNFcTKojEpmI6eEennnyPxPXoNgMxI9j0OSuHZhVviukSgiZfAzgZcN0NvmZ4gBceZMMdfkjcuIF5+B82kPA8wZit8OSGzeq8ns2oIFVljIko6lnbB0AfbK/VokhP/xKtcRxaaFmBKkusCDFtXycWT2cN+fuo5uynBDlv9TJcUp4B2wQAXvkPPeHh3hc165L1yfyqZVlG90RzrcFtQdHQDfJRef84neNZS0aVNJvEp2bt41Jg2AjiWzHaevK5fnmGVrCDn40ZsO7nDbrMS24yuoqKAB9A==, SigningCertUrl=https://sns.sa-east-1.amazonaws.com/SimpleNotificationService-60eadc530605d63b8e62a523676ef735.pem, UnsubscribeUrl=https://sns.sa-east-1.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:sa-east-1:622547859380:monitoriamento:d86dba16-8c3a-4247-a562-8908f4e65ea5, MessageAttributes={}}}]}";
		
	}
	
}

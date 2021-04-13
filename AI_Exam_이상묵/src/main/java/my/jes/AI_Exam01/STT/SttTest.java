package my.jes.AI_Exam01.STT;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class SttTest {

    // Clova Speech secret key
	private static final String SECRET = "학원에서 제공해주는 시크릿 키 값";
    // Clova Speech invoke URL
	private static final String INVOKE_URL = "학원에서 제공해주는 유알엘 값";

	private CloseableHttpClient httpClient = HttpClients.createDefault();
	private Gson gson = new Gson();

	private static final Header[] HEADERS = new Header[] {
		new BasicHeader("Accept", "application/json"),
		new BasicHeader("X-CLOVASPEECH-API-KEY", SECRET),
	};

	public static class Boosting {
		private String words;

		public String getWords() {
			return words;
		}

		public void setWords(String words) {
			this.words = words;
		}
	}

	public static class Diarization {
		private Boolean enable = Boolean.FALSE;
		private Integer speakerCountMin;
		private Integer speakerCountMax;

		public Boolean getEnable() {
			return enable;
		}

		public void setEnable(Boolean enable) {
			this.enable = enable;
		}

		public Integer getSpeakerCountMin() {
			return speakerCountMin;
		}

		public void setSpeakerCountMin(Integer speakerCountMin) {
			this.speakerCountMin = speakerCountMin;
		}

		public Integer getSpeakerCountMax() {
			return speakerCountMax;
		}

		public void setSpeakerCountMax(Integer speakerCountMax) {
			this.speakerCountMax = speakerCountMax;
		}
	}

	public static class KeywordExtractionContext {
		private String script;
		private String category;
		private Long timestamp;

		public String getScript() {
			return script;
		}

		public void setScript(String script) {
			this.script = script;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
	}

	public static class KeywordExtraction {
		private Boolean enable;
		private List<KeywordExtractionContext> context;

		public Boolean getEnable() {
			return enable;
		}

		public void setEnable(Boolean enable) {
			this.enable = enable;
		}

		public List<KeywordExtractionContext> getContext() {
			return context;
		}

		public void setContext(List<KeywordExtractionContext> context) {
			this.context = context;
		}
	}

	public static class NestRequestEntity {
		private String language = "ko-KR";
		//completion optional, sync/async
		private String completion = "sync";
		//optional, used to receive the analyzed results
		private String callback;
		//optional, any data
		private Map<String, Object> userdata;
		private Boolean sttEnable = Boolean.TRUE;
		private Boolean wordAlignment = Boolean.TRUE;
		private Boolean fullText = Boolean.TRUE;
		private String script;
		//boosting object array
		private List<Boosting> boostings;
		//comma separated words
		private String forbiddens;
		private Diarization diarization;
		private KeywordExtraction keywordExtraction;
		private Boolean groupByAudio;

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getCompletion() {
			return completion;
		}

		public void setCompletion(String completion) {
			this.completion = completion;
		}

		public String getCallback() {
			return callback;
		}

		public Boolean getSttEnable() {
			return sttEnable;
		}

		public void setSttEnable(Boolean sttEnable) {
			this.sttEnable = sttEnable;
		}

		public Boolean getWordAlignment() {
			return wordAlignment;
		}

		public void setWordAlignment(Boolean wordAlignment) {
			this.wordAlignment = wordAlignment;
		}

		public Boolean getFullText() {
			return fullText;
		}

		public void setFullText(Boolean fullText) {
			this.fullText = fullText;
		}

		public String getScript() {
			return script;
		}

		public void setScript(String script) {
			this.script = script;
		}

		public void setCallback(String callback) {
			this.callback = callback;
		}

		public Map<String, Object> getUserdata() {
			return userdata;
		}

		public void setUserdata(Map<String, Object> userdata) {
			this.userdata = userdata;
		}

		public String getForbiddens() {
			return forbiddens;
		}

		public void setForbiddens(String forbiddens) {
			this.forbiddens = forbiddens;
		}

		public List<Boosting> getBoostings() {
			return boostings;
		}

		public void setBoostings(List<Boosting> boostings) {
			this.boostings = boostings;
		}

		public Diarization getDiarization() {
			return diarization;
		}

		public void setDiarization(Diarization diarization) {
			this.diarization = diarization;
		}

		public KeywordExtraction getKeywordExtraction() {
			return keywordExtraction;
		}

		public void setKeywordExtraction(KeywordExtraction keywordExtraction) {
			this.keywordExtraction = keywordExtraction;
		}

		public Boolean getGroupByAudio() {
			return groupByAudio;
		}

		public void setGroupByAudio(Boolean groupByAudio) {
			this.groupByAudio = groupByAudio;
		}
	}

	/**
	 * recognize media using URL
	 * @param url required, the media URL
	 * @param nestRequestEntity optional
	 * @return string
	 */
	public String url(String url, NestRequestEntity nestRequestEntity) {
		HttpPost httpPost = new HttpPost(INVOKE_URL + "/recognizer/url");
		httpPost.setHeaders(HEADERS);
		Map<String, Object> body = new HashMap<>();
		body.put("url", url);
		body.put("language", nestRequestEntity.getLanguage());
		body.put("completion", nestRequestEntity.getCompletion());
		body.put("callback", nestRequestEntity.getCallback());
		body.put("userdata", nestRequestEntity.getCallback());
		body.put("sttEnable", nestRequestEntity.getSttEnable());
		body.put("script", nestRequestEntity.getScript());
		body.put("wordAlignment", nestRequestEntity.getWordAlignment());
		body.put("fullText", nestRequestEntity.getFullText());
		body.put("forbiddens", nestRequestEntity.getForbiddens());
		body.put("boostings", nestRequestEntity.getBoostings());
		body.put("diarization", nestRequestEntity.getDiarization());
		body.put("keywordExtraction", nestRequestEntity.getKeywordExtraction());
		body.put("groupByAudio", nestRequestEntity.getGroupByAudio());
		HttpEntity httpEntity = new StringEntity(gson.toJson(body), ContentType.APPLICATION_JSON);
		httpPost.setEntity(httpEntity);
		return execute(httpPost);
	}

	/**
	 * recognize media using Object Storage
	 * @param dataKey required, the Object Storage key
	 * @param nestRequestEntity optional
	 * @return string
	 */
	public String objectStorage(String dataKey, NestRequestEntity nestRequestEntity) {
		HttpPost httpPost = new HttpPost(INVOKE_URL + "/recognizer/object-storage");
		httpPost.setHeaders(HEADERS);
		Map<String, Object> body = new HashMap<>();
		body.put("dataKey", dataKey);
		body.put("language", nestRequestEntity.getLanguage());
		body.put("completion", nestRequestEntity.getCompletion());
		body.put("callback", nestRequestEntity.getCallback());
		body.put("userdata", nestRequestEntity.getCallback());
		body.put("sttEnable", nestRequestEntity.getSttEnable());
		body.put("script", nestRequestEntity.getScript());
		body.put("wordAlignment", nestRequestEntity.getWordAlignment());
		body.put("fullText", nestRequestEntity.getFullText());
		body.put("forbiddens", nestRequestEntity.getForbiddens());
		body.put("boostings", nestRequestEntity.getBoostings());
		body.put("diarization", nestRequestEntity.getDiarization());
		body.put("keywordExtraction", nestRequestEntity.getKeywordExtraction());
		body.put("groupByAudio", nestRequestEntity.getGroupByAudio());
		StringEntity httpEntity = new StringEntity(gson.toJson(body), ContentType.APPLICATION_JSON);
		httpPost.setEntity(httpEntity);
		return execute(httpPost);
	}

	/**
	 *
	 * recognize media using a file
	 * @param file required, the media file
	 * @param nestRequestEntity optional
	 * @return string
	 */
	public String upload(File file, NestRequestEntity nestRequestEntity) {
		HttpPost httpPost = new HttpPost(INVOKE_URL + "/recognizer/upload");
		httpPost.setHeaders(HEADERS);
		HttpEntity httpEntity = MultipartEntityBuilder.create()
			.addTextBody("params", gson.toJson(nestRequestEntity), ContentType.APPLICATION_JSON)
			.addBinaryBody("media", file, ContentType.MULTIPART_FORM_DATA, file.getName())
			.build();
		httpPost.setEntity(httpEntity);
		return execute(httpPost);
	}

	private String execute(HttpPost httpPost) {
		try (final CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
			final HttpEntity entity = httpResponse.getEntity();
			return EntityUtils.toString(entity, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		final SttTest clovaSpeechClient = new SttTest();
		NestRequestEntity requestEntity = new NestRequestEntity();
		final String result =
			clovaSpeechClient.upload(new File("sample.wav"), requestEntity);
		//final String result = clovaSpeechClient.url("file URL", requestEntity);
		//final String result = clovaSpeechClient.objectStorage("Object Storage key", requestEntity);
		System.out.println("음성 인식 결과 : " + result.toString());
		
	}

}

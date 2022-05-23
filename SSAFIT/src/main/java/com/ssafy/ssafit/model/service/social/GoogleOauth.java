package com.ssafy.ssafit.model.service.social;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth{
	@Value("${sns.google.url}")
	private String GOOGLE_SNS_BASE_URL;
	@Value("${sns.google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;
    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;
    @Value("${sns.google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
    private final String GOOGLE_SNS_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";
	
	@Override
	public String getOauthRedirectURL() {
		Map<String, Object> params = new HashMap<>();
		params.put("scope", "email");
		params.put("response_type", "code");
		params.put("client_id", GOOGLE_SNS_CLIENT_ID);
		params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
		
		String parameterString =  params.entrySet().stream()
				.map(x -> x.getKey() + "=" + x.getValue())
				.collect(Collectors.joining("&"));
		
		return GOOGLE_SNS_BASE_URL + "?" + parameterString;
	}

	@Override
	public String requestAccessToken(String code) {
		RestTemplate restTemplate = new RestTemplate();
		
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		params.put("client_id", GOOGLE_SNS_CLIENT_ID);
		params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
		params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
		params.put("grant_type", "authorization_code");
		
		ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	// 토큰 받아온걸 sha256적용해서 payload에 있는 email을 읽어오자..
        	try {
				String email = this.getEmail(responseEntity);
				return email;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return "구글 로그인 요청 처리 실패";
	}
	
	@Override
	public String getEmail(ResponseEntity<?> responseEntity) throws Exception {
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParse.parse((String) responseEntity.getBody());
		String idToken = (String) jsonObject.get("id_token");
		String payload = idToken.split("\\.")[1];
//		System.out.println(idToken);
		Base64.Decoder decoder = Base64.getDecoder();
//		System.out.println(new String(decoder.decode(payload), StandardCharsets.UTF_8));
		String strPayload = new String(decoder.decode(payload), StandardCharsets.UTF_8);
		jsonObject = (JSONObject) jsonParse.parse(strPayload);
		String email = (String) jsonObject.get("email");
		System.out.println(email);
		return email;
	}
}

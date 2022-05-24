package com.ssafy.ssafit.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ssafy.ssafit.model.dto.DType;
import com.ssafy.ssafit.model.dto.NotifyDTO;
import com.ssafy.ssafit.model.service.NotifyService;

import lombok.RequiredArgsConstructor;

@Component("webSocketHandler")
@RequiredArgsConstructor
public class SocketTextHandler extends TextWebSocketHandler {
	private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
	private final String NOTICE = "새로운 공지사항이 올라왔습니다";
	private final String SELF = "님 환영합니다!";
	private final String TAG = "{}님이 회원님을 태그했습니다";
	private final String REVIEW = "{}님이 회원님의 게시글에 댓글을 달았습니다.";	
	private final String LIKE = "{}님이 회원님의 게시글을 좋아합니다.";	
	private final Map<String, WebSocketSession> userSessionMap = new HashMap<>();

	private static final Logger log = LoggerFactory.getLogger(SocketTextHandler.class);

	private final NotifyService notifyService;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Socket {} 연결", session);
		sessions.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info(message.getPayload());
		JSONObject jObject =new JSONObject(message.getPayload());
		
		String type = jObject.getString("type");
		String fromUsername = jObject.getString("from-username");
		String toUsername = jObject.getString("to-username");
		int to = jObject.getInt("to");
		int id = jObject.getInt("id");
		String msg = "";
		
		if(!type.equals("self")) {
			notifyService.notice(
					NotifyDTO.builder()
					.userId(to)
					.objectId(id)
					.dType(DType.fromString(type))
					.build());
		}
		
		WebSocketSession toSession = userSessionMap.get(toUsername);
		TextMessage textMsg = null;
		
		JSONObject object = new JSONObject();
		
		object.put("type",type);
		object.put("from",fromUsername);
		object.put("to",toUsername);
		
		if(type.equals("self")) {
			log.info(fromUsername,session);
			userSessionMap.put(fromUsername,session);
			msg = fromUsername+SELF;
			object.put("msg",msg);
			session.sendMessage(new TextMessage(object.toString()));
		}
		else if(type.equals("notice")) {
			object.put("msg",NOTICE);
			sessions.stream().forEach(s->{
				try {
					s.sendMessage(new TextMessage(object.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else if(toSession != null) {
			if(type.equals("like")) {
				msg = LIKE;
			}
			else if(type.equals("review")) {
				msg = REVIEW;
			}
			else if(type.equals("tag")) {
				msg = TAG;
			}
			object.put("msg",msg);
			toSession.sendMessage(new TextMessage(object.toString()));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("Socket {} 종료",session);
		sessions.remove(session);
		
	}

}

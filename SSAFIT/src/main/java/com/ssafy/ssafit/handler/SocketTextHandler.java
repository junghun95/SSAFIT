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
import com.ssafy.ssafit.model.service.UserService;

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
	private final String DM = "{}님이 회원님에게 DM을 보냈습니다.";
	private final String REPORT = "신고가 접수되었습니다.";
	private final Map<String, WebSocketSession> userSessionMap = new HashMap<>();
	private final UserService userService;

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
		
		if(!type.equalsIgnoreCase("self")) {
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
		
		if(type.equalsIgnoreCase("self")) {
			log.info(fromUsername,session);
			userSessionMap.put(fromUsername,session);
			msg = fromUsername+SELF;
			object.put("msg",msg);
			
			// 팔로우 목록을 볼때 로그인된 사용자들은 표시를 하기 위해 필요한 기능
			// 세션에 로그인 일때 내가 팔로우한 사람들 목록 불러와서 그중 로그인되어있는 사람만
			// -> session에 저장되어있는 사람만 알림으로 보내기
			// (형식 JSON {username : 'usernae'}으로 보내기)
			if(toSession != null) {
				// 여기서 로그인된사람들 리스트 뽑아오기 -> toSession에 있는 key리스트를 받아오면 그게 로그인한 유저리스트
				Set<String> loginUserList = userSessionMap.keySet();
				// 그 리스트 중에 fromUsername이 팔로우한 사람들 목록을 꺼내온다
				JSONObject followLoginUsers = new JSONObject();
				userService.getFollows(fromUsername).forEach(userDTO -> {
					if(loginUserList.contains(userDTO.getUsername()) ) {
						// 여기까지 오면 로그인한 사람중 내가 팔로우한 사람들 한명씩이니까 json형태로 담아주면 된다 -> 이걸 생각해보자
						followLoginUsers.put("username", userDTO.getUsername());
					}
				});
				object.put("followLoginList", followLoginUsers);
			}
			session.sendMessage(new TextMessage(object.toString()));
			
		}
		else if(type.equalsIgnoreCase("notice")) {
			object.put("msg",NOTICE);
			sessions.stream().forEach(s->{
				try {
					s.sendMessage(new TextMessage(object.toString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		else if(type.equalsIgnoreCase("report")) {
			msg = REPORT;
			object.put("msg",msg);
			session.sendMessage(new TextMessage(object.toString()));
		}
		else if(toSession != null) {
			if(type.equalsIgnoreCase("like")) {
				msg = LIKE.replace("{}", fromUsername);
			}
			else if(type.equalsIgnoreCase("review")) {
				msg = REVIEW.replace("{}", fromUsername);
			}
			else if(type.equalsIgnoreCase("tag")) {
				msg = TAG.replace("{}", fromUsername);
			}
			else if(type.equalsIgnoreCase("dm")) {
				msg = DM.replace("{}", fromUsername);
			}
			object.put("msg",msg);
//			session.sendMessage(new TextMessage(object.toString()));
			toSession.sendMessage(new TextMessage(object.toString()));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("Socket {} 종료",session);
		sessions.remove(session);
		
	}

}

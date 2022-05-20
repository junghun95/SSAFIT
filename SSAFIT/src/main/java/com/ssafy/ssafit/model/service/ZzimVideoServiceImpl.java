package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dao.ZzimVideoDao;
import com.ssafy.ssafit.model.dto.VideoDTO;
import com.ssafy.ssafit.model.dto.ZzimVideoDTO;
import com.ssafy.ssafit.model.dto.request.VideoRequestDTO;
import com.ssafy.ssafit.model.dto.response.VideoResponseDTO;

import lombok.RequiredArgsConstructor;

@Service("zzimService")
@RequiredArgsConstructor
public class ZzimVideoServiceImpl implements ZzimVideoService{

	private final ZzimVideoDao zzimVideoDao;
	private final VideoDao videoDao;
	
	@Override
	public void createZzim(VideoRequestDTO videoRequestDTO) {
		// video 에 추가
		// video에 먼저 추가해주어야 쿼리문을 날릴때 참조 무결성 에러가 안난다..!!
		VideoDTO videoDTO = VideoDTO.builder()
									.id(videoRequestDTO.getVideoId())
									.title(videoRequestDTO.getTitle())
									.channelName(videoRequestDTO.getChannelName())
									.url(videoRequestDTO.getUrl())
									.build();
		videoDao.insert(videoDTO);

		// like_video 에 추가
		ZzimVideoDTO zzimVideoDTO = ZzimVideoDTO.builder()
				.videoId(videoRequestDTO.getVideoId())
				.userId(videoRequestDTO.getUserId())
				.partId(videoRequestDTO.getPartId())
				.build();
		zzimVideoDao.insert(zzimVideoDTO);
	}

	@Override
	public void remove(int id) {
		zzimVideoDao.delete(id);
	}

	@Override
	public List<VideoResponseDTO> getZzimList(int userId) {
		return zzimVideoDao.selectAll(userId);
	}


}

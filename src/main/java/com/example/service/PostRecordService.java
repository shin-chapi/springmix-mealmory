package com.example.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.form.PostForm;
import com.example.model.CalendarRecord;
import com.example.model.Post;
import com.example.repository.PostMapper;

@Service
public class PostRecordService {

	private final PostMapper PostMapper;

	public PostRecordService(PostMapper PostMapper) {
		this.PostMapper = PostMapper;
	}

	@Transactional(readOnly = true)
	public List<CalendarRecord> findAllCalendarRecords(String userName) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

		//ユーザーIDで投稿を検索
		List<Post> diaryRecords = PostMapper.findAllDiaryRecords(userName);
		List<CalendarRecord> calendarRecords = new ArrayList<CalendarRecord>();
        //検索した投稿をカレンダーに表示のためデータをcalendarRecordsにデータ入れ替え
		for (int i = 0; i < diaryRecords.size(); i++) {
			Post diary = diaryRecords.get(i);
			CalendarRecord calendar = new CalendarRecord();
			calendar.setStart(simpleDate.format(diary.getDiaryDay()));
			calendar.setEnd(simpleDate.format(diary.getDiaryDay()));

			switch (diary.getCategoryId()) {
			case 1:
				calendar.setUrl("index/record/" + calendar.getStart() + "/1");
				calendar.setTitle("朝食" + diary.getRecord2());
				break;
			case 2:
				calendar.setUrl("index/record/" + calendar.getStart() + "/2");
				calendar.setTitle("昼食" + diary.getRecord2());
				break;
			case 3:
				calendar.setUrl("index/record/" + calendar.getStart() + "/3");
				calendar.setTitle("夕食" + diary.getRecord2());
				break;

			}
			calendarRecords.add(calendar);
		}
		return calendarRecords;
	}


	@Transactional(readOnly = false)
	public void insertDiaryRecord(PostForm form) {
		Post diary = new Post(form.getUserName(), form.getCategoryId(), form.getDiaryDay(), form.getRecord1(),
				form.getRecord2(), form.getRecord3(), form.getImageName(), form.getMemo(), form.getCreateAt(),
				form.getCreateAt());
		PostMapper.insertDiaryRecord(diary);
	}

	@Transactional(readOnly = true)
	public PostForm findOneDiaryRecord(String userName, int categoryId, Date diaryDay) {
		Post diary = PostMapper.findOneDiaryRecord(userName, categoryId, diaryDay);
		if (Objects.isNull(diary)) {
			return null;
		}
		PostForm form = new PostForm(diary.getUserName(), diary.getCategoryId(), diary.getDiaryDay(),
				diary.getRecord1(), diary.getRecord2(), diary.getRecord3(), diary.getImageName(), diary.getMemo(),
				diary.getCreateAt());
		return form;

	}

	@Transactional(readOnly = false)
	public void updateDiaryRecord(PostForm form) {
		Post diary = new Post(form.getUserName(), form.getCategoryId(), form.getDiaryDay(), form.getRecord1(),
				form.getRecord2(), form.getRecord3(), form.getImageName(), form.getMemo(), form.getCreateAt(),
				LocalDateTime.now());
		PostMapper.updateDiaryRecord(diary);
	}

	@Transactional(readOnly = false)
	public void deleteDiaryRecord(PostForm form) {
		Post diary = new Post();
		diary.setUserName(form.getUserName());
		diary.setCategoryId(form.getCategoryId());
		diary.setDiaryDay(form.getDiaryDay());
		PostMapper.deleteDiaryRecord(diary);
	}
}

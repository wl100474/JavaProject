package com.wl1004.project.volunteer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wl1004.project.comment.Comment;
import com.wl1004.project.member.Member;

public interface VolunteerMapper {
	public abstract int regVol(Volunteer v);
	public abstract Volunteer getDetail(int v_no);
	public abstract int count();
	public abstract int countSearch(@Param("type") String search);
	public abstract List<Volunteer> getAllList(@Param("search") String search, @Param("start") int start,
			@Param("end") int end);
	public abstract int update(Volunteer v);
	public abstract int delete(int v_no);

	// 봉사 신청 명단 등록
	public abstract int apply(@Param("v_no") int v_no, @Param("m_id") String m_id);

	// 봉사 신청 자원봉사 테이블 인원 카운트
	public abstract int countP(int v_no);

	// 명단에서 데이터 가져오기
	public abstract StaffList getP(@Param("v_no") int v_no, @Param("m_id") String m_id);

	// 봉사 신청 취소
	public abstract int cancelV1(@Param("v_no") int v_no, @Param("v_id") String v_id);
	public abstract int cancelV2(@Param("v_no") int v_no);

	// 봉사 상태 업데이트
	public abstract int updateStatus0();
	public abstract int updateStatus1();
	public abstract int updateStatus2(@Param("today") String today);

	// 로그인한 회원의 봉사 리스트
	public abstract int countUserBoards1(@Param("m_id") String m_id);
	public abstract int countUserBoards2(@Param("m_id") String m_id);
	public abstract List<Volunteer> getMyList(@Param("m_id") String m_id, @Param("start") int start,
			@Param("end") int end);
	public abstract List<Volunteer> getMyList2(@Param("m_id") String m_id, @Param("start") int start,
			@Param("end") int end);
	public abstract List<Volunteer> getMyList_5(@Param("m_id") String m_id);
	public abstract List<Volunteer> getMyList2_5(@Param("m_id") String m_id);
	
	public abstract List<Map<String, Object>> getCount();
}

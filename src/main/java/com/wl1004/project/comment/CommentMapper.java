package com.wl1004.project.comment;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
	// 댓글
	public abstract List<HashMap<String, Object>> getCmt(@Param("c_no") int c_no);

	public abstract int setAnsnum(@Param("v_no") int v_no, @Param("c_ansnum") int c_ansnum);

	public abstract int regCmt(Comment cmt);

	public abstract List<Comment> getAllCmt(@Param("v_no") int v_no, @Param("start") int start, @Param("end") int end);

	public abstract int countCmt(@Param("v_no") int v_no);

	public abstract int deleteCmt(@Param("v_no") int v_no, @Param("c_no") int c_no);

	public abstract int updateCmt(@Param("c_content") String c_content, @Param("v_no") int v_no,
			@Param("c_no") int c_no);
}

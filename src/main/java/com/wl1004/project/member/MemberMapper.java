package com.wl1004.project.member;

import java.util.List;
import java.util.Map;

public interface MemberMapper {
	// method 리턴타입
	// insert / update / delete -> 영향을 받은 데이터의 수 -int(리턴타입)
	// select
	// 결과가 하나 나옴 : resultType객체(Student)
	// 결과가 여러 개 나옴 : List<resultType객체> (List<Student>)

	// 메소드명 - mapper.xml의 id와 맞추기
	// 파라미터 - mapper.xml의 parameterType과 맞추기
	public abstract int checkID(String m_id);
	public abstract int checkEmail(String m_email);
	public abstract int regMember(Member m);
	public abstract Member login(Member m);
	public abstract int updateInfo(Member m);
	public abstract int delMember(Member m);
	public abstract Member searchPwd(Member m);
	public abstract int updatePwd(Member m);
}

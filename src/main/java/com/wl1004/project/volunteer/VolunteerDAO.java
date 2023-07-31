package com.wl1004.project.volunteer;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl1004.project.comment.Comment;
import com.wl1004.project.member.Member;

@Service
public class VolunteerDAO {

	@Autowired
	private SqlSession ss;

	private int allBoardCount;
	private int userBoardCount1;
	private int userBoardCount2;

	// 봉사 등록
	public void regVol(Volunteer v, HttpServletRequest req) {
		try {
			// 작성자 아이디 - 세션에서 받아오기
			Member m = (Member) req.getSession().getAttribute("loginMember");
			v.setV_writer(m.getM_id());

			// 줄바꿈 처리
			v.getV_content().replace("\r\n", "<br>");

			VolunteerMapper vm = ss.getMapper(VolunteerMapper.class);

			if (vm.regVol(v) == 1) {
				System.out.println("봉사 등록 성공");
				allBoardCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("봉사 등록 실패");
		}
	}

	// 봉사 상세보기
	public void getDetail(int v_no, HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");

			req.setAttribute("detail", ss.getMapper(VolunteerMapper.class).getDetail(v_no));

			StaffList list = ss.getMapper(VolunteerMapper.class).getP(v_no, m.getM_id());
			req.setAttribute("list", list);

			System.out.println("봉사 리스트 상세보기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("봉사 리스트 상세보기 실패");
		}
	}

	// 전체 글 갯수 가져오기
	public void countBoards() {
		try {
			allBoardCount = ss.getMapper(VolunteerMapper.class).count();
			// System.out.println(allBoardCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시판에 처음 접근 or 타입선택 X
	public void clearSearch(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}

	// 검색어 값 가져오기
	public void searchMsg(HttpServletRequest req) {
		// 새로운 요청이 일어났을 때(페이지 전환)에도 검색어는 살아있어야 해서 세션 사용
		String search = req.getParameter("search");
		req.getSession().setAttribute("search", search);
		// 검색(요청) -> 25개 -> 1페이지 10개씩 보여주기
		// 검색한 것의 2페이지로(요청)
		// 검색한 것의 3페이지로(요청)

	}

	// 검색어 (글 내용에 포함되는 게시글만)
	private int countSearchMsg(String search) { 
		try {

			return ss.getMapper(VolunteerMapper.class).countSearch(search);

		} catch (Exception e) {
			e.printStackTrace();
			return 0; // 해당하는 내용이 없으면 0 리턴
		}
	}

	// 전체 봉사 리스트 페이징
	public void getAllList(int pageNo, HttpServletRequest req) {
		try {
			int boardCount = allBoardCount; // 게시글 전체 갯수
			String search = (String) req.getSession().getAttribute("search"); // 검색어
			if (search == null || search.equals("전체")) { // 검색어가 없으면 전체 조회
				search = "";
			} else { // 검색어가 있으면
				boardCount = countSearchMsg(search);
			}
			System.out.println("봉사 타입: " + search);
			System.out.println("전체 게시글 : " + allBoardCount);
			System.out.println("게시글 갯수 : " + boardCount);

			int boardPerPage = 10; // 한 페이지당 보여줄 게시글 데이터 수

			// 페이지 갯수 가져오기
			int pageCount = (int) (Math.ceil(boardCount / (double) boardPerPage));
			req.setAttribute("pageCount", pageCount);
			// System.out.println(pageCount);

			int start = (boardPerPage * (pageNo - 1)) + 1; // 한 페이지에 담을 게시물 첫 번째 번호 값
			int end = (pageNo == pageCount) ? allBoardCount : (start + boardPerPage - 1); // 한 페이지에 담을 끝 게시물 번호 값

//			req.setAttribute("List", ss.getMapper(VolunteerMapper.class).getAllList(start, end));
			req.setAttribute("List", ss.getMapper(VolunteerMapper.class).getAllList(search, start, end));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수정
	public void update(Volunteer v, HttpServletRequest req) {
		try {
			// 줄바꿈 처리
			v.getV_content().replace("\r\n", "<br>");

			VolunteerMapper vm = ss.getMapper(VolunteerMapper.class);

			if (vm.update(v) == 1) {
				System.out.println("봉사 수정 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("봉사 수정 실패");
		}
	}

	// 삭제
	public void delete(int v_no, HttpServletRequest req) {
		try {
			VolunteerMapper vm = ss.getMapper(VolunteerMapper.class);

			if (vm.delete(v_no) == 1) {
				System.out.println("봉사 삭제 성공");
				allBoardCount--;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("봉사 삭제 실패");
		}
	}

	// 신청
	public void apply(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			int v_no = Integer.parseInt(req.getParameter("v_no"));
			VolunteerMapper vm = ss.getMapper(VolunteerMapper.class);

			// 모집 인원과 현재 인원 비교
			Volunteer v = vm.getDetail(v_no);
			if (v.getV_staff() != v.getV_recruit()) {
				if (vm.apply(v_no, m.getM_id()) == 1 && vm.countP(v_no) == 1) {
					System.out.println("봉사 신청 성공");
				}
			} else {
				req.setAttribute("error", "※ 잘못된 접근입니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("봉사 신청 실패");
		}
	}

	// 신청 취소
	public void cancel(int v_no, String v_id) {
		try {
			VolunteerMapper vm = ss.getMapper(VolunteerMapper.class);

			if (vm.cancelV1(v_no, v_id) == 1 && vm.cancelV2(v_no) == 1) {
				System.out.println("신청 취소 성공");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("신청 취소 실패");
		}
	}

	// 봉사 상태 업데이트
	public void updateStatus() {
		try {
			VolunteerMapper vm = ss.getMapper(VolunteerMapper.class);

			vm.updateStatus0();
			vm.updateStatus1();

			// yyyy-MM-dd 포맷 설정
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String today = dateFormat.format(new Date());
			System.out.println("오늘 날짜 : " + today);

			vm.updateStatus2(today);
			System.out.println("봉사 상태 확인 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("봉사 상태 확인 실패");
		}
	}

	// 유저가 작성한 전체 글 갯수 가져오기
	public void countUserBoards1(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			userBoardCount1 = ss.getMapper(VolunteerMapper.class).countUserBoards1(m.getM_id());
			System.out.println("유저가 작성한 전체 글 갯수" + userBoardCount1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 유저가 참여한 전체 글 갯수 가져오기
	public void countUserBoards2(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			userBoardCount2 = ss.getMapper(VolunteerMapper.class).countUserBoards2(m.getM_id());
			System.out.println("유저가 참여한 전체 글 갯수" + userBoardCount2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인한 유저의 봉사 리스트만
	public void getMyList(int pageNo, HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");

			int boardCount = userBoardCount1; // 유저가 생성한 봉사 갯수
			int boardPerPage = 5; // 한 페이지당 보여줄 게시글 데이터 수

			// 페이지 갯수 가져오기
			int pageCount = (int) (Math.ceil(boardCount / (double) boardPerPage));
			req.setAttribute("pageCount", pageCount);
			// System.out.println(pageCount);

			int start = (boardPerPage * (pageNo - 1)) + 1; // 한 페이지에 담을 게시물 첫 번째 번호 값
			int end = (pageNo == pageCount) ? userBoardCount1 : (start + boardPerPage - 1); // 한 페이지에 담을 끝 게시물 번호 값

			req.setAttribute("myList", ss.getMapper(VolunteerMapper.class).getMyList(m.getM_id(), start, end));
			System.out.println("생성한 봉사 리스트 가져오기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("생성한 봉사 리스트 가져오기 실패");
		}
	}

	// 로그인한 유저의 봉사 리스트만
	public void getMyList2(int pageNo, HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");

			int boardCount = userBoardCount2; // 유저가 생성한 봉사 갯수
			int boardPerPage = 5; // 한 페이지당 보여줄 게시글 데이터 수

			// 페이지 갯수 가져오기
			int pageCount = (int) (Math.ceil(boardCount / (double) boardPerPage));
			req.setAttribute("pageCount", pageCount);
			// System.out.println(pageCount);

			int start = (boardPerPage * (pageNo - 1)) + 1; // 한 페이지에 담을 게시물 첫 번째 번호 값
			int end = (pageNo == pageCount) ? userBoardCount2 : (start + boardPerPage - 1); // 한 페이지에 담을 끝 게시물 번호 값

			req.setAttribute("myList2", ss.getMapper(VolunteerMapper.class).getMyList2(m.getM_id(), start, end));
			System.out.println("참여한 봉사 리스트 가져오기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("참여한 봉사 리스트 가져오기 실패");
		}
	}

	// 로그인한 유저의 봉사 리스트 5개만
	public void getMyList_5(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");

			req.setAttribute("myList", ss.getMapper(VolunteerMapper.class).getMyList_5(m.getM_id()));
			System.out.println("생성한 봉사 리스트 가져오기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("생성한 봉사 리스트 가져오기 실패");
		}
	}

	// 로그인한 유저의 봉사 리스트 5개만
	public void getMyList2_5(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");

			req.setAttribute("myList2", ss.getMapper(VolunteerMapper.class).getMyList2_5(m.getM_id()));
			System.out.println("참여한 봉사 리스트 가져오기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("참여한 봉사 리스트 가져오기 실패");
		}
	}
	
	public List<Map<String, Object>> getJSON(HttpServletRequest req) {
		//System.out.println(ss.getMapper(VolunteerMapper.class).getCount().toString());
		List<Map<String, Object>> result = ss.getMapper(VolunteerMapper.class).getCount();
		return result;
	}
}

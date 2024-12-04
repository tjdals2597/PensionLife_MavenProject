package reservation;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class web_main {
	
	PrintWriter pw = null;
	
	@Resource(name = "pension_module")
	private pension_dao pendao;
	
	@Resource(name = "reservation_module")
	private reservation_dao reserdao;
	
	@GetMapping("/")
	public String webpage() {
		return "index";
	}
	
	@GetMapping("/main")
	public String mainpage(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata, Model m) {
		m.addAttribute("pensionlist", this.pendao.mainpage_select());
		if (sessiondata != null) {
			m.addAttribute("userdata", sessiondata);
		}
		return "m_index";
	}
	
	@PostMapping("/reserpage_go.do")
	public String reserpage_go(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata, Model m,
			@RequestParam(defaultValue = "", required = false) String resername, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/main';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("pension_name", resername);
			m.addAttribute("rnlist", this.pendao.rinfo_selelct(resername));
		}
		return "m_reservation";
	}
	
	@PostMapping("/pension_reservation.do")
	public void pension_reservation(@RequestParam(defaultValue = "", required = false) String rname, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		List<List<Object>> result = this.pendao.ajax_select(rname);
		this.pw = res.getWriter();
		int n = 0, w = 0;
		this.pw.print("var sdata = new Array();");
		do {
			this.pw.print("var data_dump = new Array();");
			w = 0;
			do {
				this.pw.write("data_dump.push('" + result.get(n).get(w) + "');");
				w++;
			} while (w < result.get(0).size());
			this.pw.write("sdata.push(data_dump);");
			n++;
		} while(n < result.size());
		this.pw.close();
	}
	
	@PostMapping("/reser_insert.do")
	public void reser_insert(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata,
			@ModelAttribute reservation_dto dto, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		String useridx = sessiondata.get(0).toString();
		dto.setMember_idx(Integer.parseInt(useridx));
		int result = this.reserdao.reser_insert(dto);
		if (result > 0) {
			this.pw.write("<script>alert('펜션 예약에 성공하였습니다.'); location.href='./reservation_check.do?check=" + useridx + "';</script>");
		}
		else {
			this.pw.write("<script>alert('예약이 처리되지 않았습니다.'); history.go(-1);</script>");
		}
		this.pw.close();
	}
	
	@GetMapping("/reservation_list.do")
	public String reservation_list(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/main';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("reserlist", this.reserdao.reservation_list(sessiondata.get(0).toString()));
		}
		return "m_reservation_list";
	}
	
	@GetMapping("/reservation_check.do")
	public String reservation_check(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m, @RequestParam(required = false, value = "") String check,
			@RequestParam(required = false) Integer number) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/main';</script>");
			this.pw.close();
		}
		else {
			String useridx = sessiondata.get(0).toString();
			m.addAttribute("userdata", sessiondata);
			if (check != null && check.equals(useridx)) {
				m.addAttribute("reserdata", this.reserdao.reservation_check(check));
			}
			else if (check == null && number == null) {
				reservation_dto result = this.reserdao.reservation_check(useridx);
				if (result == null) {
					this.pw = res.getWriter();
					this.pw.print("<script>alert('예약 내역이 없습니다.'); location.href = '/main';</script>");
					this.pw.close();
				}
				m.addAttribute("reserdata", result);
			}
			else {
				reservation_dto dto = this.reserdao.reservation_check(number);
				if (!useridx.equals(String.valueOf(dto.getMember_idx()))) {
					this.pw = res.getWriter();
					this.pw.print("<script>alert('잘못된 접근입니다.'); location.href = '/main';</script>");
					this.pw.close();
				}
				m.addAttribute("reserdata", dto);
			}
		}
		return "m_reservation_check";
	}
	
	@PostMapping("/reser_delete.do")
	public void reservation_delete(HttpServletResponse res, String reser_idx) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		int result = this.reserdao.reser_delete(reser_idx);
		if (result > 0) {
			this.pw.write("<script>alert('성공적으로 예약을 취소하셨습니다.'); location.href='/main';</script>");
		}
		else {
			this.pw.write("<script>alert('예약 취소에 오류가 발생하였습니다.'); history.go(-1);</script>");
		}
		this.pw.close();
	}
}

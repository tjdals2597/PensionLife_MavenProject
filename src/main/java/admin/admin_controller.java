package admin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import member.password_encoder;
import member.qaboard_dao;
import member.qaboard_dto;

@Controller
public class admin_controller {

	PrintWriter pw = null;
	
	@Resource(name = "admin_module")
	private admin_dao admdao;
	
	@Resource(name = "qa_module")
	private qaboard_dao qadao;
	
	@GetMapping("/admin")
	public String main_page(@SessionAttribute(name = "admin", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata != null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('이미 로그인하셨습니다.'); location.href = '/admin_qalist.do';</script>");
			this.pw.close();
		}
		return "admin/admin_login";
	}
	
	@PostMapping("/admin_login.do")
	public void login(@ModelAttribute admin_dto dto, HttpServletResponse res, HttpServletRequest req) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		dto.setApass(new password_encoder().encodePass(dto.getApass()));
		int result = this.admdao.admin_login(dto, req);
		if (result > 0) {
			this.pw.write("<script>alert('로그인 되었습니다.'); location.href='./admin_qalist.do';</script>");
		}
		else {
			this.pw.write("<script>alert('아이디나 비밀번호를 확인하세요.'); history.go(-1);</script>");
		}
		this.pw.close();
	}
	
	@GetMapping("/admin_qalist.do")
	public String admin_qalist(@SessionAttribute(name = "admin", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("qalist", this.admdao.getQaList("1"));
		}
		return "admin/admin_qalist";
	}
	
	@GetMapping("/admin_qaflist.do")
	public String admin_qaflist(@SessionAttribute(name = "admin", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("qalist", this.admdao.getQaList("2"));
		}
		return "admin/admin_qaflist";
	}
	
	@GetMapping("/admin_logout.do")
	public void logout(HttpSession htss, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		htss.removeAttribute("admin");
		this.pw.write("<script>alert('로그아웃 되었습니다.'); location.href='/admin';</script>");
		this.pw.close();
	}
	
	@PostMapping("/admin_qawrite.do")
	public String admin_qawrite(@SessionAttribute(name = "admin", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m, @RequestParam String qa_idx_value) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("qadata", this.qadao.getQaView(qa_idx_value));
		}
		return "admin/admin_qawrite";
	}
	
	@PostMapping("/answer_insert.do")
	public void answer_insert(HttpServletResponse res, @ModelAttribute qaboard_dto dto) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		int result = this.admdao.addAnswerQa(dto);
		if (result > 0) {
			this.pw.write("<script>alert('답변이 정상 등록되었습니다.'); location.href = './admin_qalist.do';</script>");
		}
		else {
			this.pw.write("<script>alert('답변 등록이 정상 처리되지 않았습니다.'); history.go(-1);</script>");
		}
		this.pw.close();
	}
}

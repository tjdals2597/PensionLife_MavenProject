package member;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class member_controller {
	
	PrintWriter pw = null;
	
	@Resource(name = "member_module")
	private member_dao memdao;
	
	@Resource(name = "qa_module")
	private qaboard_dao qadao;
	
	@PostMapping("/id_search.do")
	public void id_search(@ModelAttribute member_dto dto, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		String searchid = this.memdao.searchID(dto);
		this.pw = res.getWriter();
		if (searchid == null) {
			this.pw.print("error");
		}
		else {
			this.pw.print(searchid);
		}
		this.pw.close();
	}
	
	@PostMapping("/change_pass.do")
	public String change_pass(@ModelAttribute member_dto dto, HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		member_dto mdto = this.memdao.searchPW(dto);
		if (mdto != null) {
			m.addAttribute("search_midx", mdto.getMidx());
		}
		else {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('일치하는 회원 정보가 존재하지 않습니다.');history.go(-1);</script>");
			this.pw.close();
		}
		return "change_pass";
	}
	
	@PostMapping("/change_passok.do")
	public void change_passok(@ModelAttribute member_dto dto, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		dto.setMpass(new password_encoder().encodePass(dto.getMpass()));
		int callback = this.memdao.changePW(dto);
		this.pw = res.getWriter();
		if (callback > 0) {
			this.pw.print("<script>alert('비밀번호가 변경되었습니다. 변경된 비밀번호로 로그인해주세요.'); location.href = '/main';</script>");
		}
		else {
			this.pw.print("<script>alert('데이터 오류가 발생하여 변경되지 않았습니다.'); history.go(-1);</script>");
		}
		this.pw.close();
	}
	
	@PostMapping("/member_signup.do")
	public void member_signup(@ModelAttribute("sign") member_dto dto, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		dto.setMpass(new password_encoder().encodePass(dto.getMpass()));
		int callback = this.memdao.sign_member(dto);
		if(callback > 0) {
			this.pw.print("<script>alert('성공적으로 회원가입 하셨습니다.');location.href='/main';</script>");
		}
		else {
			this.pw.print("<script>alert('회원 가입이 정상적으로 진행되지 않았습니다.');history.go(-1);</script>");
		}
		this.pw.close();
	}
	
	@PostMapping("/login.do")
	public void login(@RequestParam(defaultValue = "N") String auto_login, @ModelAttribute member_dto dto,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		dto.setMpass(new password_encoder().encodePass(dto.getMpass()));
		member_dto mdto = this.memdao.login_member(auto_login, dto, req);
		this.pw = res.getWriter();
		if (mdto != null) {
			if(auto_login.equals("Y")) {		
				this.pw.write("<script>localStorage.setItem('mid','" + mdto.getMid() + "');"
						+ "localStorage.setItem('mpass','" + mdto.getMpass() + "/auto');"
						+ "localStorage.setItem('autologin','" + auto_login + "');"
						+ "alert('로그인에 성공하였습니다.'); location.href='/';</script>");
			}
			else {
				this.pw.write("<script>alert('로그인에 성공하였습니다.');location.href='/main';</script>");
			}
		}
		else {
			this.pw.write("<script>alert('아이디와 비밀번호를 확인해주세요.');history.go(-1);</script>");	
		}
		this.pw.close();
	}
	
	@GetMapping("/logout.do")
	public void logout(HttpSession htss, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		htss.removeAttribute("user");
		this.pw.write("<script>alert('로그아웃 되었습니다.');window.location.href='/main';</script>");
		this.pw.close();
	}
	
	@GetMapping("/join.do")
	public String join() {
		return "m_join";
	}
	
	@PostMapping("/m_join.do")
	public String m_join(@ModelAttribute("agree") member_dto dto, Model m) {
		if (dto.getConsent_marketing() == null) {
			dto.setConsent_marketing("N");
		}
		m.addAttribute("agree_model", dto);
		return "m_join2";
	}
	
	@PostMapping("/duplicate_check.do")
	public void duplicate_check(@RequestParam(defaultValue = "", required = false) String mid,
			HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		int callback = this.memdao.id_duplicate(mid);
		if(callback > 0) {
			pw.write("사용중인 아이디입니다.");
		}
		else {
			pw.write("사용 가능한 아이디입니다.");
		}
		this.pw.close();
	}
	
	@GetMapping("/m_idsearch.do")
	public String m_idsearch() {
		return "m_idsearch";
	}
	
	@GetMapping("/qa_list.do")
	public String qa_list(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/main';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("qalist", this.qadao.getQaList(sessiondata.get(0).toString()));
		}
		return "m_qalist";
	}
	
	@GetMapping("/qa_write.do")
	public String qa_write(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/main';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
		}
		return "m_qawrite";
	}
	
	@PostMapping("/qawrite_insert.do")
	public void qawrite_insert(@ModelAttribute qaboard_dto dto, HttpServletResponse res, HttpServletRequest req,
			@RequestPart(name = "qfiles") MultipartFile qfile[]) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		String file_url = req.getServletContext().getRealPath("/qafile/");
		int result = this.qadao.insert(dto, qfile, file_url);
		if (result > 0) {
			this.pw.write("<script>alert('문의등록이 정상적으로 완료되었습니다.'); location.href='./qa_list.do';</script>");	
		} else {
			this.pw.write("<script>alert('문의등록이 실패하였습니다.');history.go(-1);</script>");	
		}
		this.pw.close();
	}
	
	@PostMapping("/qa_view.do")
	public String qa_view(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m, @RequestParam(name = "qidx") String qidx) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/main';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("userQaData", this.qadao.getQaView(qidx));
		}
		return "m_qaview";
	}
	
	@PostMapping("/qa_modify.do")
	public String qa_modify(@SessionAttribute(name = "user", required = false) ArrayList<Object> sessiondata,
			HttpServletResponse res, Model m, @RequestParam(name = "qidx") String qidx) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (sessiondata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('로그인이 필요한 서비스입니다.'); location.href = '/main';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userdata", sessiondata);
			m.addAttribute("userQaData", this.qadao.getQaView(qidx));
		}
		return "m_qamodify";
	}
	
	@PostMapping("/qa_update.do")
	public void qa_update(@ModelAttribute qaboard_dto dto, HttpServletResponse res, HttpServletRequest req,
			@RequestPart(required = false, name = "qfiles") MultipartFile qfile[],
			@RequestParam(required = false) String exfile1_del_ck,
			@RequestParam(required = false) String exfile2_del_ck) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		String file_url = req.getServletContext().getRealPath("/qafile/");
		int result = this.qadao.update(dto, qfile, file_url, exfile1_del_ck, exfile2_del_ck);
		if (result > 0) {
			this.pw.write("<script>alert('문의를 정상적으로 수정하였습니다.'); location.href='./qa_list.do';</script>");	
		} else {
			this.pw.write("<script>alert('문의를 정상적으로 수정하지 못하였습니다.'); history.go(-1);</script>");	
		}
		this.pw.close();
	}
	
	@PostMapping("/qa_delete.do")
	public void qa_delete(@ModelAttribute qaboard_dto dto, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		int result = this.qadao.delete(dto);
		if (result > 0) {
			this.pw.write("<script>alert('문의를 정상적으로 삭제하였습니다.'); location.href='./qa_list.do';</script>");	
		} else {
			this.pw.write("<script>alert('문의를 정상적으로 삭제하지 못하였습니다.'); history.go(-1);</script>");	
		}
		this.pw.close();
	}
}

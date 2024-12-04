package admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import member.qaboard_dto;

@Repository("admin_module")
public class admin_dao {

	@Resource(name = "sstemplate")
	private SqlSessionTemplate sstem;
	
	HttpSession hs = null;
	
	Map<String, String> keycode = null;
	
	public int admin_login(admin_dto dto, HttpServletRequest req) {
		int callback = 0;
		admin_dto lgdto = this.sstem.selectOne("admindb.login", dto);
		if (lgdto.getApass().equals(dto.getApass())) {
			ArrayList<Object> data = lgdto.toList();
			this.hs = req.getSession();
			this.hs.setAttribute("admin", data);
			callback = 1;
		}
		return callback;
	}
	
	public List<qaboard_dto> getQaList(String check) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", (check.equals("1") ? "3" : "4"));
		return this.sstem.selectList("qadb.printQaBoard", this.keycode);
	}
	
	public int addAnswerQa(qaboard_dto dto) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		this.keycode.put("answer", dto.getAnswer());
		this.keycode.put("awriter", String.valueOf(dto.getAwriter()));
		this.keycode.put("qidx", String.valueOf(dto.getQidx()));
		return this.sstem.update("qadb.modifyQaBoard", this.keycode);
	}
}

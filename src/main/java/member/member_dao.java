package member;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("member_module")
public class member_dao {

	@Resource(name = "sstemplate")
	private SqlSessionTemplate sstem;
	
	HttpSession htss = null;
	
	public String searchID(member_dto dto) {
		return this.sstem.selectOne("memberdb.searchid_select", dto);
	}
	
	public member_dto searchPW(member_dto dto) {
		return this.sstem.selectOne("memberdb.searchpw_select", dto);
	}
	
	public int changePW(member_dto dto) {
		return this.sstem.update("memberdb.paaword_update", dto);
	}
	
	public int sign_member(member_dto dto) {
		return this.sstem.insert("memberdb.signup", dto);
	}
	
	public member_dto login_member(String auto_login, member_dto dto, HttpServletRequest req) {
		member_dto mdto = this.sstem.selectOne("memberdb.login_select", dto);
		if (mdto != null) {
			ArrayList<Object> sdata = mdto.toList();
			sdata.add(auto_login);
			this.htss = req.getSession();
			this.htss.setAttribute("user", sdata);
		}
		return mdto;
	}
	
	public int id_duplicate(String mid) {
		return this.sstem.selectOne("memberdb.id_check", mid);
	}
}

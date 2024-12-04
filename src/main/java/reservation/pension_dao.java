package reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("pension_module")
public class pension_dao {
	@Resource(name = "sstemplate")
	private SqlSessionTemplate sstem;
	
	Map<String, String> keycode = null;
	
	public List<pension_dto> mainpage_select() {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		return this.sstem.selectList("pensiondb.getList", this.keycode);
	}
	
	public List<String> rinfo_selelct(String pname) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "2");
		this.keycode.put("pname", pname);
		return this.sstem.selectList("pensiondb.getList", this.keycode);
	}
	
	public List<List<Object>> ajax_select(String pname) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "3");
		this.keycode.put("pname", pname);
		List<pension_dto> data = this.sstem.selectList("pensiondb.getList", this.keycode);
		List<List<Object>> result = new ArrayList<List<Object>>();
		int n = 0;
		do {
			result.add(data.get(n).toList());
			n++;
		} while (n < data.size());
		return result;
	}
}

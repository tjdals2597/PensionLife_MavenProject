package member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository("qa_module")
public class qaboard_dao {

	@Resource(name = "sstemplate")
	private SqlSessionTemplate sstem;
	
	Map<String, String> keycode = null;
	
	public List<qaboard_dto> getQaList(String qwriter) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		this.keycode.put("qwriter", qwriter);
		return this.sstem.selectList("qadb.printQaBoard", this.keycode);
	}
	
	public qaboard_dto getQaView(String qidx) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "2");
		this.keycode.put("qidx", qidx);
		return this.sstem.selectOne("qadb.printQaBoard", this.keycode);
	}
	
	public int insert(qaboard_dto dto, MultipartFile qfile[], String file_url) throws Exception {
		dto.setQfile(new file_handler(qfile, file_url).saveFile());
		return this.sstem.insert("qadb.addQaBoard", dto);
	}
	
	public int update(qaboard_dto dto, MultipartFile qfile[], String file_url,
			String exfile1_del_ck, String exfile2_del_ck) throws Exception {
		this.keycode = new HashMap<String, String>();
		file_handler fh = new file_handler(qfile, file_url);
		this.keycode.put("part", "2");
		this.keycode.put("qtitle", dto.getQtitle());
		this.keycode.put("qtext", dto.getQtext());
		this.keycode.put("qfile", fh.updateFile(dto.getQfile(), exfile1_del_ck, exfile2_del_ck));
		this.keycode.put("qidx", String.valueOf(dto.getQidx()));
		return this.sstem.update("qadb.modifyQaBoard", this.keycode);
	}
	
	public int delete(qaboard_dto dto) {
		return this.sstem.delete("qadb.deleteQaBoard", dto);
	}
}

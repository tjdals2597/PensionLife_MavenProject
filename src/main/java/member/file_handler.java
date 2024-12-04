package member;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class file_handler {

	private StringBuilder sb = null;
	private StringBuilder sbu = null;
	private MultipartFile[] qfile = null;
	private String folder_url = null;
	private File file_object = null;
	
	public file_handler(MultipartFile[] qfile, String file_url) throws Exception {
		this.qfile = qfile;
		this.folder_url = file_url;
	}
	
	public String saveFile() throws Exception {
		this.sb = new StringBuilder();
		int w = 0;
		while (w < this.qfile.length) {
			if (!this.qfile[w].isEmpty()) {
				if (!this.qfile[0].isEmpty() && w == 1) {
					this.sb.append("|");
				}
				String filename = this.qfile[w].getOriginalFilename();
				this.sb.append(filename);
				this.sb.append("|");
				String refilename = this.rename(filename);
				this.sb.append(refilename);
				FileCopyUtils.copy(this.qfile[w].getBytes(), new File(this.folder_url + refilename));
			}
			w++;
		}
		return this.sb.toString();
	}
	
	public String rename(String name) {
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(day);
		int no = (int) Math.ceil(Math.random() * 10000);
		String datacode = today + no;
		return datacode + name.substring(name.indexOf("."));
	}

	public String updateFile(String ex_qfile, String exfile1_del_ck, String exfile2_del_ck) throws Exception {
		this.sbu = new StringBuilder();
		String[] fname = ex_qfile.split("\\|");
		if (fname.length > 1) {
			if (exfile1_del_ck.equals("true") && exfile2_del_ck.equals("true")) {
				this.file_object = new File(this.folder_url + fname[1]);
				this.file_object.delete();
				this.file_object = new File(this.folder_url + fname[3]);
				this.file_object.delete();
				this.sbu.append(this.saveFile());
				
			}
			else if (!exfile1_del_ck.equals("true") && exfile2_del_ck.equals("true")) {
				this.file_object = new File(this.folder_url + fname[3]);
				this.file_object.delete();
				this.sbu.append(fname[0] + "|" + fname[1]);
				this.sbu.append(this.saveFile());
			}
			else if (fname.length == 4 && exfile1_del_ck.equals("true") && !exfile2_del_ck.equals("true")) {
				this.file_object = new File(this.folder_url + fname[1]);
				this.file_object.delete();
				this.sbu.append(fname[2] + "|" + fname[3]);
				this.sbu.append(this.saveFile());
			}
			else if (fname.length == 2 && exfile1_del_ck.equals("true") && !exfile2_del_ck.equals("true")) {
				this.file_object = new File(this.folder_url + fname[1]);
				this.file_object.delete();
				this.sbu.append(this.saveFile());
			}
			else if (!exfile1_del_ck.equals("true") && !exfile2_del_ck.equals("true")) {
				this.sbu.append(ex_qfile);
			}
		}
		else {
			this.sbu.append(this.saveFile());
		}
		return this.sbu.toString();
	}
}

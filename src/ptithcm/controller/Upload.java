package ptithcm.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.bean.UploadFile;

@Controller
public class Upload {
	
	@Autowired
	@Qualifier("uploadfile")
	UploadFile UpFile;
	
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String upload() {
		return "upload";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploaded(ModelMap model, @RequestParam("image") MultipartFile image) {
		if(image.isEmpty()) {
			model.addAttribute("message", "Vui lòng chọn file");
		}
		else {
			try {
				String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +"_";
				String filename = date + image.getOriginalFilename();
				String path = UpFile.getBasePath() + File.separator + filename;
				image.transferTo(new File(path));
				Thread.sleep(2500);
				model.addAttribute("message", "Upload thanh cong");
				model.addAttribute("url", path);
				model.addAttribute("filename", filename);
			}catch(Exception e) {
				model.addAttribute("message", "Lỗi upload file");
			}
		}
		return "upload";
	}
}

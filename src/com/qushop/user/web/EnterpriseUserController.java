package com.qushop.user.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qushop.common.util.Hanyu;
import com.qushop.common.util.PublicUtil;


@Controller
@RequestMapping("user/enterpriseUser")
public class EnterpriseUserController {

	

	@RequestMapping("entuserupload.action")
	@ResponseBody
	public Object uploadImage(String entname,String certname,HttpServletRequest request){
		MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
		String entnames = new Hanyu().getStringPinYin(entname);
		MultipartFile file = req.getFile("filedata");
		try {
			String rootPath = PublicUtil.getRootFileDirectory(request)+"media_upload/entupload/"+entnames;
			File directyPah = new File(rootPath);
			if(!directyPah.exists()){
				directyPah.mkdirs();
			}
			String yfileName = file.getOriginalFilename();
			//后缀
			String suffix = yfileName.substring(yfileName.lastIndexOf("."));  
			//上传后的名字
			String fileName = certname+suffix;
			File imageFile = new File(rootPath+"/"+fileName);
			if(!imageFile.exists()){
				imageFile.createNewFile();
			}
			FileCopyUtils.copy(file.getBytes(), imageFile);
			
			return "media_upload/entupload/"+entnames+"/"+fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
}

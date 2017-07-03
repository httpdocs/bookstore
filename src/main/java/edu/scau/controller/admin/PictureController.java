package edu.scau.controller.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import edu.scau.service.admin.PictureService;

@Controller
@RequestMapping("picmgr")
public class PictureController {

	@Autowired
	private PictureService service;
	
	private static int count = 0;
	
	@RequestMapping("upload")
	public void upload(@RequestParam("img") CommonsMultipartFile file, HttpServletResponse response){
		long time = System.currentTimeMillis();
		String path = "images/"+time+"_"+(count++%10000)+".jpg";
		JSONObject json = new JSONObject();
		try {
			OutputStream out = new FileOutputStream(path);
			InputStream in = file.getInputStream();
			byte[] buffer = new byte[2048];
			int len;
			while((len = in.read(buffer)) != -1){
				out.write(buffer, 0, len);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			json.put("status", -1);
			json.put("msg", "上传出错");
			try {
				response.getWriter().println(json.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		int id = service.upload(path);
		if(id == -1){
			json.put("status", -1);
			json.put("msg", "上传出错");
		} else {
			json.put("status", 0);
			json.put("msg", "上传成功");
			json.put("pictureId", id);
		}
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}

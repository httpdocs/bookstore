package edu.scau.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import edu.scau.model.Picture;
import edu.scau.service.PictureService;

@Controller
public class PictureManageController {

	@Autowired
	private PictureService service;

	private static int count = 0;

	@RequestMapping("/picmgr/upload")
	public void upload(@RequestParam("img") MultipartFile file,String isbn, HttpServletResponse response) {
		long time = System.currentTimeMillis();
		File dir = new File("images");
		if (!dir.exists()) {
			dir.mkdir();
		}
		String path = "images/" + time + "_" + (count++ % 10000) + ".jpg";
		JSONObject json = new JSONObject();
		try {
			OutputStream out = new FileOutputStream(path);
			InputStream in = file.getInputStream();
			byte[] buffer = new byte[2048];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			out.close();
			in.close();
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
		int id = service.upload(path,isbn);
		if (id == -1) {
			json.put("status", -1);
			json.put("msg", "上传出错");
		} else {
			json.put("status", 0);
			json.put("msg", "上传成功");
			json.put("pictureId", id);
			try {
				response.sendRedirect("/admin/right_book_select.html");
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@RequestMapping("/pictures/get")
	public void get(Picture picture, HttpServletResponse response) {
		try {
			InputStream in = service.get(picture);
			if (in == null) {
				return;
			}
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			byte[] buffer = new byte[2048];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@RequestMapping("/picmgr/bind")
	public void bind(Picture picture, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		switch (service.bind(picture)) {
		case 0:
			json.put("code", 0);
			json.put("msg", "操作成功");
			break;
		case -1:
			json.put("code", -1);
			json.put("msg", "操作失败");
			break;
		case -2:
			json.put("code", -2);
			json.put("msg", "该图片已绑定其它图书");
			break;
		case -3:
			json.put("code", -3);
			json.put("msg", "图片不存在");
			break;
		default:
			json.put("code", -1);
			json.put("msg", "操作失败");
			break;
		}
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/picmgr/delete")
	public void delete(Picture picture, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		switch (service.delete(picture)) {
		case 0:
			json.put("code", 0);
			json.put("msg", "操作成功");
			break;
		case -1:
			json.put("code", -1);
			json.put("msg", "操作失败");
			break;
		case -3:
			json.put("code", -3);
			json.put("msg", "图片不存在");
			break;
		default:
			json.put("code", -1);
			json.put("msg", "操作失败");
			break;
		}
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

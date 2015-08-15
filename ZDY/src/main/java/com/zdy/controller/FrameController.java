package com.zdy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frame")
public class FrameController {
	@RequestMapping("/getTop")
	public String getUser() {
			return "main/top";
	}
	@RequestMapping("/getLeft")
	public String getLeft() {
			return "main/left";
	}
	@RequestMapping("/getIndex")
	public String getIndex() {
			return "main/index";
	}
	@RequestMapping("/getMain")
	public String getMain() {
			return "main/main";
	}
}

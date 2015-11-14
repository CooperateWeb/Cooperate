package com.cooperate.fly.web.controller;

import javax.annotation.Resource;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooperate.fly.service.user.DataExistsException;
import com.cooperate.fly.service.user.UserExistsException;
import com.cooperate.fly.web.util.Result;

public class BaseController {
	private static Logger logger=Logger.getLogger(BaseController.class);
	
	@Resource
	private MessageSource messageSource;
	
	@ExceptionHandler
	@ResponseBody
	public Result exception(Exception e){
		String msg=null;
		if(e instanceof IllegalArgumentException){
			msg=e.getMessage();
		}
		else if (e instanceof UserExistsException) {
			msg=e.getMessage();
		}
		else if (e instanceof DataExistsException) {
			msg=e.getMessage();
		}else {
			logger.error("系统出错" ,e);
			msg="system error";
		}
		
		String message=null;
		try {
			message=this.messageSource.getMessage(msg, null,null);
		} catch (NoSuchMessageException e2) {
			message=msg;
		}
		return new Result(message);
	}
}

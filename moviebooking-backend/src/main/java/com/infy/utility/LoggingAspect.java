package com.infy.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

		public static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
		
		@Before("execution(* com.infy.service.MovieServiceImpl.addMovie(..))")
		public void beforeAdvice() {
			LOGGER.info("before advice called");
		}
		@AfterThrowing(pointcut = "execution(* com.infy.service.MovieServiceImpl.addMovie(..))",throwing = "exception")
		public void AfterThrowingAdvice(Exception exception) {
			LOGGER.info(exception.getMessage());
			
		}
	}



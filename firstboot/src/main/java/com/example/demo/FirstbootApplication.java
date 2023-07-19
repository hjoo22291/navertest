package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//기본적으로 @ComponentScan(basePackages = "com.example.demo") 라는 표현이 포함되어있음. but 추가하면 기본값 없어짐
@ComponentScan(basePackages = "upload") //그냥 추가만하면 기본값 없어짐 -> 기본값도 스캔하라고 추가해줘야함.
//@ComponentScan(basePackageClasses = MyPathConfig.class) //MyPathConfig 파일이 있는 class 라는 의미. 윗줄과 같은 표현
@ComponentScan(basePackages = "com.example.demo")
@ComponentScan(basePackages = "errors")
@ComponentScan(basePackages = "board.spring.mybatis")
// ㄴ @Service, @Repository 등 만 스캔
@MapperScan(basePackages = "board.spring.mybatis")
// ㄴ @Mapper 스캔은 따로 지정해줘야함
public class FirstbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(FirstbootApplication.class, args);
		System.out.println("서버 시작중");
	}

}

/*서버 시작 - @패키지 스캔 포함
1. 스프링 부트 메인클래스 : 자동 서버 시작 - com.example.demo패키지
2. jsp파일 경로/확장자 비포함 - 서블릿 api, jstl api 비포함. pom.xml에 수동 추가 설정
3. xml 최소화 / application.properties 파일 설정 - 서버포트, jsp 확장자/경로 설정
4. ajax - pom.xml 라이브러리 추가 X
 - jquery 라이브러리 추가 O
 - src/main/resources/static/js/jqueryxxx.js 위치에 넣으면됨.
 					/static/images/*.*
 					/static/css/*.css
 	=> http://localhost:8063/js/jqueryxxx.js
5-1. file upload - pom.xml 라이브러리 추가 X
5-2. servlet-context.xml 불필요
5-3. javax.servlet 패키지명 -> jakarta.servlet 으로 import 바꿔줘야함.
- c:/kdt/upload/a.png 업로드했다면 http://localhost:8063/upload/a.png 이미지를 보여주고싶다?
서버 파일 저장 폴더 = url매핑(브라우저 출력)을 해주고싶다 (resources mapping 태그 대신)

*/	

package com.bside.breadgood.sample.api.member;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class HomeController {

    KakaoApi kakaoApi = new KakaoApi();

    @RequestMapping(value="/login")
    public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String accessToken = kakaoApi.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

        System.out.println("login info :" + userInfo.toString());

        if(userInfo.get("nickname") != null) {
//            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_token", accessToken);
        }
        mav.addObject("userId", userInfo.get("nickname"));
        mav.setViewName("signup");

        return mav;
    }

    @RequestMapping(value ="/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        kakaoApi.kakaoLogout((String)session.getAttribute("access_token"));
        session.removeAttribute("access_token");
        session.removeAttribute("userId");
        mav.setViewName("index");
        return mav;
    }
}

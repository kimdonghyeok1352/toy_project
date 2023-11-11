package com.donghyeok.community.Controller;

import com.donghyeok.community.DTO.MemberDTO;
import com.donghyeok.community.common.ProjectVersion;
import com.donghyeok.community.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@RestController//@Controller + @ReponseBody
@RequiredArgsConstructor //생성자 주입을 임의의 코드 없이 자동으로 설정
@RequestMapping(MemberController.URL.BASE) // url에서 공통적인 부분을 매핑 해주기 위함
public class MemberController {

        public static class URL {
            public static final String BASE = ProjectVersion.VERSION + "/member";
        }


        private final MemberService memberService;

        //회원가입
        @PostMapping("/signIn")
        MemberDTO.ResponseMemberJoin memberJoin(@ModelAttribute MemberDTO.RequestMemberJoin requestmemberJoin){

            return memberService.memberJoin(requestmemberJoin);

        }

        @PostMapping("/logIn")
        MemberDTO.LoginResponse loginResponse(@ModelAttribute MemberDTO.LoginRequest loginRequest,HttpSession session){
            MemberDTO.LoginResponse loginResponse = memberService.memberLogin(loginRequest);
            session.setAttribute("member_info_id",loginResponse.getMember_info_id());
            return loginResponse;
        }


        @GetMapping("/memberInfo/{member_info_id}")
        MemberDTO.MemberInfoResponse memberInfo (@PathVariable("member_info_id") Long memberInfoId,HttpSession session){
            return memberService.memberInfo(memberInfoId);
        }

        @PutMapping("/memberDelete/{member_info_id}")
        MemberDTO memberDelete(HttpServletRequest request,@PathVariable("member_info_id") Long memberInfoId){
            return memberService.memberDelete(memberInfoId);
        }

        @PostMapping("/memberUpdate")
            MemberDTO memberUpdate(HttpServletRequest request ,@ModelAttribute MemberDTO memberDTO){
            return memberDTO;
        }
}

package com.donghyeok.community.Controller;

import com.donghyeok.community.DTO.MemberDTO;
import com.donghyeok.community.common.ProjectVersion;
import com.donghyeok.community.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    Boolean memberJoin(HttpServletRequest request, @ModelAttribute MemberDTO memberDTO){
        System.out.println("api 호출");
        memberService.memberJoin(memberDTO);
        return true;
    }

    @PostMapping("/logIn")
    MemberDTO memberLogin(HttpServletRequest request, HttpSession session, @ModelAttribute MemberDTO memberDTO){
        memberDTO = memberService.memberLogin(memberDTO);
        if(memberDTO != null){
            session.setAttribute("member_info_Id" + memberDTO.getMember_info_id(),memberDTO.getMember_info_id());
            return memberDTO;
        }else{
            return null;
        }

    }

    @DeleteMapping("/logOut")
    Boolean memberLogOut(HttpServletRequest request, HttpSession session ,@RequestParam(value = "member_info_id")Long id){

        if(memberService.memberLogOut((id))){
            session.removeAttribute(("member_info_Id"+ id));
            return true;
        }else {
            return false;
        }
    }


}

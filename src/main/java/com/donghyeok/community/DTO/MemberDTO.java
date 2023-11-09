package com.donghyeok.community.DTO;

import com.donghyeok.community.MemberInfoRepository.MemberInfoRepository;
import com.donghyeok.community.entity.MemberInfoEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


public class MemberDTO {


    @AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
    @Getter
    @Builder
    public static class RequestMemberJoin{
        private String member_id;
        private String password;
        private String email;
        private String phone_number;

    }

    @AllArgsConstructor
    @Getter
    public static class LoginRequest{
        private String member_id;
        private String password;
    }

    @AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
    @NoArgsConstructor
    @Getter
    @Builder
    public static class ResponseMemberJoin{
        private String member_id;
        private String password;
        private String email;
        private String phone_number;

        public ResponseMemberJoin memberJoin(MemberInfoEntity memberInfoEntity) {

            return ResponseMemberJoin.builder().
                    member_id(memberInfoEntity.getMemberId())
                    .password(memberInfoEntity.getPassword())
                    .email(memberInfoEntity.getEmail())
                    .phone_number(memberInfoEntity.getPhoneNumber()).build();
        }
    }

    @AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
    @NoArgsConstructor
    @Getter
    @Builder
    public static class LoginResponse{
        private String member_id;
        private String password;

        public LoginResponse memberLogin(MemberInfoEntity memberInfoEntity){

            return LoginResponse.builder().
                    member_id(memberInfoEntity.getMemberId()).
                    password(memberInfoEntity.getPassword()).
                    build();
        }
    }


}

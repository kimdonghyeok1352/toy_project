package com.donghyeok.community.DTO;

import com.donghyeok.community.MemberInfoRepository.MemberInfoRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@ToString
@Builder
public class MemberDTO {
    private Long member_info_id;
    private  String member_id;
    private String password;
    private  String nick_name;
    private String phone_number;
    private  String status;
    private  String grade;
    private LocalDateTime created_at;
    private  LocalDateTime updated_at;

    //Entity > MemberDTO 변환
    public MemberDTO toMemberDTO(MemberInfoRepository memberInfoRepository){

        MemberDTO memberDTO = MemberDTO.builder()
                .grade("1")
                .build();

        return memberDTO;
    }
}

package com.donghyeok.community.entity;

import com.donghyeok.community.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Builder
@Table(name="member_info")
public class MemberInfoEntity {

    @Column(name = "member_info_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberInfoId;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "password")
    private String password;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name= "status")
    private String status;

    @Column(name = "grade")
    private  String grade;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;


    //memberDTO > MemberInfoEntity


    public MemberInfoEntity toMemberInfoEntity(MemberDTO memberDTO){

        MemberInfoEntity memberInfoEntity = MemberInfoEntity.builder()
                .memberInfoId(memberDTO.getMember_info_id())
                .memberId(memberDTO.getMember_id())
                .password(memberDTO.getPassword())
                .nickName(memberDTO.getNick_name())
                .phoneNumber(memberDTO.getPhone_number())
                .status(memberDTO.getStatus())
                .grade(memberDTO.getGrade())
                .createAt(memberDTO.getCreated_at())
                .updateAt(memberDTO.getUpdated_at())
                .build();


    return memberInfoEntity;
    }
}

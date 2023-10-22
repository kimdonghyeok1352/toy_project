package com.donghyeok.community.service;

import com.donghyeok.community.DTO.MemberDTO;
import com.donghyeok.community.MemberInfoRepository.MemberInfoRepository;
import com.donghyeok.community.entity.MemberInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberInfoRepository memberInfoRepository;
    public Boolean memberJoin (MemberDTO memberDTO){

        if(memberInfoRepository.existsByMemberId(memberDTO.getMember_id()) == true){
            System.out.println("중복아이디 | 처리불가");
            return false;
        }else{
            MemberInfoEntity memberInfoEntity = new MemberInfoEntity();
            memberInfoRepository.save(memberInfoEntity.toMemberInfoEntity(memberDTO));
            return true;
        }
    }

    //로그인
    public MemberDTO memberLogin(MemberDTO memberDTO){
        Optional<MemberInfoEntity> byMemberId = memberInfoRepository.findByMemberId(memberDTO.getMember_id());
        if(byMemberId.isPresent()){ //아이디 조회가 있을 경우
            MemberInfoEntity memberInfoEntity = byMemberId.get();
            if(memberInfoEntity.getPassword().equals(memberDTO.getPassword())){ //비밀번호가 같을 경우 로그인 성공
                return memberDTO;
            }else{ //비밀번호 불일치
                return null;
            }
        }else{//아이디 조회가 없을 경우
            return null;
        }

    }

    public Boolean memberLogOut(Long memberInfoId){

        Optional<MemberInfoEntity> optionalMemberInfoEntity = memberInfoRepository.findById(memberInfoId);
        if(optionalMemberInfoEntity.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}

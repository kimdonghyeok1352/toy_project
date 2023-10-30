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

        MemberInfoEntity memberInfoEntity = new MemberInfoEntity();
        System.out.println("memberInfoEntity.toMemberInfoEntity(memberDTO).getMemberId()" + memberInfoEntity.toMemberInfoEntity(memberDTO).getMemberId());
        if(memberInfoRepository.existsByMemberId(memberInfoEntity.toMemberInfoEntity(memberDTO).getMemberId()) == true){
            System.out.println("중복아이디 | 처리불가");
            return false;
        }else{
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

    public  MemberDTO findByIdPassWord(MemberDTO memberDTO){
        Optional<MemberInfoEntity> optionalMemberInfoEntity = memberInfoRepository.findByMemberId(memberDTO.getMember_id());
        if(optionalMemberInfoEntity.isPresent()){
            return  memberDTO;
        }else{
            return null;
        }
    }

    public  MemberDTO memberInfo(Long memberInfoId){
        MemberDTO memberDTO = new MemberDTO();
        Optional<MemberInfoEntity> MemberInfoEntity = memberInfoRepository.findById(memberInfoId);
        if(MemberInfoEntity.isPresent()){ //조회 성공
            return memberDTO.toMemberDTO(MemberInfoEntity.get());
        }else{//조회 실패
            return null;
        }
    }
    public  MemberDTO memberDelete(Long memberInfoId){
        MemberDTO memberDTO = new MemberDTO();
        Optional<MemberInfoEntity> MemberInfoEntity = memberInfoRepository.findById(memberInfoId);
        if(MemberInfoEntity.isPresent()){ //맞는 아이디 있으면 삭제
            memberInfoRepository.delete(MemberInfoEntity.get());
            return memberDTO.toMemberDTO(MemberInfoEntity.get());
        }else{
            return null;
        }
    }

    public void memberUpdate(MemberDTO memberDTO){
        Optional<MemberInfoEntity> MemberInfoEntity = memberInfoRepository.findById(memberDTO.getMember_info_id());
        if(MemberInfoEntity.isPresent()){

        }
    }
}

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
    public MemberDTO.ResponseMemberJoin memberJoin (MemberDTO.RequestMemberJoin requestMemberjoin){

        Optional<MemberInfoEntity> byMemberId = memberInfoRepository.findByMemberId(requestMemberjoin.getMember_id());
        if(byMemberId.isPresent()){
            return new MemberDTO.ResponseMemberJoin();
        }

        return new MemberDTO.ResponseMemberJoin().
                memberJoin(memberInfoRepository.save(new MemberInfoEntity().tomemberInfoEntity(requestMemberjoin)));

    }

    //로그인
    public MemberDTO.LoginResponse memberLogin(MemberDTO.LoginRequest loginRequest){
        MemberInfoEntity byMemberId = memberInfoRepository.findByMemberId(loginRequest.getMember_id()).
                orElseThrow(()->new RuntimeException("없는 아이디 입니다."));
        MemberInfoEntity byMemberIdPassWord = memberInfoRepository.findByMemberIdAndPassword(loginRequest.getMember_id(),loginRequest.getPassword())
                .orElseThrow(()->new RuntimeException("비밀번호가 일치 하지 않습니다."));

        return new MemberDTO.LoginResponse().memberLogin(byMemberIdPassWord);
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
//        Optional<MemberInfoEntity> optionalMemberInfoEntity = memberInfoRepository.findByMemberId(memberDTO.getMember_id());
//        if(optionalMemberInfoEntity.isPresent()){
//            return  memberDTO;
//        }else{
//            return null;
//        }
        return  null;
    }

    public  MemberDTO memberInfo(Long memberInfoId){
        MemberDTO memberDTO = new MemberDTO();
//        Optional<MemberInfoEntity> MemberInfoEntity = memberInfoRepository.findById(memberInfoId);
//        if(MemberInfoEntity.isPresent()){ //조회 성공
//            return memberDTO.toMemberDTO(MemberInfoEntity.get());
//        }else{//조회 실패
//            return null;
//        }
        return null;
    }
    public  MemberDTO memberDelete(Long memberInfoId){
        MemberDTO memberDTO = new MemberDTO();
//        Optional<MemberInfoEntity> MemberInfoEntity = memberInfoRepository.findById(memberInfoId);
//        if(MemberInfoEntity.isPresent()){ //맞는 아이디 있으면 삭제
//            memberInfoRepository.delete(MemberInfoEntity.get());
//            return memberDTO.toMemberDTO(MemberInfoEntity.get());
//        }else{
//            return null;
//        }
        return  null;
    }

    public void memberUpdate(MemberDTO memberDTO){
//        Optional<MemberInfoEntity> MemberInfoEntity = memberInfoRepository.findById(memberDTO.getMember_info_id());
//        if(MemberInfoEntity.isPresent()){
//
//        }
    }
}

package com.donghyeok.community.MemberInfoRepository;

import com.donghyeok.community.DTO.MemberDTO;
import com.donghyeok.community.entity.MemberInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Long> {

    boolean existsByMemberId(String memberId);

    Optional<MemberInfoEntity> findByMemberId(String memberId);
    Optional<MemberInfoEntity> findByMemberIdAndPassword(String memberId,String passWord);

    Optional<MemberInfoEntity> findByMemberInfoId(Long memberInfoId);
}

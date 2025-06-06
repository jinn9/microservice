package com.github.jinn9.member.service;

import com.github.jinn9.member.entity.Member;
import com.github.jinn9.member.exception.DuplicateMemberException;
import com.github.jinn9.member.exception.MemberNotFoundException;
import com.github.jinn9.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(Member member) {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if (optionalMember.isPresent()) {
            throw new DuplicateMemberException("Member already exists with this email: " + member.getEmail());
        }

        memberRepository.save(member);

        log.debug("Member created. id: " + member.getId());
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->
                new MemberNotFoundException("Member not found"));
    }



}

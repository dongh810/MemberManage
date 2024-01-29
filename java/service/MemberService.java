package service;

import aggregate.Member;
import repository.MemberRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberService {
    private final MemberRepository mr = new MemberRepository();
    Member mb = new Member();

    public MemberService() {
    }

    public void selectAllMembers() {
        ArrayList<Member> selectedMembers = mr.selectAllMembers();

        /* 설명. 회원이 한명도 없어서 조회 결과가 없더라도 ArrayList객체는 넘어온다.(Empty상태로) */
        if(!selectedMembers.isEmpty()) {        // 회원이 한명이라도 조회 된다면
            System.out.println("==== service까지 잘 반환되어 오나 확인 ====");
            for(Member m: selectedMembers) {
                System.out.println(m);
            }

            return;                             // 이후 코드와 상관 없이 메소드 종료
        }

        /* 설명. 조건이 맞지 않아(회원이 조회되지 않아) 출력을 하는 구문(위의 조건이 맞으면 실행되지 않음)(feat.else 안 쓰기) */
        System.out.println("슬프게도 우리 싸이트는 아직 회원이 없습니다.");
    }

    /* 설명. 전달된 회원 번호를 활용해 repository에 있는 memberList로부터 해당 회원 찾아 반환 받기 */
    public void selectMember(int memNo) {
        Member selectedMember = mr.selectMember(memNo);

        if(selectedMember == null) {
            System.out.println("그런 회원이 없네요!~");
        } else {
            System.out.println("조회된 회원은: " + selectedMember);
        }
    }

    /* 설명. 입력받아 넘어온 회원이 가질 번호를 만들고 추가 후 repository로 전달 후 결과 확인 */
    public void registMember(Member member) {
//        System.out.println("사용자가 입력해서 넘겨준 Member 확인: " + member);

        int lastMemberNo = mr.selectLastMemberNo();
        member.setMemNo(lastMemberNo + 1);
        int result = mr.registMember(member);
        if(result == 1) {
            System.out.println(member.getId() + "님의 회원 가입이 성공하였습니다.");
        }
    }

    public void deleteMember(int memNo) {
        int result = mr.deleteMember(memNo);
        if(result > 0) {
            System.out.println(memNo + "번 회원 탈퇴를 성공하였습니다.");
            return;
        }

        System.out.println("회원 탈퇴에 실패하였습니다.");
    }

//    public String inputId() {
//        boolean valiId = false;
//        String id;
//        while(true) {
//            System.out.print("아이디를 입력해주세요: ");
//            Scanner sc = new Scanner(System.in);
//            id = sc.nextLine();
//            valiId = mb.checkID(id);
//            if(valiId) {
//                break;
//            }
//        }
//        return id;
//    }
//
//    public String inputPwd() {
//        boolean valiPwd;
//        String pwd;
//        while(true) {
//            System.out.print("비밀번호를 입력해주세요: ");
//            Scanner sc = new Scanner(System.in);
//            pwd = sc.nextLine();
//            valiPwd = mb.checkPwd(pwd);
//            if(valiPwd) {
//                break;
//            }
//        }
//        return pwd;
//    }
//
//    public String inputPhoneNum() {
//        String phoneNum;
//        boolean valiPhoneNum;
//        while(true) {
//            System.out.print("전화번호를 입력해주세요: ");
//            Scanner sc = new Scanner(System.in);
//            phoneNum = sc.nextLine();
//            valiPhoneNum = mb.checkPwd(phoneNum);
//            if(valiPhoneNum) {
//                break;
//            }
//        }
//        return phoneNum;
//    }
//
//    public String inputPersonNum() {
//        String personNum;
//        boolean valiPersonNum;
//        while(true) {
//            System.out.print("주민번호를 입력해주세요: ");
//            Scanner sc = new Scanner(System.in);
//            personNum = sc.nextLine();
//            valiPersonNum = mb.checkPwd(personNum);
//            if(valiPersonNum) {
//                break;
//            }
//        }
//        return personNum;
//    }
//
//    public String inputEmail() {
//        String email;
//        boolean valiEmail;
//        while(true) {
//            System.out.print("이메일을 입력해주세요: ");
//            Scanner sc = new Scanner(System.in);
//            email = sc.nextLine();
//            valiEmail = mb.checkPwd(email);
//            if(valiEmail) {
//                break;
//            }
//        }
//        return email;
//    }

    public boolean validationThings(Member member) {
        boolean valiId = mb.checkID(member.getId());
        boolean valiPwd =  mb.checkPwd(member.getPwd());
        boolean valiPhoneNum =  mb.checkPhoneNum(member.getPhoneNum());
        boolean valiPersonNum =  mb.checkPersonNum(member.getPersonNum());
        boolean valiEmail =  mb.checkEmail(member.getEmail());

        if( valiEmail && valiPwd && valiId && valiPersonNum && valiPhoneNum ) {
            return true;
        } else return false;
    }
}

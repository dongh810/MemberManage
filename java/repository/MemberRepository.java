package repository;

import aggregate.BloodType;
import aggregate.Mbti;
import aggregate.Member;
import service.MemberService;
import stream.MyObjectOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MemberRepository {
    private ArrayList<Member> memberList = new ArrayList<>();

    /* 설명. 프로그램이 켜지자 마자(MemberRepository()가 실행되자마자) 파일에 dummy 데이터 추가 및 입력받기 */
    public MemberRepository() {

        /* 설명. 회원가입 기능 추가 후 이제는 파일이 기존에 존재하면(처음이 아니므로) 회원 3명으로 초기화 하기를 하지 않는다. */
        File file = new File("src/main/java/db/memberDB.dat");
        if(!file.exists()) {
            ArrayList<Member> members = new ArrayList<>();
            members.add(new Member(1, "user01", "pass01", "홍길동", "010-1234-1234", "abcd@naver.com", "999999-1234567",20,
                    new String[]{"발레", "수영"}, BloodType.A, Mbti.ENFJ));
            members.add(new Member(2, "user02", "pass02", "이순신", "010-9999-1234", "isun@naver.com", "550493-1238473",10,
                    new String[]{"게임", "영화시청"}, BloodType.B, Mbti.ENTJ));

            saveMembers(members);
        }

        loadMembers();

//        System.out.println("==== repository에서 회원정보 다 읽어왔는지 확인 ====");
//        for(Member m: memberList) {
//            System.out.println(m);
//        }
    }

    /* 설명. 회원이 담긴 ArrayList를 던지면 파일에 출력하는 기능 */
    private void saveMembers(ArrayList<Member> members) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("src/main/java/db/memberDB.dat")));

            /* 설명. 넘어온 회원 수만큼 객체 출력하기 */
            for(Member m: members) {
                oos.writeObject(m);
            }

            oos.flush();                // 출력 시에는 flush 해 줄것
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* 설명. 파일로부터 회원 객체들을 입력받아 memberList에 쌓기 */
    private void loadMembers() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream("src/main/java/db/memberDB.dat")));

            /* 설명. 파일로부터 모든 회원 정보를 읽어 memberList에 추가(add) */
            while(true) {
                memberList.add((Member)(ois.readObject()));
            }

        } catch (EOFException e) {
            System.out.println("회원 정보 모두 로딩됨...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Member> selectAllMembers() {
        return memberList;
    }

    public Member selectMember(int memNo) {
        for(Member m: memberList) {
            if(m.getMemNo() == memNo) return m;
        }
        return null;
    }

    public int selectLastMemberNo() {
//        Member latestMember = memberList.get(memberList.size() - 1);        // 가장 최근에 가입한 회원
//        return latestMember.getMemNo();                                     // 그 회원의 번호
        return memberList.get(memberList.size() - 1)
                .getMemNo();
    }

    /* 설명. 기존 회원(객체)에 이어서 파일 출력을 하고 추가 한 객체의 수를 반환(feat.DML 작업의 결과는 int) */
    /* 설명. 객체 출력을 할 예정인데 기존 ObjectOutputStream 대신 새로 정의한 스트림으로 회원 한명 파일 출력해서 int 반환하기(feat.이어쓰기) */
    public int registMember(Member member) {
        MyObjectOutput moo = null;
        try {
            moo = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream("src/main/java/db/memberDB.dat", true)));

            /* 설명. 파일로 객체 하나 출력하기 */
            moo.writeObject(member);

            /* 설명. repository의 memberList에도 추가 */
            memberList.add(member);

            moo.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(moo != null) moo.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return 1;
    }

    public int deleteMember(int memNo) {
        for (int i = 0; i < memberList.size(); i++) {
            if(memberList.get(i).getMemNo() == memNo) {     // 지울려는 회원과 같은 회원 번호인 index 찾기

                /* 설명. 프로그램 상에서 회원을 관리하는 memberList 뿐 아니라 DB(회원 파일)도 같이 적용되게 함 */
                memberList.remove(i);
                saveMembers(memberList);
                return 1;
            }
        }
        return 0;
    }

    public void checkPwd(String pwd) throws Exception{
        String pwdPattern = "^[a-zA-Z0-9!~@#$%^&*]+$";
        if(Pattern.matches(pwdPattern,pwd)) {
            System.out.println("올바른 비밀번호 형식입니다.");
        } else {
            throw new Exception("비밀번호 형식이 잘못되었습니다.\n비밀번호는 영어 소/대문자와 숫자,특수기호(~,!,@,#,$,%,^,&,*)만 입력가능합니다.");
        }
    }

    public void checkID(String id) throws Exception{
        String idPattern = "^[a-z0-9]+$";
        if(Pattern.matches(idPattern,id)) {
            System.out.println("올바른 아이디 형식입니다.");
        } else {
            throw new Exception("아이디 형식이 잘못되었습니다.\n아이디는 영어 소문자와 숫자로만 입력가능합니다.");
        }
    }

    public String inputId() {
        System.out.print("아이디를 입력해주세요: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        try {
            checkID(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputId();
        }
        return id;
    }

    public String inputPwd() {
        System.out.print("비밀번호를 입력해주세요: ");
        Scanner sc = new Scanner(System.in);
        String pwd = sc.nextLine();
        try {
            checkPwd(pwd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputPwd();
        }
        return pwd;
    }
}

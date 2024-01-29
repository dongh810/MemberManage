package aggregate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Member implements Serializable {
    private int memNo;              // 회원번호
    private String id;              // 회원아이디
    private String pwd;             // 회원비번
    private String name;            // 이름
    private String phoneNum;        // 전화번호
    private String email;           // 이메일
    private String personNum;       // 주민번호
    private int age;                // 회원나이
    private String[] hobbies;       // 회원취미들
    private BloodType bloodType;    // 혈액형
    private Mbti mbti;              // mbti

    /* 설명. 엔티티 클래스는 setter를 꼭 필요한 것만 만든다. */

    public Member() {
    }
//    public Member(String id, String pwd, int age, String[] hobbies) {
//        this.id = id;
//        this.pwd = pwd;
//        this.age = age;
//        this.hobbies = hobbies;
//    }


//    public Member(String name, int age, String[] hobbies) {
//        this.name = name;
//        this.age = age;
//        this.hobbies = hobbies;
//    }

    public Member(String id, String pwd, String phoneNum, String email, String personNum) {
        this.id = id;
        this.pwd = pwd;
        this.phoneNum = phoneNum;
        this.email = email;
        this.personNum = personNum;
    }
    //    public Member(int memNo, String id, String pwd, int age, String[] hobbies, BloodType bloodType) {
//        this.memNo = memNo;
//        this.id = id;
//        this.pwd = pwd;
//        this.age = age;
//        this.hobbies = hobbies;
//        this.bloodType = bloodType;
//    }

    public Member(int memNo, String id, String pwd, String name, String phoneNum, String email, String personNum, int age, String[] hobbies, BloodType bloodType, Mbti mbti) {
        this.memNo = memNo;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.personNum = personNum;
        this.age = age;
        this.hobbies = hobbies;
        this.bloodType = bloodType;
        this.mbti = mbti;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public void setMemNo(int memNo) {
        this.memNo = memNo;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setMbti(Mbti mbti) {this.mbti = mbti;}

    public int getMemNo() {
        return memNo;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonNum() {
        return personNum;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Mbti mbti() {return mbti;}

    @Override
    public String toString() {
        return "Member{" +
                "memNo=" + memNo +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", personNum='" + personNum + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", bloodType=" + bloodType +
                ", mbti =" + mbti +
                '}';
    }

    public boolean checkPwd(String pwd) {
        String pwdPattern = "^[a-zA-Z0-9!~@#$%^&*]+$";
        if(Pattern.matches(pwdPattern,pwd)) {
            System.out.println("올바른 비밀번호 형식입니다.");
            return true;
        } else {
            System.out.println("비밀번호 형식이 잘못되었습니다.\n비밀번호는 영어 소/대문자와 숫자,특수기호(~,!,@,#,$,%,^,&,*)만 입력가능합니다.");
             return false;
        }
    }

    public boolean checkID(String id) {
        String idPattern = "^[a-z0-9]+$";
        if(Pattern.matches(idPattern,id)) {
            System.out.println("올바른 아이디 형식입니다.");
            return true;
        } else {
            System.out.println("아이디 형식이 잘못되었습니다.\n아이디는 영어 소문자와 숫자로만 입력가능합니다.");
            return false;
        }
    }

    public boolean checkPhoneNum(String phoneNum) {
        String phoneNumPattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
        if(Pattern.matches(phoneNumPattern,phoneNum)) {
            System.out.println("올바른 전화번호 형식입니다.");
            return true;
        } else {
            System.out.println("전화번호 형식이 잘못되었습니다.");
            return false;
        }
    }

    public boolean checkPersonNum(String personNum) {
        String personNumPattern = "\\d{6} \\- [1-4]\\d{6}";
        if(Pattern.matches(personNumPattern,personNum)) {
            System.out.println("올바른 주민번호 형식입니다.");
            return true;
        } else {
            System.out.println("주민번호 형식이 잘못되었습니다.");
            return false;
        }
    }

    public boolean checkEmail(String email) {
        String emailPattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        if(Pattern.matches(emailPattern,email)) {
            System.out.println("올바른 이메일 형식입니다.");
            return true;
        } else {
            System.out.println("이메일 형식이 잘못되었습니다.");
            return false;
        }
    }


}

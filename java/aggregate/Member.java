package aggregate;

import java.io.Serializable;
import java.util.Arrays;

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

    public Member(String id, String pwd, String name, String phoneNum, String email, String personNum, int age, String[] hobbies) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.personNum = personNum;
        this.age = age;
        this.hobbies = hobbies;
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
}

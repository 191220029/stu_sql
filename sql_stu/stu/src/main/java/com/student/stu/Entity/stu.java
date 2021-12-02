package com.student.stu.Entity;


import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="stu2")
public class stu {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "grade")
    private String grade;
    @Column(name = "class")
    private String sclass;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private String gender;

    public stu() {
    }

    public stu(Integer id, String name, String grade, String sclass, Integer age, String gender){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.gender = gender;
        this.sclass = sclass;
        this.age =age;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "stu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", sclass='" + sclass + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public stu(Integer id){
        String familyName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻水云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳鲍史唐费岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅卞齐康伍余元卜顾孟平"
                + "黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计成戴宋茅庞熊纪舒屈项祝董粱杜阮席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田胡凌霍万柯卢莫房缪干解应宗丁宣邓郁单杭洪包诸左石崔吉"
                + "龚程邢滑裴陆荣翁荀羊甄家封芮储靳邴松井富乌焦巴弓牧隗山谷车侯伊宁仇祖武符刘景詹束龙叶幸司韶黎乔苍双闻莘劳逄姬冉宰桂牛寿通边燕冀尚农温庄晏瞿茹习鱼容向古戈终居衡步都耿满弘国文东殴沃曾关红游盖益桓公晋楚闫";
        String firstName2 = "欧阳太史端木上官司马东方独孤南宫万俟闻人夏侯诸葛尉迟公羊赫连澹台皇甫宗政濮阳公冶太叔申屠公孙慕容仲孙钟离长孙宇文司徒鲜于司空闾丘子车亓官司寇巫马公西颛孙壤驷公良漆雕乐正宰父谷梁拓跋夹谷轩辕令狐段干百里呼延东郭南门羊舌微生公户公玉公仪梁丘公仲公上公门公山公坚左丘公伯西门公祖第五公乘贯丘公皙南荣东里东宫仲长子书子桑即墨达奚褚师吴铭";
        String girlName = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽";
        String boyName = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
        List<String> grades = Arrays.asList("初一", "初二", "初三");

        String surName = "";
        Integer r = new Random().nextInt(10) + 1;
        if(r > 1){
            //single surName
            r = new Random().nextInt(familyName.length());
            surName += String.valueOf(familyName.charAt(r));
        }
        else {
            //double surName
            r = new Random().nextInt(firstName2.length() / 2);
            surName += firstName2.substring(r * 2, r * 2 + 1);
        }

        String gender;
        r = new Random().nextInt(2) + 1;
        gender = r==1?"男":"女";

        String name = "";
        r = new Random().nextInt(10) + 1;
        if(r >= 4){
            //double name
            if(gender.equals("男")){
                r = new Random().nextInt(boyName.length() / 2);
                name += boyName.substring(r * 2, r * 2 + 1);
            }
            else {
                r = new Random().nextInt(girlName.length() / 2);
                name += girlName.substring(r * 2, r * 2 + 1);
            }
        }
        else {
            //single name
            if(gender.equals("男")){
                r = new Random().nextInt(boyName.length());
                name += boyName.substring(r, r + 1);
            }
            else {
                r = new Random().nextInt(girlName.length());
                name += boyName.substring(r, r + 1);
            }
        }


        String grade;
        r = new Random().nextInt(3);
        grade = grades.get(r);

        Integer age;
        age = new Random().nextInt(2) + r + 14;

        String sclass;
        r = new Random().nextInt(5) + 1;
        sclass = r.toString();

        this.id = id;
        this.name = surName + name;
        this.grade = grade;
        this.sclass = sclass;
        this.gender = gender;
        this.age = age;

    }
}

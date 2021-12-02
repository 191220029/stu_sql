package com.student.stu.Controler;

import com.student.stu.Entity.threadGenStu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MyHtml {
    @GetMapping("/student")
    public String Bmap1(){
        return "/web/student.html";
    }
    @GetMapping("/")
    public String Bmap2(){
        return "/web/login.html";
    }



    @GetMapping("/MultiThread")
    public String Bmap3(){
        //List<threadGenStu> threads = new LinkedList<threadGenStu>();
        Integer num = 100;
        Integer res = 0;
        Instant t1 = Instant.now();
        for(Integer i = 0; i < num; i ++){
            threadGenStu t = new threadGenStu("thread" + i);
            t.run();
            res += t.getRes();
        }
        Instant t2 = Instant.now();
        System.out.println("MutlThread Time:" + Duration.between(t1, t2).toMillis());
        System.out.println("MutlThread Res:" + res);

        res = 0;
        t1 = Instant.now();
        for(Integer i = 0; i < 10000 * num; i++){
            res++;
        }
        t2 = Instant.now();
        System.out.println("SinThread Time:" + Duration.between(t1, t2).toMillis());
        System.out.println("SinThread Res:" + res);
        return "/web/blank.html";
    }

    @GetMapping("/downloadStu")
    public String Bmap4(){
        return "/web/downloadStu.html";
    }

}

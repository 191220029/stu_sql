package com.student.stu.Controler;

import com.student.stu.Entity.stu;
import com.student.stu.Entity.user;
import com.student.stu.Repository.SRepository;
import com.student.stu.Repository.URepository;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@RestController
public class SController {
    @Autowired
    SRepository sRepository;



    @GetMapping("/all")
    public List<stu> getEmp(){
        return sRepository.findAll();
    }

    public String genStuFile(List<stu> list, String path) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)16);

        HSSFSheet sheet = workbook.createSheet("Sheet1");
        //System.out.println(list.toString());


        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFRow head = sheet.createRow(0);
        head.setHeight((short)500);
        HSSFCell cell = head.createCell(0);
        cell.setCellValue("学生名单");

        HSSFCellStyle hStyle = workbook.createCellStyle();
        hStyle.setAlignment(HorizontalAlignment.CENTER);
        hStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        hStyle.setFont(font);
        cell.setCellStyle(hStyle);


        HSSFFont cFont = workbook.createFont();
        cFont.setFontHeightInPoints((short)12);
        cFont.setFontName("宋体");
        style.setFont(cFont);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        head = sheet.createRow(1);
        head.setHeight((short)400);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        for(Integer j = 0; j < 6; j++){
            cell = head.createCell(j);
            switch (j){
                case 0:cell.setCellValue("学号"); break;
                case 1:cell.setCellValue("姓名"); break;
                case 2:cell.setCellValue("年级"); break;
                case 3:cell.setCellValue("班级"); break;
                case 4:cell.setCellValue("年龄"); break;
                case 5:cell.setCellValue("性别"); break;
            }
            cell.setCellStyle(style);
        }

        for(Integer i = 0; i < list.size(); i++){
            HSSFRow row = sheet.createRow(i + 2);
            row.setHeight((short)400);
            for(Integer j = 0; j < 6; j++){
                cell = row.createCell(j);
                switch (j){
                    case 0:cell.setCellValue(list.get(i).getId()); break;
                    case 1:cell.setCellValue(list.get(i).getName()); break;
                    case 2:cell.setCellValue(list.get(i).getGrade()); break;
                    case 3:cell.setCellValue(list.get(i).getSclass()); break;
                    case 4:cell.setCellValue(list.get(i).getAge()); break;
                    case 5:cell.setCellValue(list.get(i).getGender()); break;
                }
                cell.setCellStyle(style);
            }
        }

        FileOutputStream out = new FileOutputStream(path);
        workbook.write(out);
        out.close();

        return "ok";
    }

    /* HSSFCellStyle setalignment(HSSFCellStyle hStyle){

    }*/

    @GetMapping("/genStuFile")
    public String genAllStuFile() throws IOException {
        List<stu> list = getEmp();
        String path = "E:/Java Project/sql_stu/Files/StuFile.xlsx";
        return genStuFile(list, path);
    }

    @GetMapping("/genStuPage/startPage={startPage}&pSize={pSize}")
    public String genStuPageFile(
            @PathVariable("startPage") Integer startPage,
            @PathVariable("pSize") Integer pSize
    ) throws IOException {
        List<stu> list = getPage(startPage, pSize);
        String path = "E:/Java Project/sql_stu/Files/StuFile.xlsx";
        return genStuFile(list, path);
    }

    @GetMapping("/searchById={id}")
    public stu getS(@PathVariable("id") Integer id){
        stu s = sRepository.findById(id).get();
        return s;
    }

    @GetMapping("/searchByName={name}")
    public List<stu> getS(@PathVariable("name") String name){
        List<stu> list = sRepository.findByName(name);
        return list;
    }

    @GetMapping("/searchByGradeAndClass=grade={grade}&class={sclass}")
    public List<stu> getS(@PathVariable("grade") String grade, @PathVariable("sclass") String sclass){
        List<stu> list = sRepository.findByGradeAndSclass(grade, sclass);
        return list;
    }

    @GetMapping("/deleteByID/{id}")
    public boolean deleteById(@PathVariable("id") Integer id){
        if(sRepository.existsById(id)){
            stu s = sRepository.findById(id).get();
            sRepository.delete(s);
            return true;
        }
        return false;
    }

    @GetMapping("add=id={id}&name={name}&grade={grade}&class={class}&age={age}&gender={gender}")
    public stu add(@PathVariable("id") Integer id,
                       @PathVariable("name") String name,
                       @PathVariable("grade") String grade,
                       @PathVariable("class") String sclass,
                       @PathVariable("age") Integer age,
                       @PathVariable("gender") String gender
    ){
        stu s = new stu(id, name, grade, sclass, age, gender);
        sRepository.save(s);
        return s;
    }

    @GetMapping("stuTotalNum")
    public  Integer stuTotalNum(){
        return sRepository.getRCount();
    }

    @GetMapping("getPage/startPage={startPage}&pSize={pSize}")
    public List<stu> getPage(
            @PathVariable("startPage") Integer startPage,
            @PathVariable("pSize") Integer pSize
    ){
        Page<stu> page = sRepository.findAll(PageRequest.of(startPage, pSize));
        List<stu> list = page.getContent();
        return list;
    }

    @Autowired
    URepository uRepository;
    @GetMapping("FindUsers/username={username}&password={password}")
    public String FindUser(
            @PathVariable("username") String username,
            @PathVariable("password") String password
    ){
        System.out.println("username=" + username + " ,password =" + password);
        user u = uRepository.findByName(username);
        //List<user> list = uRepository.findAll();
        //System.out.println(list.get(0).toString());
        if(u != null){
            if(username.equals(u.getUsername())) {
                if(password.equals(u.getPassword())) {
                    if (u.getGroup().equals("admin"))
                        return "admin";
                    else
                        return "user";
                }
                return "password failed";
            }
            //System.out.println(u.toString());
        }
        return "username failed";

    }

    @RequestMapping("login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
            ){
        System.out.println("username=" + username + " ,password =" + password);
        user u = uRepository.findByName(username);
        //List<user> list = uRepository.findAll();
        //System.out.println(list.get(0).toString());
        if(u != null){
            if(username.equals(u.getUsername())) {
                if(password.equals(u.getPassword())) {
                    if (u.getGroup().equals("admin")){
                        //System.out.println("return admin");
                        return "admin";
                    }
                    else
                        return "user";
                }
                return "password failed";
            }
            //System.out.println(u.toString());
        }
        return "username failed";
    }

    @GetMapping("RandomGenStu/num={num}")
    public List<stu> RGStu(
            @PathVariable("num") Integer num
    ){
        List<stu> list = sRepository.findAll();
        List<stu> newList = new LinkedList<>();
        Integer startid = list.size() + 1;
        for(Integer i = startid; i < startid + num; i++){
            stu s = new stu(i);
            newList.add(s);
            //sRepository.save(s);
        }
        Instant t1 = Instant.now();
        sRepository.saveAll(newList);
        Instant t2 = Instant.now();
        System.out.println(Duration.between(t1, t2).toMillis());
        return newList;

    }

}

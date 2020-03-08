package com.cun.app.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cun.app.entity.Student;
import com.cun.app.service.QuestionService;
import com.cun.app.service.StudentService;
import com.cun.app.vo.QuestionStudentVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
@EnableSwagger2
public class CommonController {

    @Autowired
    QuestionService questionService;

    @Autowired
    StudentService studentService;

    /**
     * @param page
     * @param size
     * @return
     *
     */
    @GetMapping("/getAllQuestionByPage/{page}/{size}")
    public Map<String, Object> getAllQuestionByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();

        List<Student> students = studentService.list(null);
        for (Student student1 : students) {
            if (StringUtils.isBlank(student1.getNum())){
                student1.setNum(student1.getTrueName());
            }
        }
        System.out.println(students);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.set("true_name","tesss");
        studentService.updateBatchById(students);

        Page<QuestionStudentVO> questionPage = questionService.getQuestionStudent(new Page<>(page, size));
        if (questionPage.getRecords().isEmpty()) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionPage);
        }
        return map;
    }


    /**
     * @param size
     * @return
     */
    @GetMapping("/getAllQuestionWithStudentByPage/{page}/{size}")
    public Map<String, Object> getAllQuestionWithStudentByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        Page<QuestionStudentVO> questionStudent = questionService.getQuestionStudent(new Page<>(page, size));
        if (questionStudent.getRecords().isEmpty()) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionStudent);
        }
        return map;
    }

}

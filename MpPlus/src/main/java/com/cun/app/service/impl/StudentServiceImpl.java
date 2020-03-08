package com.cun.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cun.app.entity.Student;
import com.cun.app.mapper.StudentMapper;
import com.cun.app.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linhongcun
 * @since 2018-08-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}

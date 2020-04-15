package com.fhdone.paper2019.controller;

import com.fhdone.paper2019.model.Student;
import com.fhdone.paper2019.service.IStudentService;
import com.fhdone.paper2019.util.LogUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private Logger logger = LogManager.getLogger(StudentController.class);

    @Autowired
	IStudentService  studService ;

	@RequestMapping("/list")
	public  Mono<String>  getAllStudents(
            @RequestParam(value="pn",defaultValue="1")Integer pn,
            Model model){
		LogUtils.logip();
		logger.info("StudentController getAllStudents");
		//在查询之前使用分页   pn第几页 每页10条
		PageHelper.startPage(pn, 10);
		List<Student> students=studService.getAllStudents();
		//可以使用PageInfo对结果集合进行包装 直接把pageInfo交给前端页面
		//第二个参数表示连续显示的页数 5页
		PageInfo<Student> pageInfo = new PageInfo<Student>(students,10);
		//model.addAttribute("studs", students);
		model.addAttribute("pageInfo", pageInfo);

		return Mono.just("list") ;
	}

//    @RequestMapping("/queryStuByPage")
//    @ResponseBody
//    public  PageInfo  queryStuByPage(
//            @RequestParam(value="offset",defaultValue="0")Integer offset,
//            @RequestParam(value="limit",defaultValue="0")Integer limit,
//            Model model){
//
//	    int pageNum = 1;
//	    if(offset!=0 && limit!=0 ){
//            pageNum = offset/limit+1;
//        }
//        PageHelper.startPage(pageNum, limit);
//        List<Student> students=studService.getAllStudents();
//        PageInfo<Student> pageInfo = new PageInfo<Student>(students,10);
//        return pageInfo;
//    }

    @RequestMapping("/queryStuByPage")
    @ResponseBody
    public Mono<PageInfo>  queryStuByPage(
            @RequestParam(value="offset",defaultValue="0")Integer offset,
            @RequestParam(value="limit",defaultValue="0")Integer limit,
            Model model){

	    int pageNum = 1;
	    if(offset!=0 && limit!=0 ){
            pageNum = offset/limit+1;
        }
        PageHelper.startPage(pageNum, limit);
        List<Student> students=studService.getAllStudents();
        PageInfo<Student> pageInfo = new PageInfo<Student>(students,10);
        return Mono.just(pageInfo);
    }


	@RequestMapping("/delById")
	public  String  delStudents (@RequestParam(value="id")Long id ){
		studService.deleteByPrimaryKey(id);
		return "redirect:/student/list";
	}


}

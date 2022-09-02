package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //자동으로 스프링 빈으로 등록
            //애노테이션 기반 컨트롤러로 인식
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {//메소드 이름은 아무거나 정해도 됨
        return new ModelAndView("new-form");
    }
}

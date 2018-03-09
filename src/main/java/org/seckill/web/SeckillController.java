package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller开发中的方法完全是对照Service接口方法进行开发的，
 * 第一个方法用于访问我们商品的列表页，
 * 第二个方法访问商品的详情页，
 * 第三个方法用于返回一个json数据，数据中封装了我们商品的秒杀地址
 * 第四个方法用于封装用户是否秒杀成功的信息，
 * 第五个方法用于返回系统当前时间。
 */

@Controller
@RequestMapping("/seckill")//url:模块/资源/{}/细分
public class SeckillController
{

    @Autowired
    private SeckillService seckillService;

    //获取列表页
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model) //model存放数据 list.jsp+model=ModelAndView 建议用String+Model=ModelAndView
    {


        List<Seckill> list=seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";
    }
    //获取详情页
    //带{xxx}占位符的URL,@pathVariable绑定参数传入到URL中
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model)
    {
        if (seckillId == null)
        {
            return "redirect:/seckill/list";//重定向
        }

        Seckill seckill=seckillService.getById(seckillId);//通过id获取详情
        if (seckill==null) //如果用户随便传了一个id,跳转到列表页
        {
            return "forward:/seckill/list"; //请求转发
        }

        model.addAttribute("seckill",seckill); //将商品展示到detail详情页

        return "detail";
    }

    // 返回json数据,暴露秒杀接口的方法
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody  /* 该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式(json,xml)后，写入到Response对象的body数据区。*/
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId)
    {
        SeckillResult<Exposer> result;
        try{

            //获得new Exposer(true, md5, seckillId);
            Exposer exposer=seckillService.exportSeckillUrl(seckillId);
            result=new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e)
        {
            e.printStackTrace();
            result=new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "userPhone",required = false) Long userPhone)// required=false 不强制有cookie,如果没有cookie也能进入这个方法
    {
        if (userPhone==null)
        {
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution> result;

        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true, execution);
        }catch (RepeatKillException e1)
        {
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e2)
        {
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
        catch (Exception e)
        {
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,execution);
        }

    }

    //获取系统时间
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time()
    {
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
























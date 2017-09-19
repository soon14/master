package com.jy.resolver;

import com.alibaba.fastjson.JSON;
import com.jy.common.ajax.AjaxRes;
import com.jy.common.exception.DaysBaseException;
import com.jy.common.msg.RetMsg;
import com.jy.common.utils.base.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yutao on 2017/4/20.
 */
public class CommonExceptionResolver extends AbstractHandlerExceptionResolver {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        String viewName = "index";
        if(ex instanceof org.springframework.security.core.AuthenticationException){
            viewName = "/user/login";
        } else if (ex instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
            viewName = "error/maxUploadExceeded";
        }else if (ex instanceof DaysBaseException){
            viewName = "jsonMsg";
        }

        logger.debug("doResolveException");
        if (ex instanceof DaysBaseException){
            RetMsg retMsg = ((DaysBaseException) ex).getRegMsg();
            if(retMsg.getCode()== Const.NO_AUTHORIZED){


//                    httpServletResponse.sendRedirect("http://localhost:8092/backstage/noAuthorized");
                    httpServletResponse.setStatus(200);
//                    httpServletResponse.sendRedirect("/backstage/noAuthorized");
                    ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("system/noAuthorized");
//                    modelAndView.setViewName("redirect:/backstage/system/noAuthorized");

                return modelAndView;
            }
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setFailMsg(retMsg.getMsg());
            ajaxRes.setRes(retMsg.getCode());

            ajaxRes.setObj(retMsg.getMap());


            try {
                httpServletResponse.getWriter().print(JSON.toJSONString(ajaxRes));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            if(retMsg != null) {
//                mv.addObject("retMsg", retMsg);
//                String mapString = objectToJson(retMsg.getMap());
//                if(mapString != null&&!mapString.trim().isEmpty()) {
//                    mapString = mapString.substring(1,mapString.length()-2);
//                    mv.addObject("map", mapString);
//                }
//            }
        }
        //FIXME:如无特殊情况，都可以返回200
        httpServletResponse.setStatus(200);

        return null;
    }

    private String objectToJson(Object obj){
        if (obj == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        if(obj instanceof Map){
            Map<Object,Object> map = (Map)obj;
            sb.append("{");

            for (Map.Entry entry : map.entrySet()) {
                sb.append("\"");
                sb.append(entry.getKey().toString());
                sb.append("\":");
                sb.append(objectToJson(entry.getValue()));
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("},");
        }else if(obj instanceof List){
            List<Object> list = (List<Object>) obj;
            sb.append("[");
            for (Object o : list) {
                if(!(o instanceof Map)){
                    return "";
                }
                sb.append(objectToJson(o));
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("],");
        }else{
            sb.append("\"");
            sb.append(obj.toString());
            sb.append("\",");
        }
        return sb.toString();
    }
}

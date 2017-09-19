package com.days.webservice.direct;


import com.jy.vo.webService.FileWithdrawInfoDTO;
import com.jy.vo.webService.ResultData;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


/**
 * Created by baowei on 2016/4/18.
 */
@WebService
public interface CompareWebService
{

	/******查询处理文件请求******/
    @WebMethod(action = "urn:compareWebService")
    @POST
    @Path("doQueryCompareInfo")
    ResultData<FileWithdrawInfoDTO> doQueryCompareInfo(@WebParam(name = "date") String date, @WebParam(name = "srcType") String srcType);


}

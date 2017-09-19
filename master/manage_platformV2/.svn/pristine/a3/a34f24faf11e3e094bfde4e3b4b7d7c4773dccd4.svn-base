package com.days.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mpi.days.data.OrderData;
import mpi.days.exception.PayException;
import mpi.days.trans.TopPayLink;

public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     * @param url   发送请求的URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url   发送请求的 URL
     * @param param    请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //public static String createXml(String tranCode,String merchantId,String merOrderNum,String tranDateTime,String tranAmt,String customerEmail,String merBackURL,String merUrl,String productInfo,String msgExt) {
    public static String createXml() {
		StringBuilder sb=new StringBuilder();
		//组装报文
		/*sb.append("MPIReq=<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<PGGATE>");
		sb.append("<Message id=\"msg_id\">");
		sb.append("<MPIReq id=\"mpi.days.data.OrderData\">");
		sb.append("<version>20121201</version>");		
		sb.append("<tranCode>"+tranCode+"</tranCode>");
		sb.append("<merchantId>"+merchantId+"</merchantId>");
		sb.append("<parentMerchantId/>");
		sb.append("<merOrderNum>"+merOrderNum+"</merOrderNum>");
		sb.append("<tranDateTime>"+tranDateTime+"</tranDateTime>");
		sb.append("<acctType>00</acctType>");
		sb.append("<tranAmt>"+tranAmt+"</tranAmt>");
		sb.append("<feeAmt/>");
		sb.append("<rsvdAmt/>");
		sb.append("<currencyType>156</currencyType>");
		sb.append("<customerEmail>"+customerEmail+"</customerEmail>");
		sb.append("<isBackG>1</isBackG>");
		sb.append("<merUrl>"+merUrl+"</merUrl>");
		sb.append("<merBackUrl>"+merBackURL+"</merBackUrl>");
		sb.append("<tranIp/>");
		sb.append("<productInfo>"+productInfo+"</productInfo>");
		sb.append("<msgExt>"+msgExt+"</msgExt>");
		sb.append("</MPIReq>");
		sb.append("</Message>");
		sb.append("</PGGATE>");*/
		
		//测试报文
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<PlatformCode></PlatformCode>");
		sb.append("<ReqSerialNo></ReqSerialNo>");
		sb.append("<InvestorAccount></InvestorAccount>");
		sb.append("<Mobile></Mobile>");
		sb.append("<SmsMessage></SmsMessage>");
		sb.append("</xml>");
		String a = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><head><merdateTime>2015-10-10 09:00:00</merdateTime><serviceId>003</serviceId><ver>1.0</ver></head><param><platformCode>platformCode</platformCode><reqSerialNo>reqSerialNo</reqSerialNo><investorAccount>investorAccount</investorAccount><mobile>Mobile</mobile><smsMessage>SmsMessage</smsMessage></param></request>";
		//return sb.toString();
		return a;
	}
    
    
    public static String getXML() {
		//******************定义变量*************************
		//以下字段是消费交易必输字段
		//消费交易码           : 1000
		//后台查询交易码 : 4020
		//后台退款交易码 : (暂不提供，目前要求线下方式退款)
		String transCode = "1000";
		//商户代码(请填入实际分配的商户编号)
		String merId = "910151030100170";
		//String merId = "910150915100661";
		//商户订单号（每日通讯时不能重复）
		//示例时订单号采用商户代码倒数六位[040076]+时间后10位
		String sDT = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String merOrderNo = "100101"+sDT.substring(4);
		//交易时间(注意位数:14位，交易时间和得仕通系统当前时间相差不能超过2个小时)
		String tranDateTime = sDT;
		//货币代码（人民币:156）
		String currType = "156";
		//交易金额（单位：分）可参考MPI文档
		String transAmt = "101";
		
		//以下字段是消费交易可输字段
		//消费者姓名
		String cusmName = "测试用户(可修改)";
		//商品信息
		String prodInfo = "测试商户信息(可修改)";
		//附加信息
		String extMsg = "测试商户接入(可修改)";
		//客户浏览器IP
		String clientIp = "";
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			clientIp = addr.getHostAddress();//获得本机IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		//以下字段是消费交易无需输入字段
		//预授权ID
		String txnAuthId = "";
		//消费者Email
		String cusmEmail = "932074842@qq.com";
		
		OrderData orderData = new OrderData();
		orderData.setTranCode(transCode);
		orderData.setMerchantId(merId);
		orderData.setMerOrderNum(merOrderNo);
		orderData.setTranAmt(transAmt);
		orderData.setTranDateTime(tranDateTime);
		orderData.setCurrencyType(currType);
		orderData.setCustomerName(cusmName);
		orderData.setProductInfo(prodInfo);
		orderData.setMsgExt(extMsg);
		orderData.setTranIp(clientIp);
		orderData.setAuthId(txnAuthId);
		orderData.setCustomerEmail(cusmEmail);
		
		String host_pay_url = "";
		String version = "";
		String mpiReq = "";
		String termUrl = "";
		try {
			//调用MPI接口GetMPIProperties方法获取发往得仕通支付网关前端地址
			host_pay_url = TopPayLink.GetMPIProperties("HostPay.URL");
			
			//version 默认都填二代版本号2.0.0
			version = "2.0.0";
			
			//成功页面返回商户地址，此地址为商户服务器前端接收地址，需商户自行配置。
			termUrl = "http://192.168.192.105:9080/dayspay/dst/example/RecvSuccReturn.jsp";
			
			//商户后台通知接收标志，1为接收，其他为不接收
			String merBackFlag = "1";
			//支付成功后台通知地址，此地址为商户服务器后台接收地址，需商户自行配置。
			String merBackUrl = "http://192.168.192.105:9080/dayspay/dst/example/RecvBackNotice.jsp";
			
			orderData.setMerUrl(termUrl);
			orderData.setMerBackUrl(merBackUrl);
			orderData.setIsBackG(merBackFlag);
			//request.setAttribute("orderData", orderData);
			
			//调用MPI接口方法组装报文，返回经过签名和加密的XML报文.
			mpiReq = TopPayLink.PayTrans(orderData);
			
			//System.out.println("1111111111111:"+TopPayLink.DecryptedPin(mpiReq));
		} catch (PayException pe) {
			pe.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mpiReq;
	}
    
    
    
    /**
     * 测试请求
     * 
     * */
    public static void main(String[] args) {
    	String host = null;
		try {
			host = TopPayLink.GetMPIProperties("SendMSG.URL");// "http://101.231.207.206:17001/dayspay/paygate/daysPlaceOrder.do";
		} catch (Exception e) {
			e.printStackTrace();
		}  
    	String version = "2.0.0";
    	String termUrl = "./s/pay/payment/";
    	String MPIReq = createXml();
    	System.out.println("111111111111111111111:"+MPIReq);
        //发送 POST 请求
        //String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
        String sr=HttpRequest.sendPost(host, "data=" + MPIReq + "&sign=" + 1 + "");
        System.out.println("以post方式发送报文："+sr);
    }
    
}
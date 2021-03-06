package com.example.aliyun.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
    
/**
 * ljz
 */
@Controller
public class PayController {
    private final String APP_ID = "2021000117628019";
    private final String APP_PRIVATE_KEY ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEFZq1W4V/fTqBLh+uTj7RQos0GuQut3/D9oxjXmehbKikotVQqwVx+fBGuz85c+OgP2DsCvd8EJ4eDQ3Ru53B+YrfDPFYiydWZTyjcqtrxxpc/+BTMBvHpDiauQxlhGXs2woiQoevw7U2/kXGWs+dKevDh96Ofw3miz7r9ajmmggZfNeU18OQmQheEusaID++ibC2Zj5To7bZs8uAFBNO1F2Aa0h6zWlOcPnryfgHqnhAz2nLkdYajmO8ZiSNN+yu8eO22IyNGUQjRpVlNgr7IK2hDKXeLWVp8tGY8B39IK5DzoZ+mfpHYDKxTXX0I1n09gmAJyPsnrSQ7vpoeMEVAgMBAAECggEAUFpIRL4Jp7ZKKh4iHcryAiHGR4t4d0cgjdlmLGlZB+XvbkC1Hp778AuJ9a2h19S9jFYiI1bEmhAN8OAmIQpO/ZtOtUTNaf01kCSEUDbJPAbi7eKn2FOzAq/P06hxdyylSQMz1gW9P9sCWhMz5WiHVugSyl/Ur8FxdmgNfkRzXN0662SM8pamF5tyIh86djMoKC4p2J99Avqq+QbQpnHv4kKdXDWybKPnrFJVtKyrlaL2j1wOeRsg6mSwyFrMMXvJv5YfNC+WiEewEmkyI2sD4rIEE9JaeFZjUU0a26Ki8Wi4K7XHitLGUwzGgvodmK1fJ97flprii2PqofyDQKgrAQKBgQD7qi7S0ph+uM2i84Hox8RFzDHLp8Z9ye3a0fvZOsTMZQF+WJQs3GdE6DTpRoJviXumlZetZN9aJkoZk6FW2E4e+7ryJTlem60Jnk7D3Z0d2OISpK3ltzG1a9cnxuGDAF6l3oUsiUys2OPHU55oik2tYV+mpskLgRd5howAz5JawQKBgQCGXBVm9gqwbPbjMA/ndBDShPWEm7RpMI50NeMZBsI3vT1DoGhVq0pRXuvIYHtrexCvEft5xOPMLNarMzFam6JCSMYo27wRyaZFviOgKS57ozSDVPBj97F0x6YKvBtXTY/1OD5K8soP5mXo+2zZEGjBWnjijcn8qqTdECHsCPNfVQKBgBNhdL+pljLUGon9BLkfgT76za7oWcHgJiAe4a1F+FfhQ28udSLLSmylEUYRtwPN66bViFvDE+xGXJiBpQAiIkoCitARC8z8YiQGe/xbebkNtcNHyViSdhKJnoUirMVA5wkRDOJ0JGJ/RXNZPi7Az+CVSh90YrEvHNawBOIfGh9BAoGAZAc0K9YrORO2boc+n+sKByDThGlDVDjBJYVk0CH2//ZUIP/QDU+b4Z6QXTpvSCJz1lLF7O8HRN9w5j4TlUhe8Ygqdwq4JmoA7tVSwo9GOufTVZ8r5NDn/XARjtZiaImnDkJUJgpBIrs9zck5TRTpWGHZs5kp8oC8gpPbHVXAko0CgYBLSRFsoSFGKqvRFCfOT2ETNYnu3iVLTjQ135VHItRFvgdW6AVr+30Lfx8rceq0KLMW7j8DMzll1x2aNq9XcT65fAUTtvy74NqUGQ2prItUtnP/dEtBY1cYy9OZaKUUWDPamu9flBz//XuSrQndhXqOQRkZsZX+tSuwAqnnKmnNew==";

    private final String CHARSET = "UTF-8";



    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg+LD75DBq82bc7UpexEpGGYHAbpjiYgwlmhvC0UQBB7+qiEeeZHMaFcUIj4Wk7Lc1Xj67EOM3KMs1u/BseA/Ad39E1zOG0uTMSph9RhI/zu7igehZBG9uESjNf3055UopUr4hEeDqz6KKz6+wVXvU180pTNY+hNOmbdSmho88gtbtBQ8d9SSkgYmqwbI5kfkXqkCeivSFw/SZaCNFDlroYB76N1Zyg3yhrUurB4C4XUUS4x5ToeyKDzTjLXB3jozC594FtBzkatEqZ63jeTCftmXMPMG+CSoDA+sXDVB5UYrrMTPMNKpH1QoXbXr4jOhV4fap/NJH0RHTAJQIJGwmwIDAQAB";

    //????????????????????????,???????????????https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";

    //????????????
    private final String SIGN_TYPE = "RSA2";

    //???????????????????????????,????????????????????????????????????????????????,?????????????????????
    private final String NOTIFY_URL = "http://????????????/notifyUrl";

    //???????????????????????????,???????????????????????????????????????????????????,????????????????????????
    private final String RETURN_URL = "http://localhost:8080/returnUrl";

    @RequestMapping("alipay")
    public void alipay(@RequestParam(required = false) String money, HttpServletResponse httpResponse) throws IOException {
        money=money==null?"1":money;
        //??????????????????,??????????????????
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        //?????????????????????????????????????????????
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        Random random=new Random();

        request.setBizContent("{\"out_trade_no\":\""+ random.nextInt(100000) +"\","
                + "\"total_amount\":\""+money+"\","
                + "\"subject\":\"?????????\","
                + "\"body\":\" Android \","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            // ??????SDK????????????
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// ????????????????????????html???????????????
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }



    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public String returnUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException {
        System.out.println("=================================????????????=====================================");

        // ???????????????GET??????????????????
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // ???????????????????????????????????????????????????
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);
        boolean signVerified =  AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "utf-8", "RSA2");

        // ????????????????????????????????????????????????????????????????????????
        if (signVerified) {
            // ???????????????
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // ??????????????????
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // ????????????
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("???????????????="+out_trade_no);
            System.out.println("??????????????????="+trade_no);
            System.out.println("????????????="+total_amount);
            request.setAttribute("out_trade_no",out_trade_no);
            request.setAttribute("trade_no",trade_no);
            request.setAttribute("total_amount",total_amount);
            return "Payment";
        }
        request.setAttribute("error","error");
        return "Payment";
    }

    @RequestMapping("/index")
    public String p(){
        return "index";
    }

}

package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testBase64 {

    public static void main(String[] args) {
//        testTrim();
        String str = "{\"sendno\":0,\"msg_id\":\"58546797838450208\",\"message\":\"推送成功\",\"error\":null}";
        JSONObject jsonObject = JSON.parseObject(str);
        String erroe = jsonObject.getString("erroe");
        if(erroe==null){
            System.out.println(erroe);
        }
    }

    private static void urlSubstr() {
        String str = "https://test.unicoin.top:8090/view/article.html?id=1024";
        if(str.startsWith("http")){
            String[] end = str.split("/");
            String newstr = "/";
            for (int i = 3; i <end.length ; i++) {
                newstr += end[i];
                if(i!=end.length-1){
                    newstr += "/";
                }

            }
            System.out.println(newstr);
        }
    }

    private static void testTrim(){
        String str = "								10 月 15 日早间，Bitfinex 出现 BTC 溢价，下午 3 时出现 USDT 大规模恐慌性抛售，其形式演变为：质押 USDT——>兑换为 BTC——>离场。 ";
        str=str.trim();
        System.out.println(str);
    }

}

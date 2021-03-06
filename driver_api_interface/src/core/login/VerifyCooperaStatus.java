package core.login;


import com.letvyidao.init.Constant;
import com.letvyidao.utils.DriverInfo;
import com.letvyidao.utils.GetOuthToken;
import com.letvyidao.utils.HttpUtils;
import com.letvyidao.utils.RequestHeaderConfig;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongche on 16/11/1.
 */
public class VerifyCooperaStatus {
    public RequestHeaderConfig headerConfig = new RequestHeaderConfig();
    public HttpUtils httpUtils;
    public DriverInfo driverInfo;
    public Map<String, String> params;
    public String url;
    public Map<String, String> headers;
    public String response;

    public VerifyCooperaStatus() {
        headers = headerConfig.getHeaderMapWithToken();
        httpUtils = HttpUtils.getIntance();
        driverInfo = new DriverInfo().getDiverInfoAnd();
        params = new HashMap<>();
        url = Constant.api_VerifyCooperaStatus;


    }

    @BeforeMethod
    public void init() {
        params.put("cellphone", "16820160315");
        params.put("vehicle_number", "0315");;
        params.put("area_code", "86");
        params.put("imei", "358182063039758");
        params.put("version", "96");
        params.put("x_auth_mode", driverInfo.x_auth_mode);
        params.put("is_gzip", driverInfo.is_zip);

    }

    @Test(description = "获取司机验证码(V5.0.1版本以前)")
    public void test_verifyCooperaStatus() {
        response=httpUtils.doSendGetOAuth(url + "?" + HttpUtils.getPostParameter(params) + headerConfig, headers).toString();
        JSONObject jsonObject = JSONObject.fromString(response);
        System.out.println(response);
        Assert.assertEquals(jsonObject.getInt("code"), 200, response + "---" + "参数如下：" + HttpUtils.getPostParameter(params));

    }


}

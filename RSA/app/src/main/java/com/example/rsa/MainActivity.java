package com.example.rsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import com.example.rsa.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1, btn2;// 加密，解密
    private EditText et1, et2, et3;// 需加密的内容，加密后的内容，解密后的内容

    /* 密钥内容 base64 code */
   /* private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfRTdcPIH10gT9f31rQuIInLwe"
            + "\r" + "7fl2dtEJ93gTmjE9c2H+kLVENWgECiJVQ5sonQNfwToMKdO0b3Olf4pgBKeLThra" + "\r"
            + "z/L3nYJYlbqjHC3jTjUnZc0luumpXGsox62+PuSGBlfb8zJO6hix4GV/vhyQVCpG" + "\r"
            + "9aYqgE7zyTRZYX9byQIDAQAB" + "\r";
    private static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ9FN1w8gfXSBP1/"
            + "\r" + "fWtC4gicvB7t+XZ20Qn3eBOaMT1zYf6QtUQ1aAQKIlVDmyidA1/BOgwp07Rvc6V/" + "\r"
            + "imAEp4tOGtrP8vedgliVuqMcLeNONSdlzSW66alcayjHrb4+5IYGV9vzMk7qGLHg" + "\r"
            + "ZX++HJBUKkb1piqATvPJNFlhf1vJAgMBAAECgYA736xhG0oL3EkN9yhx8zG/5RP/" + "\r"
            + "WJzoQOByq7pTPCr4m/Ch30qVerJAmoKvpPumN+h1zdEBk5PHiAJkm96sG/PTndEf" + "\r"
            + "kZrAJ2hwSBqptcABYk6ED70gRTQ1S53tyQXIOSjRBcugY/21qeswS3nMyq3xDEPK" + "\r"
            + "XpdyKPeaTyuK86AEkQJBAM1M7p1lfzEKjNw17SDMLnca/8pBcA0EEcyvtaQpRvaL" + "\r"
            + "n61eQQnnPdpvHamkRBcOvgCAkfwa1uboru0QdXii/gUCQQDGmkP+KJPX9JVCrbRt" + "\r"
            + "7wKyIemyNM+J6y1ZBZ2bVCf9jacCQaSkIWnIR1S9UM+1CFE30So2CA0CfCDmQy+y" + "\r"
            + "7A31AkB8cGFB7j+GTkrLP7SX6KtRboAU7E0q1oijdO24r3xf/Imw4Cy0AAIx4KAu" + "\r"
            + "L29GOp1YWJYkJXCVTfyZnRxXHxSxAkEAvO0zkSv4uI8rDmtAIPQllF8+eRBT/deD" + "\r"
            + "JBR7ga/k+wctwK/Bd4Fxp9xzeETP0l8/I+IOTagK+Dos8d8oGQUFoQJBAI4Nwpfo" + "\r"
            + "MFaLJXGY9ok45wXrcqkJgM+SN6i8hQeujXESVHYatAIL/1DgLi+u46EFD69fw0w+" + "\r" + "c7o0HLlMsYPAzJw="
            + "\r";*/


     private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQPSJKjW9F/ksJgZX+jOiUnz8dfLLeHhnePFItfc1NF5Q5KzsBna/XJR2/PxDmbnboBxHJSX57S4pUn5FFAuE2eeS4D7mcbckGOLsWQlLJW1OCjSDAAbh29+yUvZ/rbysKaIjP/prwH3K2afDwby7kbEKJbPYLh5XZub2FA+OurQIDAQAB";
    private static String PRIVATE_KEY = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBANA9IkqNb0X+SwmBlf6M6JSfPx18st4eGd48Ui19zU0XlDkrOwGdr9clHb8/EOZudugHEclJfntLilSfkUUC4TZ55LgPuZxtyQY4uxZCUslbU4KNIMABuHb37JS9n+tvKwpoiM/+mvAfcrZp8PBvLuRsQols9guHldm5vYUD466tAgMBAAECgYEAwzILCsc5HusXe3m1GD8NZQNBLmrNX9fnd6IjhfD+mN0ZW91iGkmtooZwM+zx8o5pd2XpDj2OChTtPhW1HnNslusar9nRkllMfQFzL98+1tvelmiUjh0vcQ9WyVjKFz1Ub4yC749tXI9T9IAlhKXMz99ozVzpFUTr8MnHd8Sc3SECQQDpKmHaMG6MpShCqU4A3ehbh0LK78XEkjlZwrRwKHPBFu/MORrKjPagYUva32AlGrUHnEQwTKsMqn3jYziKIgc/AkEA5KHSPWXKY0uUSyV6s75UZyfAYPdgHI4pWoWm5ZsBXfPpqrx4wJXxPg6zLz9qkhUHPKzAXM49QCPXoS2WIoabEwJBAOUClKOKKDqJyNZYld2yFxM28fqCsDtW529rFnCGutKu5Y4vlEnXVDDH/4oVFci7x4yXBj5uPx6sogLhpFm//F8CQQCOr+NrCA0AXVnmcDSc2usIGgn5OknUaHCsmv7nCw8WWLR7JvbhknKlvTzc/uH+23rRxiqjgN8/GV9oqu+pCoLbAkEAsHj9ds3bDxPM9CG+OGrlZhe3+jKLUY7gdYVqUG21SO7Y1O6O9KWKBSw5TVufLDGpruU85Q2EJs8dy4yqNwjDgA==";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView()
    {
        btn1 = (Button) findViewById(R.id.encrypt);
        btn2 = (Button) findViewById(R.id.decrypt);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            // 加密
            case R.id.encrypt:
                String source = et1.getText().toString().trim();
                try
                {
                    // 从字符串中得到公钥
                     PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
                    // 从文件中得到公钥
                    //InputStream inPublic = getResources().getAssets().open("rsa_public_key.pem");
                    //PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
                    // 加密
                    byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);
                    // 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
                    String afterencrypt = Base64Utils.encode(encryptByte);
                    et2.setText(afterencrypt);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            // 解密
            case R.id.decrypt:
                String encryptContent = et2.getText().toString().trim();
                try
                {
                    // 从字符串中得到私钥
                     PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
                    // 从文件中得到私钥
                    //InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private_key.pem");
                    //PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
                    // 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
                    byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(encryptContent), privateKey);
                    String decryptStr = new String(decryptByte);
                    et3.setText(decryptStr);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

}

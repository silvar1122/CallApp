package tz.co.callapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText et_user_id,et_user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        1187119285

//        fdaaba8d7519b0edc7f903aa58e58e373b5cff558b1374dae309a1625582d51f

        submit=findViewById(R.id.submit);
        et_user_id=findViewById(R.id.et_user_id);
        et_user_name=findViewById(R.id.et_user_name);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // StartService(et_user_id.getText().toString());
                String the_user_id=et_user_id.getText().toString();
                String the_user_name=et_user_name.getText().toString();
                if( the_user_id.isEmpty()){
                    return;
                }
                StartService(the_user_id,the_user_name);
                Intent intent=new Intent(MainActivity.this,SendCallInvitationActivity.class);
                intent.putExtra("userID",the_user_id);
                intent.putExtra("userName",the_user_name);
                startActivity(intent);

            }
        });

//

    }

    private void StartService(String the_userID,String the_username){
        Application application = getApplication(); // Android's application context
        long appID =1187119285 ;   // yourAppID
        String appSign ="fdaaba8d7519b0edc7f903aa58e58e373b5cff558b1374dae309a1625582d51f";  // yourAppSign
        String userID =the_userID; // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName =the_username;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}
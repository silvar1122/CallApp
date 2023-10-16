package tz.co.callapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.plugin.invitation.components.ZegoStartInvitationButton;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class SendCallInvitationActivity extends AppCompatActivity {

    TextView user_name;
    EditText et_receiver_id;
    ZegoSendCallInvitationButton voice_call,video_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_call_invitation);

        String userID=getIntent().getStringExtra("userID");
        user_name=findViewById(R.id.user_name);
        et_receiver_id=findViewById(R.id.et_receiver_id);
        voice_call=findViewById(R.id.voice_call);
        video_call=findViewById(R.id.video_call);

        user_name.setText(userID);

        et_receiver_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String targer_userID=et_receiver_id.getText().toString().trim();
                SetVoiceCall(targer_userID,targer_userID);
               // SetVideoCall(targer_userID,targer_userID);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

   private void SetVoiceCall(String user_ID,String user_name){
       voice_call.setIsVideoCall(false);
       voice_call.setResourceID("zego_uikit_call");
       voice_call.setInvitees(Collections.singletonList(new ZegoUIKitUser(user_ID,user_name)));
   }

    private void SetVideoCall(String user_ID,String user_name){
        voice_call.setIsVideoCall(true);
        voice_call.setResourceID("zego_uikit_call");
        voice_call.setInvitees(Collections.singletonList(new ZegoUIKitUser(user_ID,user_name)));
    }
}
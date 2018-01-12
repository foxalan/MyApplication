package com.example.mybulter.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybulter.R;
import com.example.mybulter.constant.Constant;
import com.example.mybulter.info.ChatInfo;
import com.example.mybulter.info.Type;
import com.example.mybulter.util.TimeUtil;
import com.example.mybulter.adapter.ChatAdapter;


import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;

import com.iflytek.cloud.SynthesizerListener;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {

    private ListView lv_chat;
    private ChatAdapter adapter;

    private EditText et_chat;
    private Button bt_send;

    private List<ChatInfo> chatInfoList = new ArrayList<>();

    private static final int TYPE_ROBOT = 0;
    private static final int TYPE_HUMAN = 1;

    //TTS
    private SpeechSynthesizer mTts;

    private static ChatFragment chatFragment;

    public static ChatFragment getIntance() {

        if (chatFragment == null) {
            chatFragment = new ChatFragment();
        }

        return chatFragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, null);


        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(getActivity(), null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”

        initView(view);
        initData();
        initEvent();

        return view;
    }

    private void initView(View view) {
        lv_chat = (ListView) view.findViewById(R.id.lv_chat);
        et_chat = (EditText) view.findViewById(R.id.et_chat);
        bt_send = (Button) view.findViewById(R.id.bt_chat_send);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        chatInfoList.clear();

        String content = "你好,我是智能机器人小莫,非常高兴为你服务";
        String data = TimeUtil.getCurrentTimeS();
        Type type = Type.ROBOT;
        ChatInfo chatInfo = new ChatInfo(content, data, type);
        chatInfoList.add(chatInfo);

    }

    private void initEvent() {
        adapter = new ChatAdapter(getActivity(), chatInfoList);

        lv_chat.setAdapter(adapter);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_chat.getText().toString().equals("")) {
                    return;
                }

                initUserContent();


                /**
                 * 逻辑
                 * 1.获取输入框的内容
                 * 2.判断是否为空
                 * 3.判断长度不能大于30
                 * 4.清空当前的输入框
                 * 5.添加你输入的内容到right item
                 * 6.发送给机器人请求返回内容
                 * 7.拿到机器人的返回值之后添加在left item
                 */

                //1.获取输入框的内容
                String text = et_chat.getText().toString();
                //2.判断是否为空


                if (text.length() > 30) {

                } else {

                    //6.发送给机器人请求返回内容
                    String url = "http://op.juhe.cn/robot/index?info=" + text
                            + "&key=" + Constant.CHAT_LIST_KEY;
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {

                            parsingJson(t);
                        }
                    });
                }

                et_chat.setText("");
            }
        });




    }

    //开始说话
    private void startSpeak(String text) {
        //3.开始合成
        mTts.startSpeaking(text, mSynListener);
    }

    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };

    //解析Json
    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            //拿到返回值
            String text = jsonResult.getString("text");
            startSpeak(text);
            Type user_type = Type.ROBOT;
            String user_data = TimeUtil.getCurrentTimeS();


            addToList(text, user_type, user_data);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 机器人的listView
     *
     * @param text
     * @param user_type
     * @param user_data
     */
    private void addToList(String text, Type user_type, String user_data) {

        ChatInfo chatInfo = new ChatInfo(text, user_data, user_type);
        chatInfoList.add(chatInfo);

        updateListView();

    }

    /**
     * 添加到用户对话列表
     */

    private void initUserContent() {

        String user_content = et_chat.getText().toString();
        String user_data = TimeUtil.getCurrentTimeS();
        Type user_type = Type.HUMAN;

        ChatInfo chatInfo = new ChatInfo(user_content, user_data, user_type);
        chatInfoList.add(chatInfo);

        updateListView();
    }

    /**
     * 刷新listView
     */
    private void updateListView(){
        adapter.notifyDataSetChanged();
        lv_chat.getChildAt(chatInfoList.size() - 1);
        lv_chat.smoothScrollToPosition(chatInfoList.size() - 1);

    }


}

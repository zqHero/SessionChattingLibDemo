package com.hero.zhaoq.sessionlibdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.hero.zhaoq.emotionboardlib.Constants;
import com.hero.zhaoq.emotionboardlib.EmoticonsInputBoard;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.EmotionsPageManager;
import com.hero.zhaoq.emotionboardlib.widget.EmoticonsEditText;
import com.hero.zhaoq.emotionboardlib.widget.FuncLayout;
import com.hero.zhaoq.emotionboardlib.widget.SimpleAppsGridView;
import com.sj.emoji.EmojiBean;

/**
 * 一款  简单的聊天  会话界面
 */
public class EmotionsActivity extends AppCompatActivity implements FuncLayout.OnFuncKeyBoardListener {

    private EmoticonsInputBoard ekBar;

    private EmoticonClickListener emoticonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ekBar = findViewById(R.id.ek_bar);

        initEmotionListener();
        initMView();
    }

    private void initEmotionListener() {
        //TODO  SendImage
        emoticonClickListener = new EmoticonClickListener() {
            @Override
            public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {

                if (isDelBtn) {
                    EmotionsPageManager.delClick(ekBar.getEtChat());
                } else {
                    if (o == null) {
                        return;
                    }
                    if (actionType == Constants.EMOTICON_CLICK_IMAGE) {
                        if (o instanceof EmoticonBean) {
                            //TODO  SendImage
                            Toast.makeText(EmotionsActivity.this, "图片:" + ((EmoticonBean)o).getContent(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String content = null;
                        if (o instanceof EmojiBean) {
                            content = ((EmojiBean) o).emoji;
                        } else if (o instanceof EmoticonBean) {
                            content = ((EmoticonBean) o).getContent();
                        }

                        if (TextUtils.isEmpty(content)) {
                            return;
                        }
                        int index = ekBar.getEtChat().getSelectionStart();
                        Editable editable = ekBar.getEtChat().getText();
                        editable.insert(index, content);
                    }
                }
            }
        };
    }

    /**
     * 初始化  view：
     */
    private void initMView() {
        ekBar.setAdapter(EmotionsPageManager.getCommonAdapter(this, emoticonClickListener));
        ekBar.addOnFuncKeyBoardListener(this);
        SimpleAppsGridView gridView = new SimpleAppsGridView(this);
        ekBar.addFuncView(gridView);

        ekBar.getEtChat().setOnSizeChangedListener(new EmoticonsEditText.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                scrollToBottom();
            }
        });
        //发送按钮
        ekBar.getBtnSend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mcgContent = ekBar.getEtChat().getText().toString();
                scrollToBottom();
                if (mcgContent.equals("")) {
                    return;
                }

                Toast.makeText(getApplicationContext(), "发送消息", Toast.LENGTH_SHORT).show();
            }
        });
        //切换语音输入
        ekBar.getVoiceOrText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.btn_voice_or_text) {
                    ekBar.setVideoText();
                    //语音  输入：
                }
            }
        });
    }


    private void scrollToBottom() {

    }

    @Override
    public void OnFuncPop(int height) {

    }

    @Override
    public void OnFuncClose() {

    }
}

package com.hero.zhaoq.sessionlibdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.hero.zhaoq.emotionboardlib.Constants;
import com.hero.zhaoq.emotionboardlib.EmotionsPageManager;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.widget.EmoticonsEditText;
import com.hero.zhaoq.emotionboardlib.widget.EmoticonsInputBoard;
import com.hero.zhaoq.emotionboardlib.widget.EmoticonsPageLayout;
import com.hero.zhaoq.emotionboardlib.widget.SimpleAppsGridView;
import com.hero.zhaoq.sessionlib.adapters.MessageListAdapter;
import com.hero.zhaoq.sessionlib.holder.HolderConfig;
import com.hero.zhaoq.sessionlib.listeners.ImageLoader;
import com.hero.zhaoq.sessionlib.widgets.MessageView;

/**
 * 聊天界面   crated by zq
 */
public class ChattingActivity extends AppCompatActivity implements EmoticonsPageLayout.OnFuncKeyBoardListener {

    private EmoticonsInputBoard ekBar;

    private EmoticonClickListener emoticonClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        //初始化 View:
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
                            Toast.makeText(ChattingActivity.this, "图片:" + o.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String content = null;
                        if (o instanceof EmoticonBean) {
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
                    //切换
                    ekBar.setVideoText();
                }
            }
        });

        //初始化  recycleView：
        initListView();
    }

    private void initListView() {
        // Use default layout
        MessageView messageView = findViewById(R.id.lv_chat);

        //初始化  list View布局
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadAvatarImage(ImageView avatarImageView, String string) {
                Glide.with(getApplicationContext())
                        .load(string)
                        .placeholder(R.drawable.ic_healthy_helper)
                        .into(avatarImageView);
            }
            @Override
            public void loadImage(ImageView imageView, String string) {
                Glide.with(getApplicationContext())
                        .load(string)
                        .fitCenter()
                        .placeholder(R.drawable.ic_healthy_helper)
                        .override(300, Target.SIZE_ORIGINAL)
                        .into(imageView);
            }
        };

        HolderConfig holdersConfig = new HolderConfig();
        MessageListAdapter messListAdapter = new MessageListAdapter("0", holdersConfig, imageLoader);
        messageView.setLayoutManager(new LinearLayoutManager(this));
        messageView.setAdapter(messListAdapter);
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

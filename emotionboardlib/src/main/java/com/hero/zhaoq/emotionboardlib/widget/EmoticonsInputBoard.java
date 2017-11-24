package com.hero.zhaoq.emotionboardlib.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hero.zhaoq.emotionboardlib.EmoticonsInputBoardUtils;
import com.hero.zhaoq.emotionboardlib.R;
import com.hero.zhaoq.emotionboardlib.adapter.PageSetAdapter;
import com.hero.zhaoq.emotionboardlib.entity.PageSetEntity;

import java.util.ArrayList;


/**
 * author: zhaoqiang
 * date:2017/11/21 / 11:06
 * zhaoqiang:zhaoq_hero@163.com
 */
public class EmoticonsInputBoard extends AutoHeightLayout implements View.OnClickListener, EmoticonsFuncView.OnEmoticonsPageViewListener,
        EmoticonsToolBarView.OnToolBarItemClickListener, EmoticonsEditText.OnBackKeyClickListener, EmoticonsPageLayout.OnFuncChangeListener {

    public static final int FUNC_TYPE_EMOTION = -1;
    public static final int FUNC_TYPE_APPPS = -2;

    protected LayoutInflater mInflater;

    protected ImageView mBtnVoiceOrText;
    protected RecordVoiceButton mBtnVoice;
    protected EmoticonsEditText mEtChat;
    protected ImageView mBtnFace;
    protected RelativeLayout mRlInput;
    protected ImageView mBtnMultimedia;
    protected Button mBtnSend;
    protected EmoticonsPageLayout emotionPageLayout;

    protected EmoticonsFuncView mEmoticonsFuncView;
    protected EmoticonsIndicatorView mEmoticonsIndicatorView;
    protected EmoticonsToolBarView mEmoticonsToolBarView;

    protected boolean mDispatchKeyEventPreImeLock = false;

    public EmoticonsInputBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.view_keyboard_input, this);
        initView();
        initEmoticonFuncView();
        initEditView();
    }

    protected void initView() {
        mBtnVoiceOrText = findViewById(R.id.btn_voice_or_text);
        mBtnVoice = findViewById(R.id.btn_voice);
        mEtChat = findViewById(R.id.et_chat);
        mBtnFace = findViewById(R.id.btn_face);
        mRlInput = findViewById(R.id.rl_input);
        mBtnMultimedia = findViewById(R.id.btn_multimedia);
        mBtnSend = findViewById(R.id.btn_send);
        emotionPageLayout = findViewById(R.id.emotion_page_layout);

        mBtnVoiceOrText.setOnClickListener(this);
        mBtnFace.setOnClickListener(this);
        mBtnMultimedia.setOnClickListener(this);
        mEtChat.setOnBackKeyClickListener(this);
    }

    protected void initEmoticonFuncView() {
        View keyboardView = mInflater.inflate(R.layout.view_board_func, null);
        emotionPageLayout.addFuncView(FUNC_TYPE_EMOTION, keyboardView);
        mEmoticonsFuncView = findViewById(R.id.view_epv);
        mEmoticonsIndicatorView = findViewById(R.id.view_eiv);
        mEmoticonsToolBarView = findViewById(R.id.view_etv);
        mEmoticonsFuncView.setOnIndicatorListener(this);
        mEmoticonsToolBarView.setOnToolBarItemClickListener(this);
        emotionPageLayout.setOnFuncChangeListener(this);
    }

    protected void initEditView() {
        mEtChat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!mEtChat.isFocused()) {
                    mEtChat.setFocusable(true);
                    mEtChat.setFocusableInTouchMode(true);
                }
                return false;
            }
        });

        mEtChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    mBtnSend.setVisibility(VISIBLE);
                    mBtnMultimedia.setVisibility(GONE);
                    mBtnSend.setBackgroundResource(R.drawable.btn_send_bg);
                } else {
                    mBtnMultimedia.setVisibility(VISIBLE);
                    mBtnSend.setVisibility(GONE);
                }
            }
        });
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        if (pageSetAdapter != null) {
            ArrayList<PageSetEntity> pageSetEntities = pageSetAdapter.getPageSetEntityList();
            if (pageSetEntities != null) {
                for (PageSetEntity pageSetEntity : pageSetEntities) {
                    mEmoticonsToolBarView.addToolItemView(pageSetEntity);
                }
            }
        }
        mEmoticonsFuncView.setAdapter(pageSetAdapter);
    }

    public void addFuncView(View view) {
        emotionPageLayout.addFuncView(FUNC_TYPE_APPPS, view);
    }

    public void reset() {
        EmoticonsInputBoardUtils.closeSoftKeyboard(this);
        emotionPageLayout.hideAllFuncView();
        mBtnFace.setImageResource(R.drawable.icon_face_nomal);
    }

    protected void showVoice() {
        mRlInput.setVisibility(GONE);
        mBtnVoice.setVisibility(VISIBLE);
        reset();
    }

    protected void checkVoice() {
        if (mBtnVoice.isShown()) {
            mBtnVoiceOrText.setImageResource(R.drawable.btn_voice_or_text_keyboard);
        } else {
            mBtnVoiceOrText.setImageResource(R.drawable.btn_voice_or_text);
        }
    }

    protected void showText() {
        mRlInput.setVisibility(VISIBLE);
        mBtnVoice.setVisibility(GONE);
    }

    protected void toggleFuncView(int key) {
        showText();
        emotionPageLayout.toggleFuncView(key, isSoftKeyboardPop(), mEtChat);
    }

    @Override
    public void onFuncChange(int key) {
        if (FUNC_TYPE_EMOTION == key) {
            mBtnFace.setImageResource(R.drawable.icon_softkeyboard_nomal);
        } else {
            mBtnFace.setImageResource(R.drawable.icon_face_nomal);
        }
        checkVoice();
    }

    @Override
    public void onSoftKeyboardHeightChanged(int height) {
        emotionPageLayout.updateHeight(height);
    }

    @Override
    public void OnSoftPop(int height) {
        super.OnSoftPop(height);
        emotionPageLayout.setVisibility(true);
        onFuncChange(emotionPageLayout.DEF_KEY);
    }

    @Override
    public void OnSoftClose() {
        super.OnSoftClose();
        if (emotionPageLayout.isOnlyShowSoftKeyboard()) {
            reset();
        } else {
            onFuncChange(emotionPageLayout.getCurrentFuncKey());
        }
    }

    public void addOnFuncKeyBoardListener(EmoticonsPageLayout.OnFuncKeyBoardListener l) {
        emotionPageLayout.addOnKeyBoardListener(l);
    }

    @Override
    public void emoticonSetChanged(PageSetEntity pageSetEntity) {
        mEmoticonsToolBarView.setToolBtnSelect(pageSetEntity.getUuid());
    }

    @Override
    public void playTo(int position, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playTo(position, pageSetEntity);
    }

    @Override
    public void playBy(int oldPosition, int newPosition, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playBy(oldPosition, newPosition, pageSetEntity);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_face) {
            toggleFuncView(FUNC_TYPE_EMOTION);
        } else if (i == R.id.btn_multimedia) {
            toggleFuncView(FUNC_TYPE_APPPS);
        }
    }

    public void setVideoText() {
        if (mRlInput.isShown()) {
            mBtnVoiceOrText.setImageResource(R.drawable.btn_voice_or_text_keyboard);
            showVoice();
        } else {
            showText();
            mBtnVoiceOrText.setImageResource(R.drawable.btn_voice_or_text);
            EmoticonsInputBoardUtils.openSoftKeyboard(mEtChat);
        }
    }

    public ImageView getVoiceOrText() {
        return mBtnVoiceOrText;
    }

    @Override
    public void onToolBarItemClick(PageSetEntity pageSetEntity) {
        mEmoticonsFuncView.setCurrentPageSet(pageSetEntity);
    }

    @Override
    public void onBackKeyClick() {
        if (emotionPageLayout.isShown()) {
            mDispatchKeyEventPreImeLock = true;
            reset();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                if (mDispatchKeyEventPreImeLock) {
                    mDispatchKeyEventPreImeLock = false;
                    return true;
                }
                if (emotionPageLayout.isShown()) {
                    reset();
                    return true;
                } else {
                    return super.dispatchKeyEvent(event);
                }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (EmoticonsInputBoardUtils.isFullScreen((Activity) getContext())) {
            return false;
        }
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        if (EmoticonsInputBoardUtils.isFullScreen((Activity) getContext())) {
            return;
        }
        super.requestChildFocus(child, focused);
    }

    public EmoticonsEditText getEtChat() {
        return mEtChat;
    }

    public Button getBtnSend() {
        return mBtnSend;
    }
}

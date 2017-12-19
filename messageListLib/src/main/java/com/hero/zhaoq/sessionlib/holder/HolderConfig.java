package com.hero.zhaoq.sessionlib.holder;

import android.view.View;

import com.hero.zhaoq.sessionlib.R;
import com.hero.zhaoq.sessionlib.beans.IMessage;

/**
 * author: zhaoqiang
 * date:2017/11/27 / 09:46
 * zhaoqiang:zhaoq_hero@163.com
 */

public class HolderConfig {

    //发送文本 和接受文本
    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mSendTxtHolder;
    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mReceiveTxtHolder;

    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mSendLocationHolder;
    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mReceiveLocationHolder;

    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mSendVoiceHolder;
    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mReceiveVoiceHolder;

    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mSendPhotoHolder;
    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mReceivePhotoHolder;

    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mSendVideoHolder;
    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mReceiveVideoHolder;

    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mCustomSendMsgHolder;
    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mCustomReceiveMsgHolder;

    private Class<? extends BaseMessageViewHolder<? extends IMessage>> mEventMsgHolder;


    private int mSendTxtLayout;
    private int mReceiveTxtLayout;

    private int mSendLocationLayout;
    private int mReceiveLocationLayout;

    private int mSendVoiceLayout;
    private int mReceiveVoiceLayout;

    private int mSendPhotoLayout;
    private int mReceivePhotoLayout;

    private int mSendVideoLayout;
    private int mReceiveVideoLayout;

    private int mCustomSendMsgLayout;
    private int mCustomReceiveMsgLayout;

    private int mEventLayout;

    public HolderConfig() {

        mSendTxtHolder = DefaultTxtViewHolder.class;
        mReceiveTxtHolder = DefaultTxtViewHolder.class;

        mSendVoiceHolder = DefaultVoiceViewHolder.class;
        mReceiveVoiceHolder = DefaultVoiceViewHolder.class;

        mSendPhotoHolder = DefaultPhotoViewHolder.class;
        mReceivePhotoHolder = DefaultPhotoViewHolder.class;

        mSendVideoHolder = DefaultVideoViewHolder.class;
        mReceiveVideoHolder = DefaultVideoViewHolder.class;

        mSendTxtLayout = R.layout.item_send_text;
        mReceiveTxtLayout = R.layout.item_receive_txt;

        mSendVoiceLayout = R.layout.item_send_voice;
        mReceiveVoiceLayout = R.layout.item_receive_voice;

        //TODO :
//        mSendPhotoLayout = R.layout.item_send_photo;
//        mReceivePhotoLayout = R.layout.item_receive_photo;
//
//        mSendVideoLayout = R.layout.item_send_video;
//        mReceiveVideoLayout = R.layout.item_receive_video;
//
//        //
//        mEventMsgHolder = DefaultEventMsgViewHolder.class;
//        mEventLayout = R.layout.item_event_message;
    }

    //文本  消息：
    private static class DefaultTxtViewHolder extends TxtViewHolder<IMessage> {
        public DefaultTxtViewHolder(View itemView, boolean isSender) {
            super(itemView, isSender);
        }
    }

    //照片
    private static class DefaultPhotoViewHolder extends PhotoViewHolder<IMessage> {
        public DefaultPhotoViewHolder(View itemView, boolean isSender) {
            super(itemView, isSender);
        }
    }

    //音频  文件：
    private static class DefaultVoiceViewHolder extends VoiceViewHolder<IMessage> {
        public DefaultVoiceViewHolder(View itemView, boolean isSender) {
            super(itemView, isSender);
        }
    }

    //视频   发送：
    private static class DefaultVideoViewHolder extends VideoViewHolder<IMessage> {
        public DefaultVideoViewHolder(View itemView, boolean isSender) {
            super(itemView, isSender);
        }
    }

    // 文本消息提醒：
    private static class DefaultEventMsgViewHolder extends EventViewHolder<IMessage> {
        public DefaultEventMsgViewHolder(View itemView, boolean isSender) {
            super(itemView, isSender);
        }
    }


}

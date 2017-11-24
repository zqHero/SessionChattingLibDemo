package com.hero.zhaoq.emotionboardlib;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hero.zhaoq.emotionboardlib.adapter.BoardBaseAdapter;
import com.hero.zhaoq.emotionboardlib.adapter.ImageAdapter;
import com.hero.zhaoq.emotionboardlib.adapter.EmoticonsAdapter;
import com.hero.zhaoq.emotionboardlib.adapter.PageSetAdapter;
import com.hero.zhaoq.emotionboardlib.adapter.TextAdapter;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.utils.FileUtils;
import com.hero.zhaoq.emotionboardlib.utils.ImageBase;
import com.hero.zhaoq.emotionboardlib.utils.ParseDataUtils;
import com.hero.zhaoq.emotionboardlib.interfce.EmoticonClickListener;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageBean;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageSetEntity;
import com.hero.zhaoq.emotionboardlib.entity.PageEntity;
import com.hero.zhaoq.emotionboardlib.entity.PageSetEntity;
import com.hero.zhaoq.emotionboardlib.interfce.PageViewInstantiateListener;
import com.hero.zhaoq.emotionboardlib.widget.EmotionItemPageView;
import com.hero.zhaoq.emotionboardlib.widget.SimpleAppsGridView;

import java.lang.reflect.Constructor;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 14:32
 * zhaoqiang:zhaoq_hero@163.com
 */
public class EmotionsPageManager {

    public static PageSetAdapter sCommonPageSetAdapter;

    public static PageSetAdapter getCommonAdapter(Context context, EmoticonClickListener emoticonClickListener) {

        if (sCommonPageSetAdapter != null) {
            return sCommonPageSetAdapter;
        }

        PageSetAdapter pageSetAdapter = new PageSetAdapter();

        //添加  微信  表情
        addEmotionPageSetEntity(pageSetAdapter, context, emoticonClickListener,
                "wxemotions", "wxemotions.zip","wx_emotions.xml");

        //添加  极光 表情：
        addEmotionPageSetEntity(pageSetAdapter, context, emoticonClickListener,
                "jiguangemotions", "jiguangemotions.zip", "jiguang_emotions.xml");

        //添加  兔斯基表情：
        addImagePageSetEntity(pageSetAdapter, context, emoticonClickListener,
                "rabbits", "rabbits.zip", "rabbits.xml");

        //添加  兔子表情图片：
        addImagePageSetEntity(pageSetAdapter, context, emoticonClickListener,
                "bigrabbits", "bigrabbits.zip", "big_rabbits.xml");

        //添加  鸡表情图片：
        addImagePageSetEntity(pageSetAdapter, context, emoticonClickListener,
                "chickenemotions", "chickenemoticons.zip", "chicken_emoticons.xml");

        //添加  全身熊图片：
        addImagePageSetEntity(pageSetAdapter, context, emoticonClickListener,
                "bigbears","bigbears.zip","big_bear.xml");

        //添加  熊头像图片：
        addImagePageSetEntity(pageSetAdapter, context, emoticonClickListener,
                "bears","bears.zip","bear.xml");

        //添加  颜文字
        addEmotionTextPageSetEntity(pageSetAdapter, context, emoticonClickListener);

        //滑倒  更多的   其他功能：
//        addTestPageSetEntity(pageSetAdapter, context); //控制能否从表情滑动到更多功能

        return pageSetAdapter;
    }

    /**
     * 插入   符号   表情集
     *
     * @param pageSetAdapter
     * @param context
     * @param emoticonClickListener
     */
    public static void addEmotionPageSetEntity(PageSetAdapter pageSetAdapter, Context context,
                                               EmoticonClickListener emoticonClickListener,
                                               String saveFolderPath, String assetsFileName,
                                               String xmlFileName) {
        final String filePath = FileUtils.getFolderPath(saveFolderPath);
        final EmoticonPageSetEntity<EmoticonBean> emoticonPageSetEntity = ParseDataUtils.parseDataFromFile(context, filePath, assetsFileName, xmlFileName);
        if (emoticonPageSetEntity == null) {
            return;
        }
        EmoticonPageSetEntity pageSetEntity
                = new EmoticonPageSetEntity.Builder()
                .setLine(emoticonPageSetEntity.getLine())
                .setRow(emoticonPageSetEntity.getRow())
                .setEmoticonList(emoticonPageSetEntity.getEmoticonList())
                .setIPageViewInstantiateItem(getEmoticonPageViewInstantiateItem(EmoticonsAdapter.class, emoticonClickListener))
                .setShowDelBtn(EmoticonPageBean.DelBtnStatus.LAST)
                .setIconUri(ImageBase.Scheme.FILE.toUri(filePath + "/" + emoticonPageSetEntity.getIconUri()))
                .build();
        pageSetAdapter.add(pageSetEntity);
    }

    /**
     * 插入 图片表情集：
     */
    private static void addImagePageSetEntity(PageSetAdapter pageSetAdapter, Context context,
                                              EmoticonClickListener emoticonClickListener,
                                              String saveFolderPath, String assetsFileName,
                                              String xmlFileName) {
        String filePath = FileUtils.getFolderPath(saveFolderPath);
        EmoticonPageSetEntity<EmoticonBean> emoticonPageSetEntity = ParseDataUtils.parseDataFromFile(context, filePath, assetsFileName, xmlFileName);
        if (emoticonPageSetEntity == null) {
            return;
        }
        EmoticonPageSetEntity pageSetEntity
                = new EmoticonPageSetEntity.Builder()
                .setLine(emoticonPageSetEntity.getLine())
                .setRow(emoticonPageSetEntity.getRow())
                .setEmoticonList(emoticonPageSetEntity.getEmoticonList())
                .setIPageViewInstantiateItem(getEmoticonPageViewInstantiateItem(ImageAdapter.class, emoticonClickListener))
                .setIconUri(ImageBase.Scheme.FILE.toUri(filePath + "/" + emoticonPageSetEntity.getIconUri()))
                .build();
        pageSetAdapter.add(pageSetEntity);
    }

//    /**
//     * 添加  兔斯基表情：
//     * @param pageSetAdapter
//     * @param context
//     * @param emoticonClickListener
//     */
//    private static void addRabbitPageSetEntity(PageSetAdapter pageSetAdapter, Context context, EmoticonClickListener emoticonClickListener) {
//        final String filePath = FileUtils.getFolderPath("rabbit");
//        final EmoticonPageSetEntity<EmoticonBean> emoticonPageSetEntity = ParseDataUtils.parseDataFromFile(context, filePath, "rabbits.zip", "rabbit.xml");
//
//        if (emoticonPageSetEntity == null) {
//            return;
//        }
//        Log.i("info",emoticonPageSetEntity.getEmoticonList().size() + "======sd===" +
//                emoticonPageSetEntity.getLine() + "=======" +
//        emoticonPageSetEntity.getRow() +"=====");
//
//        EmoticonPageSetEntity pageSetEntity
//                = new EmoticonPageSetEntity.Builder()
//                .setLine(emoticonPageSetEntity.getLine())
//                .setRow(emoticonPageSetEntity.getRow())
//                .setEmoticonList(emoticonPageSetEntity.getEmoticonList())
//                .setIPageViewInstantiateItem(getEmoticonPageViewInstantiateItem(ImageAdapter.class, emoticonClickListener))
//                .setShowDelBtn(EmoticonPageBean.DelBtnStatus.GONE)
//                .setIconUri(ImageBase.Scheme.FILE.toUri(filePath + "/" + emoticonPageSetEntity.getIconUri()))
//                .build();
//        pageSetAdapter.add(pageSetEntity);
//    }

    /**
     * 插入颜文字表情集
     *
     * @param pageSetAdapter
     * @param context
     * @param emoticonClickListener
     */
    public static void addEmotionTextPageSetEntity(PageSetAdapter pageSetAdapter, Context context, EmoticonClickListener emoticonClickListener) {
        EmoticonPageSetEntity kaomojiPageSetEntity
                = new EmoticonPageSetEntity.Builder()
                .setLine(3)
                .setRow(3)
                .setEmoticonList(ParseDataUtils.parseKaomojiData(context))
                .setIPageViewInstantiateItem(getEmoticonPageViewInstantiateItem(TextAdapter.class, emoticonClickListener))
                .setIconUri(ImageBase.Scheme.DRAWABLE.toUri("icon_textemotion"))
                .build();
        pageSetAdapter.add(kaomojiPageSetEntity);
    }

    /**
     * 测试页集
     *
     * @param pageSetAdapter
     * @param context
     */
    public static void addTestPageSetEntity(PageSetAdapter pageSetAdapter, Context context) {
        PageSetEntity pageSetEntity = new PageSetEntity.Builder()
                .addPageEntity(new PageEntity(new SimpleAppsGridView(context)))
                .setIconUri(ImageBase.Scheme.DRAWABLE.toUri("icon_textemotion"))
                .setShowIndicator(false)
                .build();
        pageSetAdapter.add(pageSetEntity);
    }

    public static PageViewInstantiateListener<EmoticonPageBean> getEmoticonPageViewInstantiateItem(
            final Class _class, final EmoticonClickListener onEmoticonClickListener) {
        return new PageViewInstantiateListener<EmoticonPageBean>() {
            @Override
            public View instantiateItem(ViewGroup container, int position, EmoticonPageBean pageEntity) {
                if (pageEntity.getRootView() == null) {
                    EmotionItemPageView pageView = new EmotionItemPageView(container.getContext());
                    pageView.setNumColumns(pageEntity.getRow());
                    pageEntity.setRootView(pageView);
                    BoardBaseAdapter adapter = (BoardBaseAdapter) newInstance(_class, container.getContext(),
                            pageEntity, onEmoticonClickListener);
                    pageView.getEmoticonsGridView().setAdapter(adapter);
                }
                return pageEntity.getRootView();
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static Object newInstance(Class _Class, Object... args) {
        Constructor cons = _Class.getConstructors()[0];
        Object obj = null;
        try {
            obj = cons.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void delClick(EditText editText) {
        int action = KeyEvent.ACTION_DOWN;
        int code = KeyEvent.KEYCODE_DEL;
        KeyEvent event = new KeyEvent(action, code);
        editText.onKeyDown(KeyEvent.KEYCODE_DEL, event);
    }
}


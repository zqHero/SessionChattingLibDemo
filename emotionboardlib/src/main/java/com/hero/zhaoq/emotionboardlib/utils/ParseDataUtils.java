package com.hero.zhaoq.emotionboardlib.utils;

import android.content.Context;
import android.util.Log;

import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageSetEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 14:59
 * zhaoqiang:zhaoq_hero@163.com
 * <p>
 * 解析文件 工具类：
 */
public class ParseDataUtils {

    public static ArrayList<EmoticonBean> ParseQqData(HashMap<String, Integer> data) {
        Iterator iter = data.entrySet().iterator();
        if (!iter.hasNext()) {
            return null;
        }
        ArrayList<EmoticonBean> emojis = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            EmoticonBean entity = new EmoticonBean();
            entity.setContent((String) key);
            entity.setIconUri("" + val);
            emojis.add(entity);
        }
        return emojis;
    }

    public static ArrayList<EmoticonBean> parseKaomojiData(Context context) {
        ArrayList<EmoticonBean> textEmotionArray = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open("textEmotion"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                EmoticonBean bean = new EmoticonBean(line.trim());
                textEmotionArray.add(bean);
            }
            return textEmotionArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static EmoticonPageSetEntity<EmoticonBean> parseDataFromFile(Context context, String filePath, String assetsFileName, String xmlName) {
        String xmlFilePath = filePath + "/" + xmlName;
        File file = new File(xmlFilePath);
        if (!file.exists()) {
            try {
                InputStream un = context.getAssets().open(assetsFileName);
                FileUtils.unzip(un, filePath);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        XmlUtil xmlUtil = new XmlUtil(context);
        return xmlUtil.ParserXml(filePath, xmlUtil.getXmlFromSD(xmlFilePath));
    }
}


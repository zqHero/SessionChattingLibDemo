package com.hero.zhaoq.emotionboardlib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.hero.zhaoq.emotionboardlib.entity.EmoticonBean;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageBean;
import com.hero.zhaoq.emotionboardlib.entity.EmoticonPageSetEntity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * author: zhaoqiang
 * date:2017/11/21 / 15:00
 * zhaoqiang:zhaoq_hero@163.com
 */

public class XmlUtil {
    Context mContext;

    public XmlUtil(Context context) {
        this.mContext = context;
    }

    public InputStream getXmlFromAssets(String xmlName) {
        try {
            InputStream inStream = this.mContext.getResources().getAssets().open(xmlName);
            return inStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream getXmlFromSD(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream inStream = new FileInputStream(file);
                return inStream;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO  解析  XML文件
     * @param filePath
     * @param inStream
     * @return
     */
    public EmoticonPageSetEntity<EmoticonBean> ParserXml(String filePath, InputStream inStream) {

        String arrayParentKey = "EmoticonBean";
        boolean isChildCheck = false;

        EmoticonPageSetEntity.Builder<EmoticonBean> emoticonPageSetEntity = new EmoticonPageSetEntity.Builder<>();
        ArrayList<EmoticonBean> emoticonList = new ArrayList<>();
        emoticonPageSetEntity.setEmoticonList(emoticonList);
        EmoticonBean emoticonBeanTemp = null;

        if (null != inStream) {
            XmlPullParser pullParser = Xml.newPullParser();
            try {
                pullParser.setInput(inStream, "UTF-8");
                int event = pullParser.getEventType();

                while (event != XmlPullParser.END_DOCUMENT) {
                    switch (event) {

                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            String skeyName = pullParser.getName();

                            /**
                             * EmoticonBeans data
                             */
                            if (isChildCheck && emoticonBeanTemp != null) {
                                if (skeyName.equals("eventType")) {
                                    try {
                                        String value = pullParser.nextText();
                                        emoticonBeanTemp.setEventType(Integer.parseInt(value));
                                    } catch (NumberFormatException e) {
                                        Log.i("info", "xml 解析失败 =====  " + e.toString());
                                    }
                                } else if (skeyName.equals("iconUri")) {
                                    String value = pullParser.nextText();
                                    emoticonBeanTemp.setIconUri("file://" + filePath + "/" + value);
                                } else if (skeyName.equals("content")) {
                                    String value = pullParser.nextText();
                                    emoticonBeanTemp.setContent(value);
                                }
                            }
                            /**
                             * EmoticonSet data
                             */
                            else {
                                try {
                                    if (skeyName.equals("name")) {
                                        String value = pullParser.nextText();
                                        emoticonPageSetEntity.setSetName(value);
                                    } else if (skeyName.equals("line")) {
                                        String value = pullParser.nextText();
                                        emoticonPageSetEntity.setLine(Integer.parseInt(value));
                                    } else if (skeyName.equals("row")) {
                                        String value = pullParser.nextText();
                                        emoticonPageSetEntity.setRow(Integer.parseInt(value));
                                    } else if (skeyName.equals("iconUri")) {
                                        String value = pullParser.nextText();
                                        emoticonPageSetEntity.setIconUri(value);
                                    } else if (skeyName.equals("isShowDelBtn")) {
                                        String value = pullParser.nextText();
                                        EmoticonPageBean.DelBtnStatus delBtnStatus;
                                        if (!TextUtils.isEmpty(value) && Integer.parseInt(value) == 1) {
                                            delBtnStatus = EmoticonPageBean.DelBtnStatus.FOLLOW;
                                        } else if (!TextUtils.isEmpty(value) && Integer.parseInt(value) == 2) {
                                            delBtnStatus = EmoticonPageBean.DelBtnStatus.LAST;
                                        } else {
                                            delBtnStatus = EmoticonPageBean.DelBtnStatus.GONE;
                                        }
                                        emoticonPageSetEntity.setShowDelBtn(delBtnStatus);
                                    }
                                } catch (NumberFormatException e) {
                                    Log.i("info", "xml 解析失败 =====  " + e.toString());
                                    e.printStackTrace();
                                }
                            }

                            if (skeyName.equals(arrayParentKey)) {
                                isChildCheck = true;
                                emoticonBeanTemp = new EmoticonBean();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            String ekeyName = pullParser.getName();
                            if (isChildCheck && ekeyName.equals(arrayParentKey)) {
                                isChildCheck = false;
                                emoticonList.add(emoticonBeanTemp);
                            }
                            break;
                        default:
                            break;
                    }
                    event = pullParser.next();
                }
                return new EmoticonPageSetEntity(emoticonPageSetEntity);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new EmoticonPageSetEntity(emoticonPageSetEntity);
    }
}


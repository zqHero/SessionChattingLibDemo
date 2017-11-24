
####<font color="#0f099">一，前言：</font>
</p>
<font color="#3e3c3d">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;说实话，搞了这么久的Android，个人感觉，Android开发  其实只是一个无限重复的工作。

</p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当然说这句话是有前提的，前提就是你还未从一个代码学习者转变为一个代码创造者。然而 很不辛，博主就是一个这样的开发者。这是博主在写这篇文章时的感悟。哎，说多了都是泪呀，</font>
</p>
</p>

	多说无益，还是谈下我们今天的项目：今天要写的博客其实是一个关于聊天界面输入表情的一篇博客。最近公司因为要用到聊天功能，博主稍微进行了一下研究。搞定公司项目后，自己写了一个关于表情的库。说实话网上有关的库简直时太多了。


####<font color="#0f099">二，献上效果图：</font>

![这里写图片描述](https://raw.githubusercontent.com/zqHero/SessionChattingLibDemo/master/img/1.gif)

![这里写图片描述](https://raw.githubusercontent.com/zqHero/SessionChattingLibDemo/master/img/2.gif)
####<font color="#0f099">三，代码介绍：</font>

	说实话 网上的这种库简直时太多，本人也是在网上找了很多代码，最后在找到感觉还不错的几个库。改了改源码。相比原来的库更加简单优化。如果有童鞋对此有兴趣，可以继续往下看，否则请忽略。

初始化底部  inputboard 代码：

```
//activity 中初始化  代码：
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ekBar = findViewById(R.id.ek_bar);

        initEmotionListener(); //初始化  表情点击事件：
        initMView(); //初始化 View
    }
```

```
//初始化     表情点击事件：
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
                            Toast.makeText(EmotionsActivity.this, "图片:" + o.toString(),
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
```


```
    /**
     * 初始化  inputView：
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
    }
```


表情图片以及表情符号，都是一些资源文件：博主对他们进行了压缩。减少了包的大小。

![这里写图片描述](https://raw.githubusercontent.com/zqHero/SessionChattingLibDemo/master/img/3.gif)


博主的表情包相对较多。当然如果对你无用你可以直接找到工具类EmotionsPageManager注释如下代码即可：

```
//初始化  底部表情pager的  适配器：表情适配器分为  文本表情和 图片表情：
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
```


<font color="#f00">当然如果对你有用希望动动你的小手fork 或者关注博主一下。下面奉上代码源地址：</font>

https://github.com/zqHero/SessionChattingLibDemo




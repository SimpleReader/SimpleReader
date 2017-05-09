package com.simplereader.graduation.model;

import java.util.List;

/**
 * Description:
 * Created by chenggong on 2017/5/4.
 */

public class ArticleResponse {

    /**
     * status : 200
     * message :
     * data : [{"article_id":"1","article_title":"有什么可以保持每天好心情的方法？","article_author":"迎刃","article_sketch":"我们毕生都在追求快乐，也一直在寻找那些走出负面情绪的方法。 有人说，等我找到另一半，我才会快乐。 有人说，升职让我快乐啊。 有人说，我的孩子很听话，所以我快乐。","article_thumbnail":"http://upload-images.jianshu.io/upload_images/253140-698af18c47962a9f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240","article_time":1493783108000,"article_avatar":"http://upload.jianshu.io/users/upload_avatars/253140/b9adfdadef8a?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240","article_content":"http://www.jianshu.com/p/b85174e51653"},{"article_id":"2","article_title":"油画｜春未央，心上一处清喜水泽","article_author":"張震绘生活","article_sketch":"春浓，轻灵盈暖，芳菲皆处，一场争奇斗艳的盛开，更显得深长而浩荡。而我，却喜欢将一颗心放在一弯水月里沉沦，感知一份恒美而清凉的静谧。依月，亭立，使劲地眨一下眼，抖落眉睫上的薄薄尘埃；一双明亮的眸子，泛起一抹清透的烟波蓝。","article_thumbnail":"http://upload-images.jianshu.io/upload_images/3870720-44173ca565abca5d.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50","article_time":1493790918000,"article_avatar":"http://upload.jianshu.io/users/upload_avatars/3870720/02c2bd94c8b0.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144","article_content":"http://www.jianshu.com/p/5efaab64ed1e"},{"article_id":"3","article_title":"拿了一年的1.5K，可我没打算辞职","article_author":"可可为","article_sketch":"文/可可为 01前文 这年头，工作就跟找对象一样难！目标很多，可合适的没那么几个。 都是还要互相看走眼的，不然就拉爆。 大家选择工作，都是以工资和发展前景作为考虑，选择目标。","article_thumbnail":"http://upload-images.jianshu.io/upload_images/4981706-ecf51bc4261e9969.JPG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50","article_time":1493792551000,"article_avatar":"http://upload.jianshu.io/users/upload_avatars/4981706/13b63682-4f8b-40c8-a291-b4b6cb4e920d.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144","article_content":"http://www.jianshu.com/p/f1e13e635211"},{"article_id":"4","article_title":"珍爱生命，远离朋友圈的学习粉","article_author":"撒哈拉异乡人","article_sketch":"01 最近一个月我的朋友圈，一定是打开方式不对，刷屏的不是微商，而是另一种每天不是在晒与某个跟他工作领域毫无关联的大咖合照，就是狂刷免费微课的培训链接，又或是今天读了多少本书做了多少个平板支撑还做成了精美的图片（一定要手绘版思维导图才更有逼格）亮瞎我的钛合金加V狗眼。","article_thumbnail":"http://upload-images.jianshu.io/upload_images/4240697-43c63da0af20b0ca.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240","article_time":1493792685000,"article_avatar":"http://upload.jianshu.io/users/upload_avatars/4240697/e44cad3286e7.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144","article_content":"http://www.jianshu.com/p/dd39a54270c4"},{"article_id":"5","article_title":"那些年网易云里戳心窝热评","article_author":"北辰以北_","article_sketch":"有人曾说:\"爱上一首歌可能不是因为旋律多动听，嗓音多感人，而是因为，你恰好也有个故事\"。 网易云，往忆云。 心血来潮，整理了部分那些年戳心窝的热评！ ﻿ 1.不在巅峰慕名而来","article_thumbnail":"http://upload-images.jianshu.io/upload_images/1368738-2bb58de8a617ef42.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50","article_time":1493793077000,"article_avatar":"http://upload.jianshu.io/users/upload_avatars/1368738/dbed36d3-92aa-4c7f-ba39-1874ccb88001.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/144/h/144","article_content":"http://www.jianshu.com/p/abdd3f0b1f70"}]
     */

    private String status;
    private String message;
    private List<Article> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }
}

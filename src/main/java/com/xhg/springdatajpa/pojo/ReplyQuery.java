package com.xhg.springdatajpa.pojo;

import java.util.Date;

public class ReplyQuery {
    private String content;
    private Date replyTime;
    private Informations infoId;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Informations getInfoId() {
        return infoId;
    }

    public void setInfoId(Informations infoId) {
        this.infoId = infoId;
    }


    @Override
    public String toString() {
        return "ReplyQuery{" +
                "content='" + content + '\'' +
                ", replyTime=" + replyTime +
                ", infoId=" + infoId +
                '}';
    }
}

package com.xhg.springdatajpa.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * replies
 * @author 
 */
@Entity
@Table(name = "replies")
public class Replies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1,max = 200,message = "请填写回复内容！")
    @Column
    private String content;

    /**
     * 回复时间
     */
    @Column
    private Date replyTime;


    /**
     * 资讯ID()
     */
    @ManyToOne
    @JoinColumn(name="infoId")
    private Informations infoId;

    private static final long serialVersionUID = 1L;

    public Replies() {
    }

    public Replies(Integer id,@NotNull @Size(min = 1, max = 200, message = "请填写回复内容！") String content, Date replyTime,Informations infoId) {
        this.id = id;
        this.content = content;
        this.replyTime = replyTime;
        this.infoId = infoId;
    }

    /*public Integer getInfo() {
        return info;
    }

    public void setInfo(Integer info) {
        this.info = info;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Replies other = (Replies) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReplyTime() == null ? other.getReplyTime() == null : this.getReplyTime().equals(other.getReplyTime()))
            && (this.getInfoId() == null ? other.getInfoId() == null : this.getInfoId().equals(other.getInfoId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReplyTime() == null) ? 0 : getReplyTime().hashCode());
        result = prime * result + ((getInfoId() == null) ? 0 : getInfoId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", replyTime=").append(replyTime);
//        sb.append(", info=").append(info);
        sb.append(", infoId=").append(infoId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
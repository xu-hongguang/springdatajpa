package com.xhg.springdatajpa.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * informations
 *
 * @author
 */
@Entity
@Table(name = "informations")
public class Informations implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    /**
     * 回复次数
     */
    @Column(name = "replyCount", nullable = false)
    private Integer replyCount;

    /**
     * 查看次数
     */
    @Column(name = "viewCount", nullable = false)
    private Integer viewCount;

    /**
     * 发表时间
     */
    @Column(name = "replyTime", nullable = false)
    private Date replyTime;

    /**
     * 最后回复时间
     */
    @Column(name = "lastPostTime", nullable = false)
    private Date lastPostTime;

    /**
     * \@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     * \@JoinColumn(name = "infoId", referencedColumnName = "id")
     */
    @OneToMany(targetEntity = Replies.class, fetch = FetchType.EAGER, mappedBy = "infoId")
    private Set<Replies> replies = new HashSet<>();

    public Informations() {
    }

    public Informations(String title, String content, Integer replyCount, Integer viewCount, Date replyTime, Date lastPostTime) {
        this.title = title;
        this.content = content;
        this.replyCount = replyCount;
        this.viewCount = viewCount;
        this.replyTime = replyTime;
        this.lastPostTime = lastPostTime;
        this.replies = replies;
    }

    public Set<Replies> getReplies() {
        return replies;
    }

    public void setReplies(Set<Replies> replies) {
        this.replies = replies;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Date getLastPostTime() {
        return lastPostTime;
    }

    public void setLastPostTime(Date lastPostTime) {
        this.lastPostTime = lastPostTime;
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
        Informations other = (Informations) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getReplyCount() == null ? other.getReplyCount() == null : this.getReplyCount().equals(other.getReplyCount()))
                && (this.getViewCount() == null ? other.getViewCount() == null : this.getViewCount().equals(other.getViewCount()))
                && (this.getReplyTime() == null ? other.getReplyTime() == null : this.getReplyTime().equals(other.getReplyTime()))
                && (this.getLastPostTime() == null ? other.getLastPostTime() == null : this.getLastPostTime().equals(other.getLastPostTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReplyCount() == null) ? 0 : getReplyCount().hashCode());
        result = prime * result + ((getViewCount() == null) ? 0 : getViewCount().hashCode());
        result = prime * result + ((getReplyTime() == null) ? 0 : getReplyTime().hashCode());
        result = prime * result + ((getLastPostTime() == null) ? 0 : getLastPostTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", replyCount=").append(replyCount);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", replyTime=").append(replyTime);
        sb.append(", lastPostTime=").append(lastPostTime);
        sb.append(", replies=").append(replies.size());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
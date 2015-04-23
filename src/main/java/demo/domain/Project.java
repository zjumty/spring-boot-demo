package demo.domain;

import java.util.Date;

/**
 * TODO: 这里需要写注释
 */
public class Project {
    private Integer id;

    private String type;

    private String name;

    private Date begin;

    private Date end;

    public Project() {
    }

    public Project(Integer id, String type, String name, Date begin, Date end) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", begin=").append(begin);
        sb.append(", end=").append(end);
        sb.append('}');
        return sb.toString();
    }
}

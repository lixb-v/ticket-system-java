package src.LXab.entity;

/**
 * cinema 电影院实体类
 * */
public class Cinema {
    // 数据库主键-id
    private int id;

    // 影院姓名
    private String name;

    // 影院地址
    private String address;

    // 开始营业时间
    private String openingTime;

    // 结束影院时间
    private String shutdownTime;

    // get和set方法
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }

    public String getOpeningTime() { return this.openingTime; }
    public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }

    public String getShutDownTime() { return this.shutdownTime; }
    public void setShutdownTime(String shutdownTime) { this.shutdownTime = shutdownTime; }

    @Override
    public String toString() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("id:");
        sbf.append(this.id);

        sbf.append(", 影院名称:");
        sbf.append(this.name);

        sbf.append(", 地址:");
        sbf.append(this.address);

        sbf.append(", 开始营业时间:");
        sbf.append(this.openingTime);


        sbf.append(", 结束营业时间:");
        sbf.append(this.shutdownTime);
        return sbf.toString();
//        return String.format("id: %s, 影院名称: %s, 地址: %s, 开始营业时间: %s, 结束营业时间: %s", this.id, this.name, this.address, this.openingTime, this.shutdownTime);
    }
}

package vo;

public class Download {
    private int id;
    private String name;
    private String path;
    private String description;
    private String size;
    private int star;
    private String image;
    private String time;

    public Download() {
    }

    public Download(int id, String name, String path, String description, String size, int star, String image, String time) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.description = description;
        this.size = size;
        this.star = star;
        this.image = image;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Download{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", star=" + star +
                ", image='" + image + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

package dao;

import vo.Download;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DownloadDao {
    private Connection con;
    private PreparedStatement pst;

    public DownloadDao(Connection con) {
        this.con=con;
    }

    public List<Download> selectAll() throws Exception{
        String sql = "select * from t_downloadList";
        this.pst = this.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<Download> downloadList = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String path = rs.getString("path");
            String description = rs.getString("description");
            long size = rs.getLong("size");
            String fileSize = FormatFileSize(size);
            int star = rs.getInt("star");
            String image = rs.getString("image");
            String time=rs.getString("time");
            Download download = new Download(id, name, path, description, fileSize, star, image,time);
            downloadList.add(download);
        }
        return downloadList;
    }

    public String FormatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    public Download get(int id) throws Exception{
        Download download=null;
        String sql="select * from t_downloadList where id=?";
        this.pst=this.con.prepareStatement(sql);
        pst.setInt(1,id);
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            download=new Download();
            download.setId(rs.getInt("id"));
            download.setName(rs.getString("name"));
            download.setPath(rs.getString("path"));
            download.setDescription(rs.getString("description"));
            long size = rs.getLong("size");
            String fileSize = FormatFileSize(size);
            download.setSize(fileSize);
            download.setStar(rs.getInt("star"));
            download.setImage(rs.getString("image"));
            download.setTime(rs.getString("time"));
        }
        return download;
    }
}

package com.simplereader.graduation.model;

/**
 * Created by gxj on 2017/3/26.
 */

public class Video {
    /**
     * backup_url_1 : aHR0cDovL3Y3LnBzdGF0cC5jb20vYmM0YmExYTk4MGY2OGY2NmQxMmM4Mjg2NjZhYjBmZDkvNTg5YzI4NGIvdmlkZW8vbS8xMTQzMDI2MDAwMDJkZTE5MTdiM2QwZDIyMDFhYWM1ZWFlYzI0YjQ5YTBiYjJhNGZiMDU1NWYzNWUzLw==
     * bitrate : 228992
     * definition : 360p
     * main_url : aHR0cDovL3YxLjM2NXlnLmNvbS8yMjAxYjhlYmVkZGJmY2MwYjg3MWQ0ZWNjMWM1OWE3ZC81ODljMjg0Yi92aWRlby9tLzExNDMwMjYwMDAwMmRlMTkxN2IzZDBkMjIwMWFhYzVlYWVjMjRiNDlhMGJiMmE0ZmIwNTU1ZjM1ZTMv
     * preload_interval : 25
     * preload_max_step : 10
     * preload_min_step : 5
     * preload_size : 327680
     * size : 8357746.0
     * socket_buffer : 1.373952E8
     * user_video_proxy : 1
     * vheight : 360
     * vtype : mp4
     * vwidth : 640
     */
    private String backup_url_1;
    private int bitrate;
    private String definition;
    private String main_url;
    private int preload_interval;
    private int preload_max_step;
    private int preload_main_step;
    private int preload_size;
    private double size;
    private double socket_buffer;
    private int user_video_proxy;
    private int vheight;
    private String vtype;
    private int vwdith;

    @Override
    public String toString() {
        return "Video{" +
                "backup_url_1='" + backup_url_1 + '\'' +
                ", bitrate=" + bitrate +
                ", definition='" + definition + '\'' +
                ", main_url='" + main_url + '\'' +
                ", preload_interval=" + preload_interval +
                ", preload_max_step=" + preload_max_step +
                ", preload_main_step=" + preload_main_step +
                ", preload_size=" + preload_size +
                ", size=" + size +
                ", socket_buffer=" + socket_buffer +
                ", user_video_proxy=" + user_video_proxy +
                ", vheight=" + vheight +
                ", vtype='" + vtype + '\'' +
                ", vwdith=" + vwdith +
                '}';
    }
}

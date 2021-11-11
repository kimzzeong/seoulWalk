package com.example.seoulwalk.data;

public class PostData {

    private String imageData;
    private String textData;
    private String youtubeData;
    private String youtubeEdit;
    public String getYoutubeData() {
        return youtubeData;
    }

    public void setYoutubeData(String youtubeData) {
        this.youtubeData = youtubeData;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getTextData() {
        return textData;
    }

    public void setEditData(String editData) {
        this.textData = editData;
    }

    public String getYoutubeEdit() {
        return youtubeEdit;
    }

    public void setYoutubeEdit(String youtubeEdit) {
        this.youtubeEdit = youtubeEdit;
    }

    public PostData(String imageData, String textData, String youtubeData, String youtubeEdit){
        this.imageData = imageData;
        this.textData = textData;
        this.youtubeData = youtubeData;
        this.youtubeEdit = youtubeEdit;
    }

}

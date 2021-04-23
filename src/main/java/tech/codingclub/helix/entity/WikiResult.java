package tech.codingclub.helix.entity;

public class WikiResult {
    private String query;
    private String textResult;
    private String imageURL;

    public WikiResult(){

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public WikiResult(String keyWord, String response, String imageURL) {
        this.query = keyWord;
        this.textResult = response;
        this.imageURL = imageURL;
    }
    public String getTextResult(){
        return textResult;
    }
}

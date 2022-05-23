package data;

import core.BaseTest;

import java.io.FileNotFoundException;

public class DataFactory extends BaseTest {

    private String url;

   
    public DataFactory( String gotoUrl ) throws FileNotFoundException {
        setUrl(gotoUrl);
    }

    public String getUrl() {
        return url;
    }


    public void setUrl(String gotoUrl) throws FileNotFoundException {
        this.url = rx.readConfig().get(gotoUrl);
    }




}

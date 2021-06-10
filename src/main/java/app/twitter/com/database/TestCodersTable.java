package app.twitter.com.database;

import app.twitter.com.entity.Coders;

public class TestCodersTable {

    public static void main(String[] args) {
        Coders codersTest = new Coders("BhanuPrakash ",21);
        //Insert this object into DB !
        new GenericDB<Coders>().addRow(app.twitter.com.tables.Coders.CODERS, codersTest);
    }
}

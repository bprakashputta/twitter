package app.twitter.com.database;

import app.twitter.com.entity.Coders;

public class TestCodersTable {

    public static void main(String[] args) {
        Coders coders = new Coders("Bhanu", 21);
        new GenericDB<Coders>().addRow(app.twitter.com.tables.Coders.CODERS ,coders);
    }
}

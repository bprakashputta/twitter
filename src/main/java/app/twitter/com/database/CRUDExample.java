package app.twitter.com.database;

import app.twitter.com.entity.Coders;
import org.jooq.meta.derby.sys.Sys;

import java.util.Date;
import java.util.List;

public class CRUDExample {
    public static void main(String[] args) {
        readExample();
        updateExample();
        readExample();
        insertExample();
        readExample();
        deleteExample();
    }

    private static void deleteExample() {
//        new GenericDB<Coders>().deleteRow();
    }

    private static void insertExample() {
        Coders spammer = new Coders("Spammer", 25);
        new GenericDB<Coders>().addRow(app.twitter.com.tables.Coders.CODERS, spammer);
    }

    private static void updateExample() {
        //It has 4 parameters
        //1. Parameter to change
        //2. Value for Updating
        //3. Which Table to Update
        //4. Condition
        new GenericDB<String>().updateColumn(app.twitter.com.tables.Coders.CODERS.NAME,"Tharun Sai", app.twitter.com.tables.Coders.CODERS, app.twitter.com.tables.Coders.CODERS.NAME.eq(" Tharun Sai "));
    }

    private static void readExample() {
        System.out.println("This side Bhanu Prakash,"+ new Date().toString());
        Coders coders = new GenericDB<Coders>().getRow( app.twitter.com.tables.Coders.CODERS,Coders.class,null);
        System.out.println(coders.getName());
        System.out.println("#####################################");
        List<Coders> list = (List<Coders>) GenericDB.getRows(app.twitter.com.tables.Coders.CODERS,Coders.class,null,null);
        for(Coders l : list){
            System.out.println(l.getAge() + " " + l.getName());
        }
        System.out.println("#####################################");
    }
}

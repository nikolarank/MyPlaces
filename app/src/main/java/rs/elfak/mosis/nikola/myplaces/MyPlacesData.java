package rs.elfak.mosis.nikola.myplaces;

import java.util.ArrayList;

/**
 * Created by Nikola on 2017-03-26.
 */

public class MyPlacesData {
    ArrayList<MyPlace> myPlaces;
    MyPlacesDBAdapter dbAdapter;

    private MyPlacesData() {

        dbAdapter = new MyPlacesDBAdapter(MyPlacesApplication.getContext());
        dbAdapter.open();
        this.myPlaces = dbAdapter.getAllEntries();
        dbAdapter.close();
        myPlaces = new ArrayList<MyPlace>();
    }

    private static class SingletonHolder{
        public static final MyPlacesData instance = new MyPlacesData();
    }

    public static MyPlacesData getInstance(){
        return SingletonHolder.instance;
    }

    public ArrayList<MyPlace> getMyPlaces() {
        return myPlaces;
    }

    public MyPlace getPlace(int index){
        return myPlaces.get(index);
    }

    public void addNewPlace(MyPlace place){
        myPlaces.add(place);
        dbAdapter.open();
        long ID = dbAdapter.insertEntry(place);
        dbAdapter.close();
        place.setID(ID);
    }

    public void deletePlace(int index){
        MyPlace place = myPlaces.remove(index);
        dbAdapter.open();
        boolean success = dbAdapter.removeEntry(place.getID());
        dbAdapter.close();
    }

    public void updatePlace(MyPlace place){
        dbAdapter.open();
        dbAdapter.updateEntry(place.getID(), place);
        dbAdapter.close();
    }

}

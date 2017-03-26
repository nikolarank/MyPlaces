package rs.elfak.mosis.nikola.myplaces;

import java.util.ArrayList;

/**
 * Created by Nikola on 2017-03-26.
 */

public class MyPlacesData {
    ArrayList<MyPlace> myPlaces;

    public MyPlacesData() {
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
    }

    public void deletePlace(int index){
        myPlaces.remove(index);
    }
}

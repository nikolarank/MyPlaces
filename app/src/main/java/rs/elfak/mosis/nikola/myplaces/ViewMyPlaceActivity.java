package rs.elfak.mosis.nikola.myplaces;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewMyPlaceActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_place);

        int positon = -1;
        try{
            Intent listIntent = getIntent();
            Bundle positionBundle = listIntent.getExtras();
            positon = positionBundle.getInt("position");
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }

        if(positon >= 0){
            MyPlace place = MyPlacesData.getInstance().getPlace(positon);
            TextView tvName = (TextView) findViewById(R.id.viewmyplace_name_text);
            tvName.setText(place.getName());

            TextView tvDesc = (TextView) findViewById(R.id.viewmyplace_desc);
            tvDesc.setText(place.getDescription());
        }

        final Button finishedButton = (Button) findViewById(R.id.viewmyplace_finished_buttom);
        finishedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view_my_place, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.show_map_item){
            Toast.makeText(this, "Show Map!", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.my_places_list_item){
            Intent i = new Intent(this, MyPlacesList.class);
            startActivity(i);
        }else if(id == R.id.about_item){
            Intent i = new Intent(this, About.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}

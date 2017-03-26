package rs.elfak.mosis.nikola.myplaces;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import rs.elfak.mosis.nikola.myplaces.R;

/**
 * Created by Nikola on 2017-03-26.
 */

public class About extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Button ok = (Button) findViewById(R.id.about_ok);
        ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }

}

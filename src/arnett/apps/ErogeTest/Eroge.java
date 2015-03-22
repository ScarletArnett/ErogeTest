package arnett.apps.ErogeTest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;

import java.lang.reflect.Type;


public class Eroge extends Activity {

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final int subs_waiting = 500;
        final Handler mHandler     = new Handler();
        final ImageView imageView = (ImageView)      findViewById(R.id.imageView)     ;
        final RelativeLayout rv   = (RelativeLayout) findViewById(R.id.relativeLayout);

        final String text                   = "Il était une fois, une phrase super longue, bien chiante, histoire de voir si   ma bande est assez longue et si ça retourne bien à la ligne!";
        final String text_two               = "Hey, en fait je veux pas de verre :-)";
        final String text_three             = "Espèce de pervers ! ~(-.-'~)";
        final String text_four              = "Ne reviens jamais, hors de ma vue Q_Q";
        final String choice_one_first_slide = "Aller boire un verre avec elle";
        final String choice_snd_first_slide = "Aller à la piscine avec elle";

        final Button skip_one = createSkipButton();
        final TypeWriter writer = createWriter(text);
        final Button one_first_slide = createChoice(950,50,choice_one_first_slide);
        final Button snd_first_slide = createChoice(950,450,choice_snd_first_slide);

        final TypeWriter first_skip = skipClickListener(skip_one,writer,text);
        rv.addView(skip_one);
        rv.addView(writer);

        /**
         * Delayed button appears !
         */

        mHandler.postDelayed(new Runnable() {
            public void run() {
                rv.addView(one_first_slide);
                rv.addView(snd_first_slide);
            }
        }, getWaitingTime(text) + subs_waiting);


        /**
         * Buttons listeners
         */
        one_first_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.removeView(one_first_slide);
                rv.removeView(snd_first_slide);
                rv.removeView(writer);
                rv.removeView(first_skip);

                imageView.setImageResource(R.drawable.second_landscape);
                final TypeWriter writer_two = createWriter(text_two);
                rv.addView(writer_two);
            }
        });

        snd_first_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * remove last components bakcground
                 */
                rv.removeView(one_first_slide);
                rv.removeView(snd_first_slide);
                rv.removeView(writer);
                rv.removeView(first_skip);

                imageView.setImageResource(R.drawable.third_landscape);
                final TypeWriter writer_three = createWriter(text_three);
                TypeWriter third_skip = skipClickListener(skip_one,writer_three,text_three);

                rv.removeView(skip_one);
                rv.addView(third_skip);
                rv.addView(writer_three);

                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        final TypeWriter writer_four  = createWriter(text_four) ;
                        rv.removeView(writer_three);
                        rv.addView(writer_four);
                    }
                }, getWaitingTime(text_four) + subs_waiting);


            }
        });
    }

    /**
     * Do I need to explain?
     * @param text
     * @return
     */
    public TypeWriter createWriter(String text){
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/TypeWriter.ttf");
        TypeWriter writer    = new TypeWriter(getApplicationContext());
        writer.setX(25);
        writer.setY(1000);
        writer.setCharacterDelay(35);
        writer.animateText(text);
        writer.setTypeface(type);

        return writer;
    }

    public TypeWriter createWriter(String text, int delay){
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/TypeWriter.ttf");
        TypeWriter writer    = new TypeWriter(getApplicationContext());
        writer.setX(25);
        writer.setY(1000);
        writer.setCharacterDelay(delay);
        writer.animateText(text);
        writer.setTypeface(type);

        return writer;
    }

    /**
     * get the average time to display all the text
     * @param text the text you want to wait
     * @return for each letters + 35ms ( writer delay )
     */
    public int getWaitingTime(String text){
        return text.length()*35;
    }

    /**
     * use to create choice after displaying text
     * @param x top corner abs
     * @param y top corner ord
     * @param text
     * @return
     */
    public Button createChoice(int x, int y, String text){
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/TypeWriter.ttf");
        Button button = new Button(getApplicationContext());
        button.setLayoutParams(new RelativeLayout.LayoutParams(600,350));
        button.setX(x);
        button.setY(y);
        //button.setBackgroundResource(R.drawable.ic_launcher);
        button.setTypeface(type);
        button.setText(text);

        return button;
    }

    public Button createSkipButton(){
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/TypeWriter.ttf");
        Button button = new Button(getApplicationContext());
        button.setLayoutParams(new RelativeLayout.LayoutParams(400,200));
        button.setText("Passer");
        button.setTypeface(type, Typeface.ITALIC);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTextColor(Color.WHITE);

        return button;
    }

    public TypeWriter skipClickListener (Button button, final TypeWriter tp, String text){

        final RelativeLayout rv   = (RelativeLayout) findViewById(R.id.relativeLayout);
        final TypeWriter new_tp       = createWriter(text,0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.removeView(new_tp);
                rv.removeView(tp);
                rv.addView(new_tp);
            }
        });

        return new_tp;
    }

    /**
     * needed Method to create Menu
     * @param menu
     * @return true to display
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * selected items on menu
     * @param item selectedItem
     * @return apply and true after applying
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_bookmark:
                Toast.makeText(Eroge.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_save:
                Toast.makeText(Eroge.this, "Save is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_search:
                Toast.makeText(Eroge.this, "Search is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_share:
                Toast.makeText(Eroge.this, "Share is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_delete:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;

            case R.id.menu_preferences:
                Toast.makeText(Eroge.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

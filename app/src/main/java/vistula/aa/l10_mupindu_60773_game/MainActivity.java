package vistula.aa.l10_mupindu_60773_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button[][] buttons;
    int buttonsID[][];
    int sideButtons,left,top,totalButtons,score;
    ConstraintLayout constraintLayout;
    ConstraintLayout.LayoutParams params;
    ConstraintSet set;
    int[] numbers,numbersAfterPermutation,playerNumber;
    int currentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout =(ConstraintLayout) findViewById(R.id.ConstraintLayout);
        left=80;
        top =330;
        sideButtons =4;
        totalButtons =sideButtons*sideButtons;
        numbers = new int[totalButtons];
        for (int i = 0; i < totalButtons;) {
            numbers[i]=++i;
        }
        numbersAfterPermutation=numbers;
        playerNumber = new int[totalButtons];
        buttons = new Button[sideButtons][sideButtons];
        buttonsID= new int[sideButtons][sideButtons];
        loadButtons();
        score=0;
    }

    private void loadButtons() {

        for (int i = 0; i < sideButtons; i++) {
            for (int j = 0; j < sideButtons; j++) {
                buttons[i][j]=new Button(this);
                buttons[i][j].setId(View.generateViewId());
                buttonsID[i][j]=buttons[i][j].getId();
                params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
                buttons[i][j].setLayoutParams(params);
                constraintLayout.addView(buttons[i][j]);
                set = new ConstraintSet();
                set.clone(constraintLayout);
                if(j==0){
                    set.connect(buttons[i][j].getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT,left);
                } else{
                    set.connect(buttons[i][j].getId(),ConstraintSet.LEFT,buttons[i][j-1].getId(),ConstraintSet.RIGHT,0);
                }
                if(i==0){
                    set.connect(buttons[i][j].getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP,top);
                } else{
                    set.connect(buttons[i][j].getId(),ConstraintSet.TOP,buttons[i-1][j].getId(),ConstraintSet.BOTTOM,0);
                }
                set.applyTo(constraintLayout);
                int finalJ =j;
                int finalI = i;
                String strNumberZero = buttons[0][0].getText().toString();
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button clickedButton = (Button)view;
                        String temp="";
                        temp = clickedButton.getText().toString();
                        if(finalI!=0){
                               if(finalJ!=0){
                                   if(buttons[finalI][finalJ].getText().toString().equals("0")){
                                       buttons[finalI][finalI].setText(temp);
                                   }
                                   else if(buttons[finalI][finalJ+1].getText().toString().equals("0")){
                                       buttons[finalI][finalJ+1].setText(temp);
                                   }else if(buttons[finalI+1][finalJ].getText().toString().equals("0")){
                                       buttons[finalI][finalJ+1].setText(temp);
                                   }
                               }
                        }

                    }
                });
            }
        }
        loadNumbers();
    }


    private void loadNumbers() {
        String str;
        numbersAfterPermutation = ArrayPermutation.ShuffleFisherYeats(numbers);
        numbersAfterPermutation[0]=0;
        for (int i = 0; i < sideButtons; i++) {
            for (int j = 0; j < sideButtons; j++) {
                str = Integer.toString(numbersAfterPermutation[i*sideButtons+j]);
                buttons[i][j].setText(str);
            }
        }
    }


}
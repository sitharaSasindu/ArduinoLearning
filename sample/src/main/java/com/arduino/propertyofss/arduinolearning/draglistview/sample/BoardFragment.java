package com.arduino.propertyofss.arduinolearning.draglistview.sample;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arduino.propertyofss.arduinolearning.draglistview.BoardView;
import com.arduino.propertyofss.arduinolearning.draglistview.DragItem;

import java.util.ArrayList;

public class BoardFragment extends Fragment {

    private static long id = 0;
    private static long id2 = 0;
    private BoardView mBoardView;

    int i;//declare the variabale i

    static ArrayList<Pair<Long, String>> availableFunctionArray = new ArrayList<>();//storing all the functions available for selected model
    static ArrayList<Pair<Long, String>> functionsArray = new ArrayList<>();//this array will be similar to availableFunctionsArray.
    //but elements are permanent. I use this to determine
    // selected cardview of function view
    static ArrayList<Pair<Long, String>> AddedFunctions = new ArrayList<>();//functions which are added to implement next

    public static BoardFragment newInstance() {
        return new BoardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        functionsArray.add(new Pair<>(id2, "Go Straight" ));//this will add same elements in the availableFunctionsArray
        functionsArray.add(new Pair<>(++id2, "Turn Left 90 Degrees" ));      //to functionsArray when creating
        functionsArray.add(new Pair<>(++id2, "Turn Right 90 Degree" ));
        functionsArray.add(new Pair<>(++id2, "Go Backwards" ));
        functionsArray.add(new Pair<>(++id2, "Run 1 Seconds" ));
        functionsArray.add(new Pair<>(++id2, "Run 2 Seconds" ));
        functionsArray.add(new Pair<>(++id2, "Run 5 Seconds" ));
        functionsArray.add(new Pair<>(++id2, "Stop" ));
        functionsArray.add(new Pair<>(++id2, "Right Turn 180 Degrees" ));
        functionsArray.add(new Pair<>(++id2, "Left Turn 180 Degrees" ));
        functionsArray.add(new Pair<>(++id2, "Zig Zag" ));
        functionsArray.add(new Pair<>(++id2, "Comeback" ));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_layout, container, false);

        mBoardView = view.findViewById(R.id.board_view);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);
        mBoardView.setCustomDragItem(new MyDragItem(getActivity(), R.layout.column_item));
        mBoardView.setSnapToColumnInLandscape(false);
        mBoardView.setColumnSnapPosition(BoardView.ColumnSnapPosition.CENTER);

        mBoardView.setBoardListener(new BoardView.BoardListener() { //this is where the android is listning the activities which is happening in the
            //drag and drop screen.

            @Override
            public void onItemDragStarted(int column, int row) {
                i = (int)ItemAdapter.selectedId;// this is the unique id of currently selected item.
                //this id is comng from the ItemAdapter class.
            }

            @Override
            public void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow) {

            }

            @Override
            public void onItemChangedPosition(int oldColumn, int oldRow, int newColumn, int newRow) {

            }

            @Override //This method will be called when the items on the rows changed.
            ///when cardviews are changed
            public void onItemChangedColumn(int oldColumn, int newColumn) {
                TextView itemCount1 = mBoardView.getHeaderView(oldColumn).findViewById(R.id.item_count);
                itemCount1.setText(String.valueOf(mBoardView.getAdapter(oldColumn).getItemCount()));//setting the
                // header text view's item count
                TextView itemCount2 = mBoardView.getHeaderView(newColumn).findViewById(R.id.item_count);
                itemCount2.setText(String.valueOf(mBoardView.getAdapter(newColumn).getItemCount()));

                //from upper code we are getting the unique arraylist id of the selected element of the availableFuntionsArray.
                String name = functionsArray.get(i).second;//here we get the string name of that element from the pre created arralist
                //which is similar to functionarray.

                availableFunctionArray.add(new Pair<>(++id, name));   //adding again same cardview which was now removed from the availableFunctionsArray
                functionsArray.add(new Pair<>(++id2, name)); //adding the cardview to ths arraylist also. bcz it is always simliar
                //to availablefunctionsArray
            }

            @Override
            public void onFocusedColumnChanged(int oldColumn, int newColumn) {
            }
        });

        mBoardView.setBoardCallback(new BoardView.BoardCallback() {
            @Override
            public boolean canDragItemAtPosition(int column, int dragPosition) {
                // Add logic here to prevent an item to be dragged
                return true;
            }

            @Override
            public boolean canDropItemAtPosition(int oldColumn, int oldRow, int newColumn, int newRow) {

                return true;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Board");

        columnList();//calling the arraylsits when the activity is created
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_board, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //menu.findItem(R.id.action_disable_drag).setVisible(mBoardView.isDragEnabled());
        //menu.findItem(R.id.action_enable_drag).setVisible(!mBoardView.isDragEnabled());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { //activity of menu items on the drag and drop class.
            case R.id.action_run:
                ActionList.listOfActions();//calling sending added functions methodto the arduino board
                return true;
            case R.id.action_refresh:
                mBoardView.clearBoard(); //clear the addesfunctions array.
                AddedFunctions.clear();  //build a new one
                addList();
                Bluetooth.mConnectedThread.write("5");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void columnList() { //adding elemments to availableFunctionsArray
        availableFunctionArray.add(new Pair<>(id, "Go Straight" ));
        availableFunctionArray.add(new Pair<>(++id, "Turn Left" ));
        availableFunctionArray.add(new Pair<>(++id, "Turn Right" ));
        availableFunctionArray.add(new Pair<>(++id, "Go Backwards" ));
        availableFunctionArray.add(new Pair<>(++id, "Run 1 Seconds" ));
        availableFunctionArray.add(new Pair<>(++id, "Run 2 Seconds" ));
        availableFunctionArray.add(new Pair<>(++id, "Run 5 Seconds" ));
        availableFunctionArray.add(new Pair<>(++id, "Object Detection"));
        availableFunctionArray.add(new Pair<>(++id, "Stop"));
        availableFunctionArray.add(new Pair<>(++id, "Right Turn 180 Degrees"));
        availableFunctionArray.add(new Pair<>(++id, "Left Turn 180 Degrees"));
        availableFunctionArray.add(new Pair<>(++id, "Zig Zag"));
        availableFunctionArray.add(new Pair<>(++id, "Comeback"));
        addList();

    }

    public void addList(){ //this will add the arraylist elements into the board(cardvews)

        final ItemAdapter listAdapter = new ItemAdapter(availableFunctionArray, R.layout.column_item, R.id.item_layout, true);
        final View header = View.inflate(getActivity(), R.layout.column_header, null);
        ((TextView) header.findViewById(R.id.text)).setText("Functions Available");
        mBoardView.addColumnList(listAdapter, header, false);


        final ItemAdapter listAdapter1 = new ItemAdapter(AddedFunctions, R.layout.column_item, R.id.item_layout, true);
        final View header1 = View.inflate(getActivity(), R.layout.column_header, null);
        ((TextView) header1.findViewById(R.id.text)).setText("Functions Added");
        mBoardView.addColumnList(listAdapter1, header1, false);

    }


    private static class MyDragItem extends DragItem {

        MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence text = ((TextView) clickedView.findViewById(R.id.text)).getText();
            ((TextView) dragView.findViewById(R.id.text)).setText(text);
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));

            dragCard.setMaxCardElevation(40);
            dragCard.setCardElevation(clickedCard.getCardElevation());
            // I know the dragView is a FrameLayout and that is why I can use setForeground below api level 23
            dragCard.setForeground(clickedView.getResources().getDrawable(R.drawable.card_view_drag_foreground));
        }

        @Override
        public void onMeasureDragView(View clickedView, View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));
            int widthDiff = dragCard.getPaddingLeft() - clickedCard.getPaddingLeft() + dragCard.getPaddingRight() -
                    clickedCard.getPaddingRight();
            int heightDiff = dragCard.getPaddingTop() - clickedCard.getPaddingTop() + dragCard.getPaddingBottom() -
                    clickedCard.getPaddingBottom();
            int width = clickedView.getMeasuredWidth() + widthDiff;
            int height = clickedView.getMeasuredHeight() + heightDiff;
            dragView.setLayoutParams(new FrameLayout.LayoutParams(width, height));

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            dragView.measure(widthSpec, heightSpec);
        }

        @Override
        public void onStartDragAnimation(View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 40);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(ANIMATION_DURATION);
            anim.start();
        }

        @Override
        public void onEndDragAnimation(View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 6);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(ANIMATION_DURATION);
            anim.start();
        }
    }
}
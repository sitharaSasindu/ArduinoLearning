
package com.arduino.propertyofss.arduinolearning.draglistview;

import com.arduino.propertyofss.arduinolearning.BuildConfig;


import com.arduino.propertyofss.arduinolearning.draglistview.BoardView;
import com.arduino.propertyofss.arduinolearning.draglistview.DragItemAdapter;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNull;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class BoardViewTest {
    private BoardView subject;
    private DragItemAdapter adapter;
    private long firstItemId;

    @Before
    public void setUp() throws Exception {
        adapter = mock(DragItemAdapter.class);
        when(adapter.hasStableIds()).thenReturn(true);

        subject = new BoardView(RuntimeEnvironment.application);
        subject.onFinishInflate();
    }

    @Test
    public void getRecyclerView_beforeAddingColumnList_returnsNull() {
        assertNull(subject.getRecyclerView(0));
    }

    @Test
    public void getRecyclerView_afterAddingColumnList_createsNewRecyclerView() {
        subject.addColumnList(adapter, mock(View.class), false);

        assertThat(subject.getRecyclerView(0)).isNotNull();
    }

    @Test
    public void columnDragging_whenDraggingItem_callsOnDragItemChangedPosition() {
        BoardView.BoardListener boardListener = mock(BoardView.BoardListener.class);
        subject.setBoardListener(boardListener);

        createColumnsAndDrag(adapter);

        verify(boardListener).onItemChangedPosition(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void columnDragging_whenDraggingItem_whenItemPositionChanges_callsOnDragItemChangedPosition() {
        int firstItemPosition = 3;
        BoardView.BoardListener boardListener = mock(BoardView.BoardListener.class);
        subject.setBoardListener(boardListener);
        DragItemRecyclerView column = createColumnsAndDrag(adapter);
        reset(boardListener);

        when(adapter.getPositionForItemId(firstItemId)).thenReturn(firstItemPosition);
        column.onDragging(0, 0);

        verify(boardListener).onItemChangedPosition(0, 0, 0, firstItemPosition);
    }

    private DragItemRecyclerView createColumnsAndDrag(DragItemAdapter adapter) {
        when(adapter.removeItem(anyInt())).thenReturn(mock(Object.class));
        DragItemRecyclerView column = subject.addColumnList(adapter, null, false);
        View view = mock(View.class);
        when(view.getWidth()).thenReturn(1);
        when(view.getHeight()).thenReturn(1);
        firstItemId = 1L;
        column.startDrag(view, firstItemId, 0.2f, 0.4f);

        column.onDragging(0, 1);

        return column;
    }
}

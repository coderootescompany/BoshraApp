package com.example.hossam.boshraapp.Helpers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.hossam.boshraapp.Fragments.ImagesGalleryFragment;
import com.example.hossam.boshraapp.Fragments.ImagesGalleryFragmentForSubCatogry;
import com.example.hossam.boshraapp.Fragments.VideosFragment;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
  private OnItemClickListener mListener;

  public RecyclerItemClickListener(VideosFragment videosFragment, OnItemClickListener onItemClickListener) {
    mListener = onItemClickListener;
    mGestureDetector = new GestureDetector(videosFragment.getContext(), new GestureDetector.SimpleOnGestureListener() {
      @Override
      public boolean onSingleTapUp(MotionEvent e) {
        return true;
      }
    });

  }


  public interface OnItemClickListener {
    public void onItemClick(View view, int position);
  }

  GestureDetector mGestureDetector;

  public RecyclerItemClickListener(ImagesGalleryFragment context, OnItemClickListener listener) {
    mListener = listener;
    mGestureDetector = new GestureDetector(context.getContext(), new GestureDetector.SimpleOnGestureListener() {
      @Override
      public boolean onSingleTapUp(MotionEvent e) {
        return true;
      }
    });
  }

  public RecyclerItemClickListener(ImagesGalleryFragmentForSubCatogry imagesGalleryFragmentForSubCatogry, OnItemClickListener listener) {
    mListener = listener;
    mGestureDetector = new GestureDetector(imagesGalleryFragmentForSubCatogry.getContext(), new GestureDetector.SimpleOnGestureListener() {
      @Override
      public boolean onSingleTapUp(MotionEvent e) {
        return true;
      }
    });
  }



  @Override
  public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
    View childView = view.findChildViewUnder(e.getX(), e.getY());
    if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
      mListener.onItemClick(childView, view.getChildPosition(childView));
      return true;
    }
    return false;
  }

  @Override
  public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

  }
}
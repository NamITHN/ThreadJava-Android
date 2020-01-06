package com.namnt.demomultithread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.namnt.demomultithread.databinding.ActivityMainBinding;


public class ClickListener {
    private int data;
    private ActivityMainBinding mainBinding;
    private Handler handler;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (data > 8) {

                try {
                    Thread.sleep(1000);
                    data--;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("nam.nt1",data+" ");
                  /*  Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = data;

                    handler.sendMessage(msg);*/

                   /* handler.post(new Runnable() {
                        @Override
                        public void run() {
                       mainBinding.setNumber(data);
                        }
                    });*/
            }
        }
    });
    public ClickListener(int data, ActivityMainBinding mainBinding, Handler handler) {
        this.data = data;
        this.mainBinding = mainBinding;
        this.handler = handler;
    }

    public void threadHandlerClick(View view) {

        thread.start();
    }

    public void asyncTaskClick(View view) {
        Asyn asyn = new Asyn();
        asyn.execute(data);
    }

    class Asyn extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... data) {
            while (data[0] > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data[0]--;
                publishProgress(data[0]);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mainBinding.setNumber(values[0]);
            Log.d("nam.nt1", values[0] + " ");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mainBinding.setDone("Done");
        }
    }
}

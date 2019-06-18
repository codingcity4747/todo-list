package project.todolist;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class userNotification {

  private Application application;


    public userNotification(Application application){
        this.application = application;
    }

    public void makeToast (String message){
        Toast.makeText(application.getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}

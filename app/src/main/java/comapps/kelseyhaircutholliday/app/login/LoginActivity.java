package comapps.kelseyhaircutholliday.app.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import comapps.kelseyhaircutholliday.app.MainActivity;
import comapps.kelseyhaircutholliday.app.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends Activity
{

  private static final String TAG = "LOGINACTIVITY";

  private TextView registerLink, restoreLink;
  private EditText identityField, passwordField;
  private Button loginButton;
  private CheckBox rememberLoginBox;
  private boolean rememberLogin;

    SharedPreferences finalSettings = null;
    Boolean firstRun;




  @Override
  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/Chalkduster.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build());

      finalSettings = getSharedPreferences("myPrefs", 0);
      firstRun = finalSettings.getBoolean("firstrun", true);

      if (firstRun) {
          // Do first run stuff here then set 'firstrun' as false
          // using the following line to edit/commit prefs


          Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();


          finalSettings.edit().putBoolean("firstrun", false).apply();

  }


    setContentView( R.layout.login );

    initUI();

    Backendless.setUrl( Defaults.SERVER_URL );
    Backendless.initApp( this, Defaults.APPLICATION_ID, Defaults.SECRET_KEY, Defaults.VERSION );

    Backendless.UserService.isValidLogin( new DefaultCallback<Boolean>( this )
    {
      @Override
      public void handleResponse( Boolean isValidLogin )
      {
        if( isValidLogin && Backendless.UserService.CurrentUser() == null )
        {
          final String currentUserId = Backendless.UserService.loggedInUser();

          if( !currentUserId.equals( "" ) )
          {
            Backendless.UserService.findById( currentUserId, new DefaultCallback<BackendlessUser>( LoginActivity.this, "Logging in..." )
            {
              @Override
              public void handleResponse( BackendlessUser currentUser )
              {
                super.handleResponse( currentUser );
                Backendless.UserService.setCurrentUser( currentUser );




                startActivity( new Intent( getBaseContext(), MainActivity.class ) );
                finish();
              }
            } );
          }
        }

        super.handleResponse( isValidLogin );
      }
    });
  }

  private void initUI()
  {



    registerLink = (TextView) findViewById( R.id.registerLink );
    restoreLink = (TextView) findViewById( R.id.restoreLink );
    identityField = (EditText) findViewById( R.id.identityField );
    passwordField = (EditText) findViewById( R.id.passwordField );
    loginButton = (Button) findViewById( R.id.loginButton );
    rememberLoginBox = (CheckBox) findViewById( R.id.rememberLoginBox );

    firstRun = finalSettings.getBoolean("firstrun", true);

if ( !firstRun ) {
    rememberLogin = finalSettings.getBoolean("rememberLogin", false);

    if (finalSettings.getBoolean("rememberLogin", false) == false) {

        Log.i(TAG, "rememberLogin not equal to true");
        rememberLoginBox.setChecked(false);

    } else {

        identityField.setText(finalSettings.getString("userLogin", null));
        rememberLoginBox.setChecked(true);
        Log.i(TAG, "Shared preferences are " + finalSettings.toString());

    }

}








      String tempString = getResources().getString( R.string.register_text );
    SpannableString underlinedContent = new SpannableString( tempString );
    underlinedContent.setSpan( new UnderlineSpan(), 0, tempString.length(), 0 );
    registerLink.setText( underlinedContent );
    tempString = getResources().getString( R.string.restore_link );
    underlinedContent = new SpannableString( tempString );
    underlinedContent.setSpan( new UnderlineSpan(), 0, tempString.length(), 0 );
    restoreLink.setText( underlinedContent );

    loginButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onLoginButtonClicked();
      }
    } );

    registerLink.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onRegisterLinkClicked();
      }
    } );

    restoreLink.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onRestoreLinkClicked();
      }
    } );
  }

  public void onLoginButtonClicked()
  {
    String identity = identityField.getText().toString();
    String password = passwordField.getText().toString();
    rememberLogin = rememberLoginBox.isChecked();




      Backendless.UserService.login( identity, password, new DefaultCallback<BackendlessUser>( LoginActivity.this )
    {
      public void handleResponse( BackendlessUser backendlessUser )
      {
        super.handleResponse( backendlessUser );

          //   String userId = Backendless.UserService.loggedInUser();

          Log.i(TAG, "Current user is " + backendlessUser.getEmail());
          Log.i(TAG, "Current user is " + backendlessUser.getUserId());
          Log.i(TAG, "Current user is " + backendlessUser.getProperty("name"));

          Log.i(TAG, "Remember login is " + String.valueOf(rememberLogin));

          SharedPreferences finalSettings = getSharedPreferences("myPrefs", 0);
          SharedPreferences.Editor editor = finalSettings.edit();
          editor.putString("userLogin", backendlessUser.getEmail());
          editor.putBoolean("rememberLogin", rememberLogin);
          editor.commit();

          startActivity( new Intent( LoginActivity.this, MainActivity.class ) );
          finish();

      }
    }, true );
  }

  public void onRegisterLinkClicked()
  {
    startActivity( new Intent( this, RegisterActivity.class ) );
    finish();
  }

  public void onRestoreLinkClicked()
  {
    startActivity( new Intent( this, RestorePasswordActivity.class ) );
    finish();
  }

  @Override
  protected void attachBaseContext (Context newBase){

    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

  }

    @Override
    public void onBackPressed() {

        Intent switchactivities = new Intent();
        switchactivities.setClass(this, MainActivity.class);
        startActivity(switchactivities);
        overridePendingTransition(R.anim.fadeinanimationgallery,R.anim.fadeoutanimationgallery);
        finish();


    }


}
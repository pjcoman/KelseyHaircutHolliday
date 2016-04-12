package comapps.kelseyhaircutholliday.app.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import comapps.kelseyhaircutholliday.app.MainActivity;
import comapps.kelseyhaircutholliday.app.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends Activity
{
  private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat( "yyyy/MM/dd" );

  private EditText deviceIDField;
  private EditText emailField;
  private EditText genderField;
  private EditText nameField;
  private EditText passwordField;

  private Button registerButton;

  private String deviceID;
  private String email;
  private String gender;
  private String name;
  private String password;

  private KelseyHaircutUser user;

  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/Chalkduster.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build());


    setContentView( R.layout.register );

    initUI();
  }

  private void initUI()
  {deviceIDField = (EditText) findViewById( R.id.deviceIDField );emailField = (EditText) findViewById( R.id.emailField );genderField = (EditText) findViewById( R.id.genderField );nameField = (EditText) findViewById( R.id.nameField );passwordField = (EditText) findViewById( R.id.passwordField );

    registerButton = (Button) findViewById( R.id.registerButton );

    registerButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onRegisterButtonClicked();
      }
    } );
  }

  private void onRegisterButtonClicked()
  {
    String deviceIDText = deviceIDField.getText().toString().trim();
    String emailText = emailField.getText().toString().trim();
    String genderText = genderField.getText().toString().trim();
    String nameText = nameField.getText().toString().trim();
    String passwordText = passwordField.getText().toString().trim();

    if ( emailText.isEmpty() )
    {
      showToast( "Field 'email' cannot be empty." );
      return;
    }

    if ( passwordText.isEmpty() )
    {
      showToast( "Field 'password' cannot be empty." );
      return;
    }

    if( !deviceIDText.isEmpty() )
    {
      deviceID = deviceIDText;
    }

    if( !emailText.isEmpty() )
    {
      email = emailText;
    }

    if( !genderText.isEmpty() )
    {
      gender = genderText;
    }

    if( !nameText.isEmpty() )
    {
      name = nameText;
    }

    if( !passwordText.isEmpty() )
    {
      password = passwordText;
    }

    user = new KelseyHaircutUser();

    if( deviceID != null )
    {
      user.setDeviceID( deviceID );
    }

    if( email != null )
    {
      user.setEmail( email );
    }

    if( gender != null )
    {
      user.setGender( gender );
    }

    if( name != null )
    {
      user.setName( name );
    }

    if( password != null )
    {
      user.setPassword( password );
    }

    Backendless.UserService.register( user, new DefaultCallback<BackendlessUser>( RegisterActivity.this )
    {
      @Override
      public void handleResponse( BackendlessUser response )
      {
        super.handleResponse( response );
        startActivity( new Intent( RegisterActivity.this, RegistrationSuccessActivity.class ) );
        finish();
      }
    } );
  }

  private void showToast( String msg )
  {
    Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
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
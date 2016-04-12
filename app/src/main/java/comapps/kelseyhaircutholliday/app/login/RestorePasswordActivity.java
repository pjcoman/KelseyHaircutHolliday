package comapps.kelseyhaircutholliday.app.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;

import comapps.kelseyhaircutholliday.app.MainActivity;
import comapps.kelseyhaircutholliday.app.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RestorePasswordActivity extends Activity
{
  private Button restorePasswordButton;
  private EditText loginField;

  @Override
  public void onCreate( Bundle savedInstanceState )
  {
    super.onCreate( savedInstanceState );

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/chalkdust.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build());

    setContentView( R.layout.restore_password );

    initUI();
  }

  private void initUI()
  {
    restorePasswordButton = (Button) findViewById( R.id.restorePasswordButton );
    loginField = (EditText) findViewById( R.id.loginField );

    restorePasswordButton.setOnClickListener( new View.OnClickListener()
    {
      @Override
      public void onClick( View view )
      {
        onRestorePasswordButtonClicked();
      }
    } );
  }

  public void onRestorePasswordButtonClicked()
  {
    String login = loginField.getText().toString();
    Backendless.UserService.restorePassword( login, new DefaultCallback<Void>( this )
    {
      @Override
      public void handleResponse( Void response )
      {
        super.handleResponse( response );
        startActivity( new Intent( RestorePasswordActivity.this, PasswordRecoveryRequestedActivity.class ) );
        finish();
      }
    } );
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
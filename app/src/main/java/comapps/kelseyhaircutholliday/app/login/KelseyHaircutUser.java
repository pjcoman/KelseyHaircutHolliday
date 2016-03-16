package comapps.kelseyhaircutholliday.app.login;

import com.backendless.BackendlessUser;

public class KelseyHaircutUser extends BackendlessUser
{
  public String getEmail()
  {
    return super.getEmail();
  }

  public void setEmail( String email )
  {
    super.setEmail( email );
  }

  public String getPassword()
  {
    return super.getPassword();
  }

  public String getDeviceID()
  {
    return (String) super.getProperty( "deviceID" );
  }

  public void setDeviceID( String deviceID )
  {
    super.setProperty( "deviceID", deviceID );
  }

  public String getGender()
  {
    return (String) super.getProperty( "gender" );
  }

  public void setGender( String gender )
  {
    super.setProperty( "gender", gender );
  }

  public String getName()
  {
    return (String) super.getProperty( "name" );
  }

  public void setName( String name )
  {
    super.setProperty( "name", name );
  }
}
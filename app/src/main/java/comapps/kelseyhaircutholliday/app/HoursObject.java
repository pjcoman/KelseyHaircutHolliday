package comapps.kelseyhaircutholliday.app;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.BackendlessDataQuery;

public class HoursObject
{
  private String sort;
  private String hours;
  private java.util.Date created;
  private String objectId;
  private java.util.Date updated;
  private String dayofweek;
  private String ownerId;
  public String getSort()
  {
    return sort;
  }

  public void setSort( String sort )
  {
    this.sort = sort;
  }

  public String getHours()
  {
    return hours;
  }

  public void setHours( String hours )
  {
    this.hours = hours;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getDayofweek()
  {
    return dayofweek;
  }

  public void setDayofweek( String dayofweek )
  {
    this.dayofweek = dayofweek;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public HoursObject save()
  {
    return Backendless.Data.of( HoursObject.class ).save( this );
  }

  public Future<HoursObject> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<HoursObject> future = new Future<HoursObject>();
      Backendless.Data.of( HoursObject.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<HoursObject> callback )
  {
    Backendless.Data.of( HoursObject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( HoursObject.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( HoursObject.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( HoursObject.class ).remove( this, callback );
  }

  public static HoursObject findById( String id )
  {
    return Backendless.Data.of( HoursObject.class ).findById( id );
  }

  public static Future<HoursObject> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<HoursObject> future = new Future<HoursObject>();
      Backendless.Data.of( HoursObject.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<HoursObject> callback )
  {
    Backendless.Data.of( HoursObject.class ).findById( id, callback );
  }

  public static HoursObject findFirst()
  {
    return Backendless.Data.of( HoursObject.class ).findFirst();
  }

  public static Future<HoursObject> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<HoursObject> future = new Future<HoursObject>();
      Backendless.Data.of( HoursObject.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<HoursObject> callback )
  {
    Backendless.Data.of( HoursObject.class ).findFirst( callback );
  }

  public static HoursObject findLast()
  {
    return Backendless.Data.of( HoursObject.class ).findLast();
  }

  public static Future<HoursObject> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<HoursObject> future = new Future<HoursObject>();
      Backendless.Data.of( HoursObject.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<HoursObject> callback )
  {
    Backendless.Data.of( HoursObject.class ).findLast( callback );
  }

  public static BackendlessCollection<HoursObject> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( HoursObject.class ).find( query );
  }

  public static Future<BackendlessCollection<HoursObject>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<HoursObject>> future = new Future<BackendlessCollection<HoursObject>>();
      Backendless.Data.of( HoursObject.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<HoursObject>> callback )
  {
    Backendless.Data.of( HoursObject.class ).find( query, callback );
  }
}
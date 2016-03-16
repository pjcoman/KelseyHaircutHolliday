package comapps.kelseyhaircutholliday.app;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class ServicesObject
{
  private String objectId;
  private java.util.Date updated;
  private String sort;
  private String service;
  private java.util.Date created;
  private String price;
  private String ownerId;
  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getSort()
  {
    return sort;
  }

  public void setSort( String sort )
  {
    this.sort = sort;
  }

  public String getService()
  {
    return service;
  }

  public void setService( String service )
  {
    this.service = service;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getPrice()
  {
    return price;
  }

  public void setPrice( String price )
  {
    this.price = price;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public ServicesObject save()
  {
    return Backendless.Data.of( ServicesObject.class ).save( this );
  }

  public Future<ServicesObject> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ServicesObject> future = new Future<ServicesObject>();
      Backendless.Data.of( ServicesObject.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<ServicesObject> callback )
  {
    Backendless.Data.of( ServicesObject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( ServicesObject.class ).remove( this );
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
      Backendless.Data.of( ServicesObject.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( ServicesObject.class ).remove( this, callback );
  }

  public static ServicesObject findById( String id )
  {
    return Backendless.Data.of( ServicesObject.class ).findById( id );
  }

  public static Future<ServicesObject> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ServicesObject> future = new Future<ServicesObject>();
      Backendless.Data.of( ServicesObject.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<ServicesObject> callback )
  {
    Backendless.Data.of( ServicesObject.class ).findById( id, callback );
  }

  public static ServicesObject findFirst()
  {
    return Backendless.Data.of( ServicesObject.class ).findFirst();
  }

  public static Future<ServicesObject> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ServicesObject> future = new Future<ServicesObject>();
      Backendless.Data.of( ServicesObject.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<ServicesObject> callback )
  {
    Backendless.Data.of( ServicesObject.class ).findFirst( callback );
  }

  public static ServicesObject findLast()
  {
    return Backendless.Data.of( ServicesObject.class ).findLast();
  }

  public static Future<ServicesObject> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<ServicesObject> future = new Future<ServicesObject>();
      Backendless.Data.of( ServicesObject.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<ServicesObject> callback )
  {
    Backendless.Data.of( ServicesObject.class ).findLast( callback );
  }

  public static BackendlessCollection<ServicesObject> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( ServicesObject.class ).find( query );
  }

  public static Future<BackendlessCollection<ServicesObject>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<ServicesObject>> future = new Future<BackendlessCollection<ServicesObject>>();
      Backendless.Data.of( ServicesObject.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<ServicesObject>> callback )
  {
    Backendless.Data.of( ServicesObject.class ).find( query, callback );
  }
}